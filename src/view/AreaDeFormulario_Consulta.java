package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class AreaDeFormulario_Consulta extends JPanel{
    //CPF
    JLabel lblCPF;
    JTextField txtCPF;
    //Buscar
    JButton btnBuscar;
    //Consulta
    /*private TableRowSorter sorter;
    private TableModel modeloTabela;*/
    
    public AreaDeFormulario_Consulta(){
        //Redefine as dimensões do painel
        Dimension d = getPreferredSize(); //Dimensões iniciais recomendadas
        d.width = 250;
        setPreferredSize(d);
        
        //Define as bordas do painel
        Border bordaExterna = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border bordaInterna = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED),"Consulta");
        setBorder(BorderFactory.createCompoundBorder(bordaExterna, bordaInterna));
        
        //CPF
        lblCPF = new JLabel("CPF do cliente a ser consultado");
        lblCPF.setFont(new Font("Arial", Font.BOLD, 13));
        txtCPF = new JTextField(15);
        txtCPF.setFont(new Font("Arial", Font.PLAIN, 14));
        //Buscar
        btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(100, 40));
        btnBuscar.setFont(new Font("Arial", Font.PLAIN, 18));
        
        //Consulta
        /*modeloTabela = new DefaultTableModel();
        sorter = new TableRowSorter<>(modeloTabela);
        txtCPF.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e){
                search(txtCPF.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                search(txtCPF.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e){
                search(txtCPF.getText());
            }
            public void search(String str){
                if (str.length() == 0){
                    sorter.setRowFilter(null);
                }else{
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });*/
        
        //Define o posicionamento dos componentes no painel
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        //Posicionamentos iniciais
        gc.insets = new Insets (5, 0, 0, 0);
        gc.weightx = 1;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        //Posicionamento CPF
        add(lblCPF, gc);
        gc.gridy++;
        add(txtCPF, gc);
        //Posicionamento buscar
        gc.insets = new Insets (30, 0, 0, 0);
        gc.gridy++;
        add(btnBuscar, gc);
    }

    //Getters
    /*public TableRowSorter getSorter(){
        return sorter;
    }*/
}
