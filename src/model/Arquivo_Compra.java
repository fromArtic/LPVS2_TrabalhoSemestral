package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Arquivo_Compra{
    private int MAX = 100; //Máximo de registros salvos
    private Compra[] comprasArquivo;
    
    public Arquivo_Compra(){
        this.comprasArquivo = new Compra[MAX];
    }

    //Adiciona compra ao vetor ao enviar formulário
    public boolean adicionarCompra(Compra c){
        for(int i = 0; i < MAX; i++){
            if(comprasArquivo[i] == null){
                comprasArquivo[i] = c;
                return true;
            }
        }
        return false;
    }
    
    //Método para excluir compra da tabela
    public boolean excluirCompra(long id){
        for(int i = 0; i < MAX; i++){
            if(comprasArquivo[i] != null){
                if(comprasArquivo[i].getId() == id){
                    comprasArquivo[i] = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    //Método para exportar arquivo
    public boolean salvarArquivo(String nomeArquivo) throws FileNotFoundException{
        File f = new File(nomeArquivo);
        PrintWriter pw = new PrintWriter(f);
        
        for(int i = 0; i < MAX; i++){
            if(comprasArquivo[i] != null){
                System.out.println(comprasArquivo[i].toString());
                pw.println(comprasArquivo[i].toString());
            }
        }
        
        pw.close();
        return true;
    }
    
    //Método para importar arquivo
    public Compra[] carregarArquivo(String nomeArquivo) throws FileNotFoundException{
        File f = new File(nomeArquivo);
        Scanner sc = new Scanner(f);
        
        this.comprasArquivo = new Compra[MAX];
        
        while(sc.hasNextLine()){
            String linha = sc.nextLine();
            String[] compraArquivo = linha.split(";");
            
            Compra c = new Compra(compraArquivo[0], //CPF
                    compraArquivo[1], //Data da compra
                    compraArquivo[2], //Valor da compra
                    FormaPagamento.valueOf(compraArquivo[3]), //Forma de pagamento
                    Long.parseLong(compraArquivo[4])); //ID

            this.adicionarCompra(c);
        }
        
        return comprasArquivo;
    }
    
    //Getter
    public Compra[] getCompras(){
        return this.comprasArquivo;
    }
}
