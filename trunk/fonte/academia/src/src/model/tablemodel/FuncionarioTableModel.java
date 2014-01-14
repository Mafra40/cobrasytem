
package src.model.tablemodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import src.model.Funcionario;

public class FuncionarioTableModel extends AbstractTableModel{
    
    //constantes que vão representar as colunas
    private final int CPF = 0;
    private final int NOME = 1;
    private final int BAIRRO = 2;
    private final int TELEFONE = 3;
    
    //Lista dos funcionário que serão exibidos
    private List<Funcionario> funcionarios;
    
    public FuncionarioTableModel(){
        funcionarios = new ArrayList();
    }
    
    public FuncionarioTableModel(List<Funcionario> funcs){
        this();
        funcionarios.addAll(funcs);
    }
    
    @Override
    public int getRowCount(){
         //cada func na lista será uma linha
        return funcionarios.size();
    }
    
    @Override
    public String getColumnName(int coluna){
        //qual nome da coluna
        if(coluna == NOME){
            return "Nome";
        }else if(coluna == CPF) {
            return "Cpf";
        }else if (coluna == BAIRRO){
            return "Bairro";
        }
        else if (coluna == TELEFONE){
            return "Telefone";
        }
        return "";
    }
    
   @Override
    public Class getColumnClass(int colunaIndex) {
        //retorna a classe que representa a coluna
        if (colunaIndex == NOME) {
            return String.class;
        } else if (colunaIndex == CPF) {
            return String.class;
        } else if (colunaIndex == BAIRRO) {
            return String.class;
        }
        else if (colunaIndex == TELEFONE) {
            return String.class;
        }
        return String.class;
    }
    
   @Override
    public Object getValueAt(int linhaIndex, int colunaIndex){
        Funcionario f = funcionarios.get(linhaIndex);
        
        if (colunaIndex == NOME){
            return f.getNome();
        }else if (colunaIndex == CPF) {
            return f.getCpf();
        }else if (colunaIndex == BAIRRO){
            return f.getBairro();
        }else if (colunaIndex == TELEFONE){
            return f.getTelefone();
        }
        
        return "";
        
    }
    
    
    @Override
    public void setValueAt(Object aValor, int linhaIndex , int colunaIndex){
        Funcionario f = funcionarios.get(linhaIndex); //pega o func da linha
        
        //escolhe o que vai ser alterado
        if (colunaIndex == NOME ){
            f.setNome(aValor.toString());
        }else if (colunaIndex == CPF){
            f.setCep(aValor.toString());
        }else if (colunaIndex == BAIRRO){
            f.setBairro(aValor.toString());
        }else if (colunaIndex == TELEFONE){
            f.setTelefone(aValor.toString());
        }
        
        //avisa quando os dados mudar.
        fireTableDataChanged();
    }
    
    
    @Override
    public boolean isCellEditable(int linhaIndex, int colunaIndex) {
        //no nosso caso todas vão ser editáveis, entao retorna true pra todas
        return false;
    }

    @Override
    public int getColumnCount() {
        return funcionarios.size();
    }
    
    
    
    ////////////////////
    
    public void inserir(Funcionario p) {
    funcionarios.add(p);
 
    fireTableDataChanged();
}
 
public void excluir(int pos) {
    funcionarios.remove(pos);
 
    fireTableDataChanged();
}
 
public void excluir(Funcionario p) {
    funcionarios.remove(p);
 
    fireTableDataChanged();
}
 
public void ordenarPorNome() {
    //ordena pelo nome
    Collections.sort(funcionarios, new Comparator<Funcionario>() {
 
        @Override
        public int compare(Funcionario o1, Funcionario o2) {
            return o1.getNome().compareTo(o2.getNome());
        }
    });
 
    //avisa que a tabela foi alterada
    fireTableDataChanged();
}
 

 
public void misturar() {
    //mistura a lista
    Collections.shuffle(funcionarios);
 
    //avisa que a tabela foi alterada
    fireTableDataChanged();
}
 
public Funcionario getProduto(int pos) {
    if (pos < 0 || pos >= funcionarios.size()) {
        return null;
    }
 
    return funcionarios.get(pos);
}
    
    
    
    
}
