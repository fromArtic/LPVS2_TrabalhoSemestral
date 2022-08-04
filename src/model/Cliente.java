package model;

public class Cliente{
    private Long id;
    private String nome;
    private String cpf;
    private TipoCliente pessoa;
    private String data;
    
    public Cliente(Long i,String n, String c, TipoCliente p, String d){
        this.nome = n;
        this.cpf = c;
        this.pessoa = p;
        this.data = d;
        this.id = i;
    }
    
    //Getters/Setters
    public Long getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public TipoCliente getPessoa(){
        return pessoa;
    }
    public void setPessoa(TipoCliente pessoa){
        this.pessoa = pessoa;
    }
    public String getData(){
        return data;
    }
    public void setData(String data){
        this.data = data;
    }
    
    //Impressão
    public String toString(){
        return  id + ";" + nome + ";" + cpf + ";" + pessoa.name() + ";" + data;
    }
}
