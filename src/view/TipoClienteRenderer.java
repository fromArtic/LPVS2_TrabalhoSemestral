package view;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import model.TipoCliente;

public class TipoClienteRenderer implements TableCellRenderer{
    private JComboBox combo;
    
    public TipoClienteRenderer(){
        combo = new JComboBox(TipoCliente.values());
    }

    //Retorna o componente JComboBox junto ao respectivo valor carregado na tabela
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        combo.setSelectedItem(value);
        return combo;
    }
}