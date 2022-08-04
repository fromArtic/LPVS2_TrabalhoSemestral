package view;

import model.TipoCliente;

public interface FormularioListener_Cliente{
    public void enviaFormulario(String n, String c,TipoCliente p, String d); //Nome, CPF, pessoa física ou jurídica, data de nascimento
}
