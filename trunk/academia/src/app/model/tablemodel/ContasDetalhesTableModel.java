/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.tablemodel;

import app.model.contadetalhes.ContaDetalhes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author MafraWise
 */
public class ContasDetalhesTableModel extends AbstractTableModel {

    private List<ContaDetalhes> linhas;
    private String[] colunas = new String[]{"ID", "Nome", "Valor", "Conta"};

    private static final int ID = 0;
    private static final int NOME = 1;
    private static final int VALOR = 2;
    private static final int CONTA = 3;
    private ContaDetalhes c;

    // Cria um Func sem nenhuma linha
    public ContasDetalhesTableModel() {
        linhas = new ArrayList<ContaDetalhes>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public ContasDetalhesTableModel(List<ContaDetalhes> listaDeContas) {
        linhas = new ArrayList<ContaDetalhes>(listaDeContas);
    }

    public void setList(List<ContaDetalhes> listaDeContas) {
        linhas = new ArrayList<ContaDetalhes>(listaDeContas);
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
            case CONTA:
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
        ContaDetalhes c = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return c.getId();
            case NOME:
                return c.getNome();
            case VALOR:
                return c.getValor();
            case CONTA:
                return c.getId_contas_receber();

            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        ContaDetalhes c = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                c.setId((int) aValue);
                break;

            case NOME:
                c.setNome((String) aValue);
                break;

            case VALOR:
                c.setValor((float) aValue);
                break;

            case CONTA:
                c.setId_contas_receber((int) aValue);
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

    public void addRow(int idConta, String nome, Float valor, int conta, int row) {
        c = new ContaDetalhes();
        c.setId(idConta);
        c.setNome(nome);
        c.setValor(valor);
        c.setId_contas_receber(conta);
        linhas.add(c);
        fireTableRowsInserted(row + 1, row + 1);
    }
    
    public void addRowNomeValor(int idConta, String nome, Float valor, int row) {
        c = new ContaDetalhes();
        c.setId(idConta);
        c.setNome(nome);
        c.setValor(valor);
        linhas.add(c);
        fireTableRowsInserted(row + 1, row + 1);
    }

    public void updateRow(int idConta, int mat, String nome, Float valor, int conta, int row) {
        c = new ContaDetalhes();
        c.setId(idConta);
        c.setNome(nome);
        c.setValor(valor);
        c.setId_contas_receber(conta);

        fireTableRowsUpdated(row, row);

    }

    public void updateRowSituacao(String situacao, int row) {
        c = new ContaDetalhes();
        c.setSituacao(situacao);

        fireTableRowsUpdated(row, row);

    }

}
