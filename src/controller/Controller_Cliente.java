package controller;

import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import model.Arquivo_Cliente;
import model.Cliente;
import model.TipoCliente;

public class Controller_Cliente{
    Arquivo_Cliente a;
    
    public Controller_Cliente(){
        a = new Arquivo_Cliente();
    }
    
    //Adiciona cliente ao vetor ao enviar formulário (comunicação com classe arquivo)
    public boolean adicionaPessoa (String n, String c, TipoCliente p, String d){
        long id = System.currentTimeMillis();
        Cliente pessoa = new Cliente(id,n, c, p, d);
        a.adicionarPessoa(pessoa);
        
        JOptionPane.showMessageDialog(null, "Cliente registrado");
        return true;
    }
    
    //Método para excluir cliente da tabela (comunicação com classe arquivo)
    public boolean excluirPessoa(long id){
        return a.excluirPessoa(id);
    }
    
    //Método para exportar arquivo (comunicação com classe arquivo)
    public boolean salvarArquivo (String nome) throws FileNotFoundException{
        a.salvarArquivo(nome);
        return true;
    }
    
    //Método para importar arquivo (comunicação com classe arquivo)
     public Cliente[] carregarArquivo (String nome) throws FileNotFoundException{
        return a.carregarArquivo(nome);
    }
    
    //Recupera cadastros através do Getter da classe Arquivo_Cliente
    public Cliente[] getPessoas(){
        return a.getPessoas();
    }
}
