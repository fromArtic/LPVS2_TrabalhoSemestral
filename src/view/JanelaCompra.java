package view;

import controller.Controller_Compra;
import model.Compra;
import model.FormaPagamento;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class JanelaCompra extends JFrame{
    MenuArquivo menu;
    AreaDeFormulario_Compra areaDeFormulario_cmpr;
    AreaDeFormulario_Consulta areaDeFormulario_cnslt;
    AreaDeTabela_Compra areaDeTabela;
    Controller_Compra cc;
    JFileChooser escolhaArquivo;
    
    public JanelaCompra(){
        //Define o título da janela
        super("Trabalho Semestral");
        
        //Define as dimensões
        super.setSize(1700, 600);
        
        //Define o layout
        super.setLayout(new BorderLayout());
        
        //Chama o método criarMenu
        super.setJMenuBar(criarMenu());
        
        //Inicializa o menu arquivo
        menu = new MenuArquivo();
        //Implementa e define a posição do menu arquivo
        super.add(menu, BorderLayout.PAGE_START);
        
        //Inicializa a área de formulário (COMPRA)
        areaDeFormulario_cmpr = new AreaDeFormulario_Compra();
        //Implementa e define a posição da área de formulário (COMPRA)
        super.add(areaDeFormulario_cmpr, BorderLayout.WEST);
        //Comunicação entre o formulário (COMPRA) e a interface FormularioListener_Compra
        areaDeFormulario_cmpr.setFormularioListener(new FormularioListener_Compra(){
            @Override
            public void enviarFormulario(String c, String d, String v, FormaPagamento fp){
                cc.adicionarCompra(c, d, v, fp);
                areaDeTabela.atualizarTabela();
            }
        });
        
        //Inicializa a área de formulário (CONSULTA)
        areaDeFormulario_cnslt = new AreaDeFormulario_Consulta();
        //Implementa e define a posição da área de formulário (CONSULTA)
        super.add(areaDeFormulario_cnslt, BorderLayout.LINE_END);
        
        //Inicializa a área de tabela (COMPRA)
        areaDeTabela = new AreaDeTabela_Compra();
        //Implementa e define a posição da área de tabela (COMPRA)
        super.add(areaDeTabela, BorderLayout.CENTER);
        //Comunicação entre a tabela (COMPRA) e a interface TabelaListener
        areaDeTabela.setTabelaListener(new TabelaListener(){
            @Override
            public void excluirCadastro(long id){ //Método para excluir cadastro da tabela
                boolean retorno = cc.excluirCompra(id);
                if(retorno){ //Remoção sucedeu
                    JOptionPane.showMessageDialog(JanelaCompra.this, "Compra removida com sucesso.", "Excluir compra", JOptionPane.INFORMATION_MESSAGE);
                    areaDeTabela.atualizarTabela();
                }else{ //Remoção fracassou
                    JOptionPane.showMessageDialog(JanelaCompra.this, "Ocorreu um erro durante a remoção da compra.", "Excluir compra", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //Inicializa Controller_Compra (serve p/ comunicação com a classe Compra)
        cc = new Controller_Compra();
        //Inicializa escolhaArquivo (serve p/ se utilizar o arquivo importado)
        escolhaArquivo = new JFileChooser();
        
        //Interação do menu arquivo com a área de tabela
        menu.setTextoListener(new TextoListener(){
            @Override
            public void enviarTexto(String s){
                switch(s){
                    case "Salvar":
                        if(escolhaArquivo.getSelectedFile() == null){ //Salvando o arquivo pela primeira vez
                            int ret = escolhaArquivo.showSaveDialog(JanelaCompra.this);
                            if(ret == JFileChooser.APPROVE_OPTION){
                                System.out.println(escolhaArquivo.getSelectedFile());
                                salvarCompras();
                            }
                        }else{ //Salvando pelas vezes subsequentes
                            salvarCompras();
                        }
                        break;
                    case "Carregar":
                        if(escolhaArquivo.getSelectedFile() == null){ //Abrindo o arquivo pela primeira vez
                            int ret = escolhaArquivo.showOpenDialog(JanelaCompra.this);
                            if(ret == JFileChooser.APPROVE_OPTION){
                                System.out.println(escolhaArquivo.getSelectedFile());
                                carregarCompras();
                            }
                        }else{ //Abrindo pelas vezes subsequentes
                            carregarCompras();
                        }
                        break;
                }
            }
        });
        
        //Manipulação do conteúdo a ser exibido na tabela (COMPRA)
        areaDeTabela.setConteudoCompras(cc.getCompra());
        
        //Exibe o JFrame
        super.setVisible(true);
        //Encerra a operação ao fechar o JFrame
        super.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
    //Configurações da barra de menu
    private JMenuBar criarMenu(){
        //Inicializa a barra de menu
        JMenuBar barraDeMenu = new JMenuBar();
        
        //Registro das opções do menu Arquivo
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem importarArquivo = new JMenuItem("Importar arquivo ...");
        JMenuItem exportarArquivo = new JMenuItem("Exportar arquivo ...");
        JMenuItem sair = new JMenuItem("Sair");
        
        //Implementação do menu Arquivo
        barraDeMenu.add(menuArquivo);
        menuArquivo.add(importarArquivo);
        menuArquivo.add(exportarArquivo);
        menuArquivo.addSeparator(); //Linha de separação
        menuArquivo.add(sair);
        
        //Importar arquivo
        importarArquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = escolhaArquivo.showOpenDialog(JanelaCompra.this);

                if(ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                    carregarCompras();
                }
            }
        });
        
        //Exportar arquivo
        exportarArquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = escolhaArquivo.showSaveDialog(JanelaCompra.this);

                if (ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                    salvarCompras();
                }
            }
        });
        
        //Confirmação de encerramento do programa
        sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = JOptionPane.showConfirmDialog(JanelaCompra.this, "Encerrar operação?", "Confirmação de encerramento", JOptionPane.YES_NO_OPTION);
                
                if(ret == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        
        //Teclas de atalho
        menuArquivo.setMnemonic(KeyEvent.VK_A); //Alt + A: abrir o menu Arquivo
        sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)); //Ctrl + X: encerrar o programa
        
        return barraDeMenu;
    }
    
    //Abrir arquivo (COMPRA)
    private void carregarCompras(){
        try{
            Compra[] c = cc.carregarArquivo(escolhaArquivo.getSelectedFile().getAbsolutePath());
            areaDeTabela.setConteudoCompras(c);
            areaDeTabela.atualizarTabela();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(JanelaCompra.this, "Não foi possível carregar o arquivo.", "Erro ao carregar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Salvar arquivo (COMPRA)
    private void salvarCompras(){
        try{
            cc.salvarArquivo(escolhaArquivo.getSelectedFile().getAbsolutePath());
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(JanelaCompra.this, "Não foi possível salvar o arquivo.", "Erro ao salvar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
