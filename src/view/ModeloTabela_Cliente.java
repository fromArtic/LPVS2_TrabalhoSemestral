package view;

import javax.swing.table.AbstractTableModel;
import model.Cliente;
import model.TipoCliente;

public class ModeloTabela_Cliente extends AbstractTableModel{
    private Cliente conteudoPessoa[];
    private String nomeColunas[] = {"ID", "Nome", "CPF", "Pessoa", "Data de Nascimento"};
    
    //Implementa o cabeçalho da tabela com a categoria de cada coluna
    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }
    
    //Retorna o número de linhas preenchidas
    @Override
    public int getRowCount(){
        int contador = 0;
        
        for(int i = 0; i < conteudoPessoa.length; i++){
            if(conteudoPessoa[i]!=null){
                contador++;
            }
        }
        
        return contador;
    }
    
    //Retorna o número de colunas
    @Override
    public int getColumnCount(){
        return 5;
    }
    
    //Retorna o conteúdo da tabela
    @Override
    public Object getValueAt(int row, int col){
        Cliente p = conteudoPessoa[row];  
        if(p != null){
            switch(col){
                case 0: return p.getId();
                case 1: return p.getNome();
                case 2: return p.getCpf();
                case 3: return p.getPessoa();
                case 4: return p.getData();
            }
        }
        return null;
    }
    
    //Setter ConteudoPessoas
    public void setConteudoPessoas(Cliente[] p){
        this.conteudoPessoa = p;
    }
    
    //Método para alterar informações já inseridas na tabela
    @Override
    public boolean isCellEditable(int row, int col){
        switch(col){
            case 1: return true;
            case 2: return true;
            case 3: return true;
            case 4: return true;
        }
        return false;
    }

    //Atribui as informações alteradas pelo método acima
    @Override
    public void setValueAt(Object aValue, int row, int col){
        long id = (long)this.getValueAt(row, 0);
        Cliente p = null;
        
        for (int i = 0; i < conteudoPessoa.length; i++){
            if(conteudoPessoa[i] != null && conteudoPessoa[i].getId() == id){
                p = conteudoPessoa[i];
            }
        }
        
        switch(col){
            case 1:
                p.setNome((String) aValue);
                break;
            case 2:
                p.setCpf((String)aValue);
                break;
            case 3:
                p.setPessoa((TipoCliente) aValue);
                break;
            case 4:
                p.setData((String)aValue);
                break;
            default: 
                return;
        }
    }

    //Retorna a classe respectiva de cada coluna da tabela
    @Override
    public Class<?> getColumnClass(int col){
        switch(col){
            case 0: return Long.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return TipoCliente.class;
            case 4: return String.class;
        }
        return null;
    }
}
