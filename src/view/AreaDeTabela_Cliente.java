package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Cliente;
import model.TipoCliente;

public class AreaDeTabela_Cliente extends JPanel{
    private JTable tabela;
    private ModeloTabela_Cliente mtp;
    private JPopupMenu popup;
    private TabelaListener tl;
   
    public AreaDeTabela_Cliente(){
        mtp = new ModeloTabela_Cliente();
        
        //Inicializa a área de tabela
        tabela = new JTable(mtp);
        //Implementa a área de tabela
        setLayout(new BorderLayout());
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        //Determina a altura dos campos exibidos na tabela
        tabela.setRowHeight(25);
        
        //Renderiza o componente JComboBox na tabela
        tabela.setDefaultRenderer(TipoCliente.class, new TipoClienteRenderer());
        //Permite a alteração do valor contido na JComboBox da tabela
        tabela.setDefaultEditor(TipoCliente.class, new TipoClienteEditor());

        //Implementa o menu pop up
        popup = new JPopupMenu();
        JMenuItem itemExcluir = new JMenuItem("Excluir");
        popup.add(itemExcluir);
        
        //Reconhecimento do ponteiro do mouse
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                int row = tabela.rowAtPoint(e.getPoint());
                tabela.getSelectionModel().addSelectionInterval(row, row);
                
                //Identifica qual botão do mouse foi pressionado
                if(e.getButton() == MouseEvent.BUTTON3){ //Botão direito
                    popup.show(tabela, e.getX(), e.getY()); //Exibe o menu pop up
                }
            }
        });
        
        //Implementa o método de exclusão do item "Excluir" do menu pop up
        itemExcluir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int row = tabela.getSelectedRow(); //Identifica a linha selecionada
                long id = (long) tabela.getValueAt(row, 0); //Recupera o respectivo ID da linha selecionada 
                tl.excluirCadastro(id); //Retorna o ID do cliente a ser excluído
            }
        });
    }
    
    //Manipulação do conteúdo a ser exibido na tabela
    public void setConteudoPessoa(Cliente p[]){
         mtp.setConteudoPessoas(p);
    }
    
    //Atualiza os parâmetros de impressão de cada coluna da tabela
    public void atualizarTabela(){
        mtp.fireTableDataChanged();
    }
    
    //Comunicação com a interface TabelaListener
    public void setTabelaListener(TabelaListener t){
        this.tl = t;
    }
}
