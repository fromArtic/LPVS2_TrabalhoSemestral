package view;

import model.Compra;
import model.FormaPagamento;
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

public class AreaDeTabela_Compra extends JPanel{
    JTable tabela;
    private ModeloTabela_Compra mtc;
    private JPopupMenu popup;
    private TabelaListener tl;
    //private AreaDeFormulario_Consulta areaDeFormulario_cnslt;
    
    public AreaDeTabela_Compra(){
        mtc = new ModeloTabela_Compra();
        
        //Inicializa a área de tabela
        tabela = new JTable(mtc);
        //Implementa a área de tabela
        setLayout(new BorderLayout());
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        //Determina a altura dos campos exibidos na tabela
        tabela.setRowHeight(25);
        
        //Renderiza o componente JComboBox na tabela
        tabela.setDefaultRenderer(FormaPagamento.class, new FormaPagamentoRenderer());
        //Permite a alteração do valor contido na JComboBox da tabela
        tabela.setDefaultEditor(FormaPagamento.class, new FormaPagamentoEditor());
        
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
                tl.excluirCadastro(id); //Retorna o ID da compra a ser excluída
            }
        });
        
        //Implementa na tabela a função de consulta
        /*areaDeFormulario_cnslt = new AreaDeFormulario_Consulta();
        tabela.setRowSorter(areaDeFormulario_cnslt.getSorter());*/
    }
    
    //Manipulação do conteúdo a ser exibido na tabela
    public void setConteudoCompras(Compra c[]){
        mtc.setConteudoCompras(c);
    }
    
    //Atualiza os parâmetros de impressão de cada coluna da tabela
    public void atualizarTabela(){
        mtc.fireTableDataChanged();
    }
    
    //Comunicação com a interface TabelaListener
    public void setTabelaListener(TabelaListener t){
        this.tl = t;
    }
}
