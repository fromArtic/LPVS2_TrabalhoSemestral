package view;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import model.FormaPagamento;

public class FormaPagamentoRenderer implements TableCellRenderer{
    private JComboBox combo;
    
    public FormaPagamentoRenderer(){
        combo = new JComboBox(FormaPagamento.values()); 
    }

    //Retorna o componente JComboBox junto ao respectivo valor carregado na tabela
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){
        combo.setSelectedItem(value);
        return combo;
    }
}
