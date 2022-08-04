package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import model.TipoCliente;

public class AreaDeFormulario_Cliente extends JPanel implements ActionListener{
    //Nome
    JLabel lblNome;
    JTextField txtNome;
    //CPF
    JLabel lblCpf;
    JTextField txtCpf;
    //Tipo de cliente
    JLabel lblTipoCliente;
    JComboBox comboTipoCliente;
    //Data de nascimento
    JLabel lblNasc;
    JTextField txtNasc;
    //Botões
    JButton btnLimpar;
    JButton btnArmazenar;
    
    FormularioListener_Cliente fl;

    public AreaDeFormulario_Cliente(){
        //Redefine as dimensões do painel
        Dimension d = getPreferredSize(); //Dimensões iniciais recomendadas
        d.width = 295;
        setPreferredSize(d);

        //Define as bordas do painel
        Border bordaExterna = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border bordaInterna = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED),"Cadastro de Clientes");
        setBorder(BorderFactory.createCompoundBorder(bordaExterna, bordaInterna));

        //Nome
        lblNome = new JLabel("Nome: ");
        txtNome = new JTextField(13);
        //CPF
        lblCpf = new JLabel("CPF: ");
        txtCpf = new JTextField(13);
        //Tipo de cliente
        lblTipoCliente = new JLabel("< O cliente é uma pessoa...");
        comboTipoCliente = new JComboBox();
        DefaultComboBoxModel modeloComboPessoa = new DefaultComboBoxModel(TipoCliente.values());
        comboTipoCliente.setModel(modeloComboPessoa);
        comboTipoCliente.setSelectedIndex(0);
        //Data de nascimento
        lblNasc = new JLabel("Data de nascimento: ");
        txtNasc = new JTextField(13);
        //Botões
        btnLimpar = new JButton ("Limpar");
        btnLimpar.addActionListener(this);
        btnArmazenar = new JButton ("Armazenar");
        btnArmazenar.addActionListener(this);
        
        //Define o posicionamento dos componentes no painel
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        //Posicionamentos iniciais
        gc.insets = new Insets (15, 0, 0, 0);
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        
        //Posicionamento nome
        add(lblNome,gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(txtNome,gc);
        
        //Posicionamento CPF
        gc.insets = new Insets (5, 0, 0, 0);
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(lblCpf,gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(txtCpf,gc);
        
        //Posicionamento tipo de cliente
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(comboTipoCliente,gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(lblTipoCliente,gc);
        
        //Posicionamento data de nascimento
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(lblNasc,gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(txtNasc,gc);
        
        //Botões
        gc.gridx = 0;
        gc.gridy++;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.gridwidth = 1;
        gc.insets = new Insets (20, 10, 0, 5);
        add(btnLimpar, gc);
        gc.gridx = 1;
        add(btnArmazenar,gc);
    }
    
    //Implementa a interface FormularioListener_Cliente
    public void setFormularioListener(FormularioListener_Cliente f){
        this.fl=f;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(fl != null){
            JButton botaoClicado = (JButton)e.getSource(); //Pega o endereço do botão clicado
            
            if(botaoClicado == btnLimpar){ //Botão limpar
                this.txtNome.setText("");
                this.txtCpf.setText("");
                this.txtNasc.setText("");
            }else if(botaoClicado == btnArmazenar){
                String n = this.txtNome.getText();
                String c = this.txtCpf.getText();
                TipoCliente p = (TipoCliente) this.comboTipoCliente.getSelectedItem();
                String d = (String) this.txtNasc.getText();

                this.fl.enviaFormulario(n, c, p, d);
                
                //Limpa os campos do formulário após salvar
                this.txtNome.setText("");
                this.txtCpf.setText("");
                this.txtNasc.setText("");     
            }
        }
    } 
}
