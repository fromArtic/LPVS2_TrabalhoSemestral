package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class JanelaInicial extends JFrame{
    EscolhaDePainel ae;
    
    public JanelaInicial(){
        //Define o título da janela
        super("Mercadinho");
        
        //Define as dimensões
        super.setSize(450, 200);
        
        //Define o layout
        super.setLayout(new BorderLayout());
        
        //Implementa o painel inicial
        ae = new EscolhaDePainel();
        super.add(ae, BorderLayout.CENTER);

        //Exibe o JFrame
        super.setVisible(true);
        //Encerra a operação ao fechar o JFrame
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
