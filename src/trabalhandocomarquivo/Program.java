package trabalhandocomarquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import model.entities.Product;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        List<Product> products = new ArrayList<>();
        
        //Recebendo caminho do arquivo
        System.out.print("Digite o diretorio: ");
        String path = sc.nextLine();
        File pathFile = new File(path);
        
        //Leitura e criação das linhas separadas por ","
        try(BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String line = br.readLine();
            while(line != null){
                String[] vect = line.split(",");
                String name = vect[0];
                double price = Double.parseDouble(vect[1]);
                int quantity = Integer.parseInt(vect[2]);
                products.add(new Product(name, price, quantity));
                line = br.readLine();
            }
            //Obtendo o caminho da pasta sem o nome do arquivo
            String targetPath = pathFile.getParent();
            System.out.println(targetPath);
            boolean file = new File(targetPath + "\\out").mkdir();
            System.out.println(file);
            //Criando o arquivo
            String TargetFile = targetPath + "\\out\\summary.csv";
            //Escrevendo o produto formatado no novo arquivo criado
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(TargetFile))) {
                for(Product prod : products){
                    bw.write(prod.getName() + "," + String.format("%.2f", prod.totalPrice()));
                    bw.newLine();
                }
            } catch (Exception e) {
                System.out.println("Erro ao criar arquivo: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
    
}
