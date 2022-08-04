package view;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import model.FormaPagamento;

public class FormaPagamentoEditor extends AbstractCellEditor implements TableCellEditor{
    private JComboBox combo;
    
    public FormaPagamentoEditor(){
        this.combo = new JComboBox(FormaPagamento.values());
    }
    
    //Recupera o valor contido na JComboBox
    @Override
    public Object getCellEditorValue(){
        return combo.getSelectedItem();
    }

    //Permite a alteração do valor contido na JComboBox
    @Override
    public boolean isCellEditable(EventObject e){
        return true;
    }

    //Retorna o valor contido na JComboBox
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col){
        combo.setSelectedItem(value);
        return combo;
    }
}
