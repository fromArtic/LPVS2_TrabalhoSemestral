package controller;

import model.Arquivo_Compra;
import model.Compra;
import model.FormaPagamento;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class Controller_Compra{
    Arquivo_Compra a;
    
    public Controller_Compra(){
        a = new Arquivo_Compra();
    }
    
    //Adiciona compra ao vetor ao enviar formulário (comunicação com classe arquivo)
    public boolean adicionarCompra(String c, String d, String v, FormaPagamento fp){
        long id = System.currentTimeMillis();
        Compra compra = new Compra(c, d, v, fp, id);
        a.adicionarCompra(compra);
        
        JOptionPane.showMessageDialog(null, "Compra efetuada");
        return true;
    }
    
    //Método para excluir compra da tabela (comunicação com classe arquivo)
    public boolean excluirCompra(long id){
        return a.excluirCompra(id);
    }
    
    //Método para exportar arquivo (comunicação com classe arquivo)
    public boolean salvarArquivo(String nome) throws FileNotFoundException{
        a.salvarArquivo(nome);
        return true;
    }
    
    //Método para importar arquivo (comunicação com classe arquivo)
    public Compra[] carregarArquivo(String nome) throws FileNotFoundException{
        return a.carregarArquivo(nome);
    }  
    
    //Recupera cadastros através do Getter da classe Arquivo_Compra
    public Compra[] getCompra(){
        return a.getCompras();
    }
}
