/*
 * 
 */
package libs;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import app.model.funcionario.Funcionario;

/**
 *
 * @author WISE
 */
public class TableModel extends AbstractTableModel {

    public List<Funcionario> linhas;

    private String[] colunas = new String[]{"Nome", "Cpf"};
    private static final int NOME = 0;
    private static final int CPF = 1;

    public TableModel() {
        linhas = new ArrayList<Funcionario>();
    }

    public TableModel(List<Funcionario> listaDeFuncionario) {
        linhas = new ArrayList<Funcionario>(listaDeFuncionario);

    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
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
                func.setEndereco((String) aValue);
                break;
            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
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
    
    
    
    
    // Retorna o sócio referente a linha especificada
public Funcionario getFuncionario(int indiceLinha) {
    return linhas.get(indiceLinha);
}
 
// Adiciona o sócio especificado ao modelo
public void addSocio(Funcionario funcionario) {
    // Adiciona o registro.
    linhas.add(funcionario);
 
    // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() - 1;
 
    // Notifica a mudança.
    fireTableRowsInserted(ultimoIndice, ultimoIndice);
}
 
// Remove o sócio da linha especificada.
public void removeFuncionario(int indiceLinha) {
    // Remove o registro.
    linhas.remove(indiceLinha);
 
    // Notifica a mudança.
    fireTableRowsDeleted(indiceLinha, indiceLinha);
}
 
// Adiciona uma lista de sócios no final da lista.
public void addListaDeSocios(List<Funcionario> funcionario) {
    // Pega o tamanho antigo da tabela, que servirá
    // como índice para o primeiro dos novos registros
    int indice = getRowCount();
 
    // Adiciona os registros.
    linhas.addAll(funcionario);
 
    // Notifica a mudança.
    fireTableRowsInserted(indice, indice + funcionario.size());
}
 
// Remove todos os registros.
public void limpar() {
    // Remove todos os elementos da lista de sócios.
    linhas.clear();
 
    // Notifica a mudança.
    fireTableDataChanged();
}
    

}


//TABELASCROL.setViewportView(TABELA);