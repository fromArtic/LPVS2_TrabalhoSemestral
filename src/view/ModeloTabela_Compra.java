package view;

import model.Compra;
import model.FormaPagamento;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela_Compra extends AbstractTableModel{
    private Compra conteudoCompras[];
    private String categoriaColunas[] = {"ID", "CPF do cliente", "Data da compra", "Valor da compra", "Forma de pagamento"};  //Define a categoria de cada coluna
  
    //Implementa o cabeçalho da tabela com a categoria de cada coluna
    @Override
    public String getColumnName(int column){
        return categoriaColunas[column];
    }
    
    //Retorna o número de linhas preenchidas
    @Override
    public int getRowCount(){
        int contador = 0;
        
        for(int i = 0; i < conteudoCompras.length; i++){
            if(conteudoCompras[i] != null){
                contador++;
            }
        }
        
        return contador;
    }

    //Retorna o número de colunas
    @Override
    public int getColumnCount(){
        return 5; //5 parâmetros: ID, CPF, data da compra, valor da compra e forma de pagamento
    }

    //Retorna o conteúdo da tabela
    @Override
    public Object getValueAt(int row, int col){
        Compra c = conteudoCompras[row];
        if(c != null){
            switch(col){
                case 0: return c.getId();
                case 1: return c.getCpf();
                case 2: return c.getData();
                case 3: return c.getValor();
                case 4: return c.getFormaPagamento();
            }
        }
        return null;
    }

    //Setter ConteudoCompras
    public void setConteudoCompras(Compra[] c){
        this.conteudoCompras = c;
    }

    //Método para alterar informações já inseridas na tabela
    @Override
    public boolean isCellEditable(int row, int col){
        switch(col){
            case 2: return true; //Data da compra
            case 3: return true; //Valor da compra
            case 4: return true; //Forma de pagamento
        }
        return false;
    }

    //Atribui as informações alteradas pelo método acima
    @Override
    public void setValueAt(Object aValue, int row, int col){
        long id = (long) this.getValueAt(row, 0);
        Compra c = null;
        
        for(int i = 0; i < conteudoCompras.length; i++){
            if(conteudoCompras[i] != null && conteudoCompras[i].getId() == id){
                c = conteudoCompras[i];
            }
        }
        
        switch(col){
            case 2:
                c.setData((String)aValue);
                break;
            case 3:
                c.setValor((String)aValue);
                break;
            case 4:
                c.setFormaPagamento((FormaPagamento)aValue);
                break;
            default:
                return;
        }
    }

    //Retorna a classe respectiva de cada coluna da tabela
    @Override
    public Class<?> getColumnClass(int col){
        switch(col){
            case 0: return long.class; //ID
            case 1: return String.class; //CPF
            case 2: return String.class; //Data da compra
            case 3: return String.class; //Valor da compra
            case 4: return FormaPagamento.class; //Forma de pagamento
        }
        return null;
    }
}
