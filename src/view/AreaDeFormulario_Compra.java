package view;

import model.FormaPagamento;
import java.awt.Dimension;
import java.awt.Font;
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

public class AreaDeFormulario_Compra extends JPanel implements ActionListener{
    //CPF do cliente
    JLabel lblCPF;
    JTextField txtCPF;
    //Data da compra
    JLabel lblData;
    JTextField txtData;
    //Valor da compra
    JLabel lblValor;
    JTextField txtValor;
    //Forma de pagamento
    JLabel lblFormaPagamento;
    JComboBox comboFormaPagamento;
    //Botões
    JButton btnSalvar;
    JButton btnLimpar;
    
    FormularioListener_Compra fl;
    
    public AreaDeFormulario_Compra(){  
        //Redefine as dimensões do painel
        Dimension d = getPreferredSize(); //Dimensões iniciais recomendadas
        d.width = 400;
        setPreferredSize(d);
        
        //Define as bordas do painel
        Border bordaExterna = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border bordaInterna = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED),"Cadastro de Compras");
        setBorder(BorderFactory.createCompoundBorder(bordaExterna, bordaInterna));
        
        //CPF
        lblCPF = new JLabel("CPF do cliente: ");
        txtCPF = new JTextField(15);
        //Data
        lblData = new JLabel("Data da compra: ");
        txtData= new JTextField(15);
        //Valor
        lblValor = new JLabel("Valor da compra (R$): ");
        txtValor = new JTextField(15);
        //Forma de pagamento
        lblFormaPagamento = new JLabel("Forma de pagamento: ");
        comboFormaPagamento = new JComboBox();
        DefaultComboBoxModel modeloComboPagamento = new DefaultComboBoxModel(FormaPagamento.values());
        comboFormaPagamento.setModel(modeloComboPagamento);
        comboFormaPagamento.setSelectedItem(FormaPagamento.CARTAODV); //Define a escolha padrão
        //Botões
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 13));
        btnLimpar.setFont(new Font("Arial", Font.BOLD, 13));
        btnSalvar.addActionListener(this);
        btnLimpar.addActionListener(this);
        
        //Define o posicionamento dos componentes no painel
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        //Posicionamentos iniciais
        gc.insets = new Insets (55, 0, 5, 0);
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        
        //Posicionamento CPF
        add(lblCPF, gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        add(txtCPF, gc);

        //Posicionamento data
        gc.insets = new Insets (10, 0, 5, 0);
        gc.gridx = 0;
        gc.gridy++; 
        gc.anchor = GridBagConstraints.LINE_END;
        add(lblData, gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;       
        add(txtData, gc);
        
        //Posicionamento valor
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(lblValor, gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;       
        add(txtValor, gc);

        //Posicionamento forma de pagamento
        gc.gridx = 0;
        gc.gridy++;   
        gc.anchor = GridBagConstraints.LINE_END;
        add(lblFormaPagamento, gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;       
        add(comboFormaPagamento, gc);
      
        //Posicionamento botões
        gc.insets = new Insets(30, 5, 0, 10); 
        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.gridwidth = 1;
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.PAGE_START;
        add(btnSalvar,gc); //Salvar
        gc.insets = new Insets(0, 5, 0, 10);
        gc.weighty = 6;
        gc.gridy++;
        add(btnLimpar,gc); //Limpar
    }
    
    //Implementa a interface FormularioListener_Compra
    public void setFormularioListener(FormularioListener_Compra f){
        this.fl = f;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(fl != null){
            JButton botaoClicado = (JButton)e.getSource(); //Pega o endereço do botão clicado
            
            if(botaoClicado == btnLimpar){ //Botão limpar
                this.txtCPF.setText("");
                this.txtData.setText("");
                this.txtValor.setText("");
            }else if(botaoClicado == btnSalvar){ //Botão salvar
                String c = this.txtCPF.getText();
                String d = this.txtData.getText();
                String v = this.txtValor.getText();
                FormaPagamento fp = (FormaPagamento) this.comboFormaPagamento.getSelectedItem(); 
                
                fl.enviarFormulario(c, d, v, fp);
                
                //Limpa os campos do formulário após salvar
                this.txtCPF.setText("");
                this.txtData.setText("");
                this.txtValor.setText("");               
            }
        }
    } 
}
