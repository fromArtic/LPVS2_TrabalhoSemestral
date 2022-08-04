package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Arquivo_Cliente{
    private int MAX = 100; //Máximo de registros salvos
    private Cliente[] pessoaArquivo;
    
    public Arquivo_Cliente(){
        this.pessoaArquivo = new Cliente[MAX];
    }
    
    //Adiciona cliente ao vetor ao enviar formulário
    public boolean adicionarPessoa(Cliente p){
        for(int i = 0; i < MAX; i++){
            if(getPessoaArquivo()[i] == null){
                pessoaArquivo[i] = p;
                return true;
            }
        }
        return false;
    }
    
    //Método para excluir cliente da tabela
    public boolean excluirPessoa (long id){
        for(int i = 0; i < MAX; i++){ 
            if(getPessoaArquivo()[i] != null){
                if(pessoaArquivo[i].getId() == id){
                    pessoaArquivo[i] = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    //Método para exportar arquivo
    public boolean salvarArquivo(String nome) throws FileNotFoundException{
        File f = new File(nome);
        PrintWriter pw = new PrintWriter(f);
        
        for(int i = 0; i < MAX; i++){
            if(getPessoaArquivo()[i]!= null){
                System.out.println(getPessoaArquivo()[i].toString());
                pw.println(getPessoaArquivo()[i].toString());
            }
        }
        
        pw.close();
        return true;
    }
    
    //Método para importar arquivo
    public Cliente[] carregarArquivo(String nome) throws FileNotFoundException{
        File f = new File(nome);
        Scanner sc = new Scanner(f);
        
        this.pessoaArquivo = new Cliente[MAX];
        
        while(sc.hasNextLine()){
            String linha = sc.nextLine();
            String[] pessoaArquivo = linha.split(";");
            
            Cliente p = new Cliente(Long.parseLong(pessoaArquivo[0]),
                    pessoaArquivo[1],
                    pessoaArquivo[2],
                    TipoCliente.valueOf(pessoaArquivo[3]),
                    pessoaArquivo[4]
                    );
            /* Pessoa p = new Pessoa(pessoaArquivo[0],
                    pessoaArquivo[1],
                    PessoaSituacao.valueOf(pessoaArquivo[2]),
                    pessoaArquivo[3],
                    Long.parseLong(pessoaArquivo[4]));*/
            this.adicionarPessoa(p);
        }
        return pessoaArquivo;
    }
    
    //Getters
    public Cliente[] getPessoaArquivo(){
        return pessoaArquivo;
    }
    public Cliente[] getPessoas(){
        return this.getPessoaArquivo();
    }
}
