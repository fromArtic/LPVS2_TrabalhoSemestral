package model;

public enum FormaPagamento{
    CARTAOCV("Cartão de crédito à vista"),
    CARTAODV("Cartão de débito à vista"),
    CARTAOCP("Cartão de crédito parcelado"),
    BOLETO("Boleto bancário"),
    DINHEIRO("Dinheiro"),
    OUTROS("Outros");
    
    private String texto;
    
    private FormaPagamento(String t){
        this.texto = t;
    }
    
    public String toString(){
        return this.texto;
    }  
}
