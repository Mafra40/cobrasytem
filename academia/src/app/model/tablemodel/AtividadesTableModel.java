package app.model.tablemodel;

import app.model.Atividade;
import app.model.AtletaAtividade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * TABELA DE ATIVIDADES NA PARTE DE MOVIMENTAÇÃO
 *
 * @author WISE
 */
public class AtividadesTableModel extends AbstractTableModel {

    private List<AtletaAtividade> linhas;
    private String[] colunas = new String[]{"ID", "Nome", "Valor"};

    private static final int ID = 0;
    private static final int NOME = 1;
    private static final int VALOR = 2;
    

    // Cria um Func sem nenhuma linha
    public AtividadesTableModel() {
        linhas = new ArrayList<AtletaAtividade>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public AtividadesTableModel(List<AtletaAtividade> listaDeAtividades) {
        linhas = new ArrayList<AtletaAtividade>(listaDeAtividades);
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
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return String.class;
            case NOME:
                return String.class;
                 case VALOR:
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
       AtletaAtividade a = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return a.getAtividadeId();
            case NOME:
                return a.getNomeAtividade();
                case VALOR:
                return a.getValorAtividade();

            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        AtletaAtividade a = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                a.setAtividadeId((int) aValue);
                break;
            case NOME:
                a.setNomeAtividade((String) aValue);
                break;
                
                case VALOR:
                a.setValorAtividade((float) aValue);
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

    public void removeAll() {
        for (int i = 0; i < linhas.size(); i++) {
            linhas.removeAll(linhas);
        }
        fireTableDataChanged();
    }

    public void addRow(int id, String nome, Float valor, String ativo, int row) {
       /* AtletaAtividade a = new Atividade();
        a.setId(id);
        a.setNome(nome);
        a.setValor(valor);
        a.setAtivo(ativo);
        linhas.add(a);
        fireTableRowsInserted(row + 1, row + 1);*/
    }

    public void updateRow(int id, String nome, Float valor, String ativo, int row) {
        /*Atividade a = new Atividade();
        a.setId(id);
        a.setNome(nome);
        a.setValor(valor);
        a.setAtivo(ativo);

        fireTableRowsUpdated(row, row);*/

    }

}
