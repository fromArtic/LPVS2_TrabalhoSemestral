package view;

import controller.Controller_Cliente;
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
import model.Cliente;
import model.TipoCliente;

public class JanelaCliente extends JFrame{
    MenuArquivo menu;
    AreaDeFormulario_Cliente areaDeFormulario;
    AreaDeTabela_Cliente areaDeTabela;
    Controller_Cliente cp;
    JFileChooser escolhaArquivo;
    
    public JanelaCliente(){
        //Define o título da janela
        super("Trabalho Semestral");
        
        //Define as dimensões
        super.setSize(1400, 400);
        
        //Define o layout
        super.setLayout(new BorderLayout());
        
        //Chama o método criarMenu
        super.setJMenuBar(criarMenu());

        //Inicializa o menu arquivo
        menu = new MenuArquivo();
        //Implementa e define a posição do menu arquivo
        super.add(menu, BorderLayout.PAGE_START);
        
        //Inicializa a área de formulário
        areaDeFormulario = new AreaDeFormulario_Cliente();
        //Implementa e define a posição da área de formulário
        super.add(areaDeFormulario, BorderLayout.WEST);
        //Comunicação entre o formulário e a interface FormularioListener_Cliente
        areaDeFormulario.setFormularioListener(new FormularioListener_Cliente(){
            @Override
            public void enviaFormulario(String n, String c, TipoCliente p, String d){
                cp.adicionaPessoa(n, c, p, d);
                areaDeTabela.atualizarTabela();
            }
        });
        
        //Inicializa a área de tabela
        areaDeTabela = new AreaDeTabela_Cliente();
        //Implementa e define a posição da área de tabela
        super.add(areaDeTabela, BorderLayout.CENTER);
        //Comunicação entre a tabela e a interface TabelaListener
        areaDeTabela.setTabelaListener(new TabelaListener(){
            @Override
            public void excluirCadastro(long id){ //Método para excluir cadastro da tabela
                boolean retorno = cp.excluirPessoa(id);
                if(retorno){ //Remoção sucedeu
                    JOptionPane.showMessageDialog(JanelaCliente.this, "Cliente removido com sucesso.", "Excluir cliente", JOptionPane.INFORMATION_MESSAGE);
                    areaDeTabela.atualizarTabela();
                }else{ //Remoção fracassou
                    JOptionPane.showMessageDialog(JanelaCliente.this, "Ocorreu um erro durante a remoção do cliente.", "Excluir cliente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Inicializa ControllerCompra (serve p/ comunicação com a classe Compra)
        cp = new Controller_Cliente();
        //Inicializa escolhaArquivo (serve p/ se utilizar o arquivo importado)
        escolhaArquivo = new JFileChooser();
        
        //Interação do menu arquivo com a área de tabela
        menu.setTextoListener(new TextoListener(){
            @Override
            public void enviarTexto(String s){
                switch(s){
                    case "Salvar":
                        if(escolhaArquivo.getSelectedFile() == null){ //Salvando o arquivo pela primeira vez
                            int ret = escolhaArquivo.showSaveDialog(JanelaCliente.this);
                            if(ret == JFileChooser.APPROVE_OPTION){
                                System.out.println(escolhaArquivo.getSelectedFile());
                                salvarPessoa();
                            }
                        }else{ //Salvando pelas vezes subsequentes
                            salvarPessoa();
                        }
                        break;
                    case "Carregar":
                        if(escolhaArquivo.getSelectedFile() == null){ //Abrindo o arquivo pela primeira vez
                            int ret = escolhaArquivo.showOpenDialog(JanelaCliente.this);
                            if(ret == JFileChooser.APPROVE_OPTION){
                                System.out.println(escolhaArquivo.getSelectedFile());
                                carregarPessoa();
                            }
                        }else{ //Abrindo pelas vezes subsequentes
                            carregarPessoa();
                        }
                        break;
                }
            }
        });
        
        //Manipulação do conteúdo a ser exibido na tabela
        areaDeTabela.setConteudoPessoa(cp.getPessoas());
        
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
                int ret = escolhaArquivo.showOpenDialog(JanelaCliente.this);

                if(ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                    carregarPessoa();
                }
            }
        });
        
        //Exportar arquivo
        exportarArquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = escolhaArquivo.showSaveDialog(JanelaCliente.this);

                if (ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                    salvarPessoa();
                }
            }
        });
        
        //Confirmação de encerramento do programa
        sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = JOptionPane.showConfirmDialog(JanelaCliente.this, "Encerrar operação?", "Confirmação de encerramento", JOptionPane.YES_NO_OPTION);
                
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
    private void carregarPessoa(){
        try{
            Cliente [] p = cp.carregarArquivo(escolhaArquivo.getSelectedFile().getAbsolutePath());
            areaDeTabela.setConteudoPessoa(p);
            areaDeTabela.atualizarTabela();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(JanelaCliente.this, "Não foi possível carregar o arquivo.", "Erro ao carregar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Salvar arquivo (COMPRA)
    private void salvarPessoa(){
        try{
            cp.salvarArquivo(escolhaArquivo.getSelectedFile().getAbsolutePath());
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(JanelaCliente.this, "Não foi possível salvar o arquivo.", "Erro ao salvar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
