package model;

public class Compra{
    private long  id;
    private String cpf;
    private String data;
    private String valor;
    private FormaPagamento formaPagamento;
    
    public Compra(String c, String d, String v, FormaPagamento fp, Long i){
        this.cpf = c;
        this.data = d;
        this.valor = v;
        this.formaPagamento = fp;
        this.id = i;
    }
    
    //Getters/Setters
    public String getCpf(){
        return cpf;
    }
    public String getData(){
        return data;
    }
    public void setData(String data){
        this.data = data;
    }
    public String getValor(){
        return valor;
    }
    public void setValor(String valor){
        this.valor = valor;
    }
    public FormaPagamento getFormaPagamento(){
        return formaPagamento;
    }
    public void setFormaPagamento(FormaPagamento formaPagamento){
        this.formaPagamento = formaPagamento;
    }
    public long getId(){
        return id;
    }
    
    //Impressão
    public String toString(){
        return cpf + ";" + data + ";" + valor + ";" + formaPagamento.name() + ";" + getId() + ";";
    }
}
