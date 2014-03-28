/*
 * Copyright (c) 2014 WISE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    WISE - initial API and implementation and/or initial documentation
 */

package app.model.tablemodel;

import app.model.conta.Conta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author WISE
 */
public class PendenciasTableModel  extends AbstractTableModel {

    private List<Conta> linhas;
    private String[] colunas = new String[]{"Nº Conta", "Lançamento" , "Vencimento", "Valor"};

    private static final int ID = 0;
    private static final int DATAL = 1;
    private static final int DATAV = 2;
    private static final int VALOR = 3;
    private Conta c;

    // Cria um Func sem nenhuma linha
    public PendenciasTableModel() {
        linhas = new ArrayList<Conta>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public PendenciasTableModel(List<Conta> listaDeContas) {
        linhas = new ArrayList<Conta>(listaDeContas);
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
            case DATAL:
                return String.class;
            case DATAV:
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
        Conta c = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return c.getId();
            case DATAL:
                return c.getLancamento();
            case DATAV:
                return c.getVencimento();
            case VALOR:
                return "R$ " + c.getValor_total();

            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        Conta c = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                c.setId((int) aValue);
                break;
            case DATAL:
                c.setLancamento((String) aValue);
                break;

            case DATAV:
                c.setVencimento((String) aValue);
                break;

            case VALOR:
                c.setValor_total((float) aValue);
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

    public void addRow(int idConta, String dataE, String dataV, Float valor, String situacao, int row) {
        c = new Conta();
        c.setId(idConta);
        c.setLancamento(dataE);
        c.setVencimento(dataV);
        c.setValor_total(valor);
        c.setSituacao(situacao);
        linhas.add(c);
        fireTableRowsInserted(row + 1, row + 1);
    }

    public void updateRow(int idConta, String dataE, String dataV, Float valor, String situacao, int row) {
        c = new Conta();
        c.setId(idConta);
        c.setLancamento(dataE);
        c.setVencimento(dataV);
        c.setValor_total(valor);
        c.setSituacao(situacao);

        fireTableRowsUpdated(row, row);

    }

    public void updateRowSituacao(String situacao, int row) {
        c = new Conta();
        c.setSituacao(situacao);

        fireTableRowsUpdated(row, row);

    }

}
