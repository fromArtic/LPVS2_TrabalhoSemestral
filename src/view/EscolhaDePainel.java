package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class EscolhaDePainel extends JPanel implements ActionListener{
    JLabel lblInstrucao;
    JButton btnCliente;
    JButton btnCompra;
    JanelaCliente jCliente;
    JanelaCompra jCompra;
    
    public EscolhaDePainel(){
        //Redefine as dimensões do painel
        Dimension d = getPreferredSize(); //Dimensões iniciais recomendadas
        d.width = 250;
        setPreferredSize(d);
        
        //Define as bordas do painel
        Border bordaExterna = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border bordaInterna = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        setBorder(BorderFactory.createCompoundBorder(bordaExterna, bordaInterna));
        
        lblInstrucao = new JLabel("SELECIONE UM PAINEL");
        lblInstrucao.setFont(new Font("Arial", Font.BOLD, 13));
        btnCliente = new JButton("Cadastro de clientes");
        btnCliente.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCliente.addActionListener(this);
        btnCompra = new JButton("Cadastro de compras");
        btnCompra.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCompra.addActionListener(this);

        //Define o posicionamento dos componentes no painel
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        //Posicionamentos iniciais
        gc.insets = new Insets (0, 0, 0, -230);
        gc.weightx = 0.5;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.CENTER;
        //Posicionamento instrução
        add(lblInstrucao,gc);
        //Posicionamento cliente
        gc.insets = new Insets (30, 0, 0, 0);
        gc.gridx = 0;
        gc.gridy++;
        add(btnCliente,gc);
        //Posicionamento compra
        gc.gridx = 1;
        add(btnCompra,gc);
        
        //Inicializa e oculta as janelas de registro ao abrir o programa
        jCliente = new JanelaCliente();
        jCliente.setVisible(false);
        jCompra = new JanelaCompra();
        jCompra.setVisible(false);
    }
    
    //Método para impedir as janelas de registro de serem exibidas simultaneamente
    @Override
    public void actionPerformed(ActionEvent e){
        JButton botaoClicado = (JButton)e.getSource(); 
        
        if(botaoClicado == btnCliente){ //Cliente
            jCliente.setVisible(true);
            jCompra.setVisible(false);
        }
        else if (botaoClicado == btnCompra){ //Compra
            jCompra.setVisible(true);
            jCliente.setVisible(false);
        }
    }
}
