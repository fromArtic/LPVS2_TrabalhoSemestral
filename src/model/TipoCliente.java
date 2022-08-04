package model;

public enum TipoCliente{
    FISICA("Física"),
    JURIDICA("Jurídica");
    
    private String texto;
    
    private TipoCliente(String t){
        this.texto = t;
    }
    
    public String toString(){
        return this.texto;
    }
}
