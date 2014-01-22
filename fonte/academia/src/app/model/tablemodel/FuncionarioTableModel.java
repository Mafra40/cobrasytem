package app.model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import app.model.Funcionario;
import com.sun.org.apache.xpath.internal.functions.FuncBoolean;

 public class FuncionarioTableModel extends AbstractTableModel {

   private List<Funcionario> linhas;
  private String[] colunas = new String[]{"Nome", "CPF"};

    private static final int NOME = 0;
    private static final int CPF = 1;

    // Cria um Func sem nenhuma linha
    public   FuncionarioTableModel() {
        linhas = new ArrayList<Funcionario>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public FuncionarioTableModel(List<Funcionario> listaDeFuncionarios) {
        linhas = new ArrayList<Funcionario>(listaDeFuncionarios);
    }

    @Override
     public  int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case NOME:
                return String.class;
            case CPF:
                return String.class;
            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        Funcionario func = linhas.get(rowIndex);

        switch (columnIndex) {
            case NOME:
                return func.getNome();
            case CPF:
                return func.getCpf();
            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        Funcionario func = linhas.get(rowIndex);

        switch (columnIndex) {
            case NOME:
                func.setNome((String) aValue);
                break;
            case CPF:
                func.setCpf((String) aValue);
                break;
            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }
    
    
 public void removeRow(int row) {
    linhas.remove(row);
    fireTableRowsDeleted(row, row);
}
   
public void  addRow(String nome, String cpf, int row){
    Funcionario func = new Funcionario();
    func.setNome(nome);
    func.setCpf(cpf);
    linhas.add(func);
    fireTableRowsInserted(row+1, row+1);
}

public void updateRow(String nome, String cpf, int row){
    Funcionario func = new Funcionario();
    func.setNome(nome);
    func.setCpf(cpf);
    
    
    fireTableRowsUpdated(row, row);
    
}

}
