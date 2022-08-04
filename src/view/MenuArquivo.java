package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class MenuArquivo extends JToolBar implements ActionListener{
    JButton btnSalvar;
    JButton btnCarregar;
    TextoListener tl;

    public MenuArquivo() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        //Salvar
        btnSalvar = new JButton();
        btnSalvar.setIcon(criarIcone("/imagens/Diskette_16x16.png"));
        btnSalvar.setToolTipText("Salvar");
        btnSalvar.addActionListener(this);
        add(btnSalvar);
        
        //Carregar
        btnCarregar = new JButton();
        btnCarregar.setIcon(criarIcone("/imagens/Refresh_16x16.png"));
        btnCarregar.setToolTipText("Carregar");
        btnCarregar.addActionListener(this);
        add(btnCarregar);
    }
    
    //Retorna as imagens dos ícones
    private ImageIcon criarIcone(String caminho){
        URL url = getClass().getResource(caminho);
        ImageIcon img = new ImageIcon(url);
        
        return img;
    }

    public void setTextoListener(TextoListener t){
        this.tl = t;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(tl != null){
            JButton btnClicado = (JButton) e.getSource();

            if(btnClicado == btnSalvar){
                tl.enviarTexto("Salvar");
            }else if(btnClicado == btnCarregar){
                tl.enviarTexto("Carregar");
            }
        }
    }
}
