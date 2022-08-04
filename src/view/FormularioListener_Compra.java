package view;

import model.FormaPagamento;

public interface FormularioListener_Compra{
    public void enviarFormulario(String c, String d, String v, FormaPagamento fp); //CPF, data da compra, valor da compra, forma de pagamento
}
