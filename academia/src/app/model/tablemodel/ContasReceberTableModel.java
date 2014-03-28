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
public class ContasReceberTableModel extends AbstractTableModel {

    private List<Conta> linhas;
    private String[] colunas = new String[]{"ID", "Matrícula", "Nome", "Data Emissão", "Vencimento", "Data. Pagamento", "Valor", "Situaçao"};

    private static final int ID = 0;
    private static final int MAT = 1;
    private static final int NOME = 2;
    private static final int DATAE = 3;
    private static final int DATAV = 4;
    private static final int DATAP = 5;
    private static final int VALOR = 6;
    private static final int SITUACAO = 7;
    private Conta c;

    // Cria um Func sem nenhuma linha
    public ContasReceberTableModel() {
        linhas = new ArrayList<Conta>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public ContasReceberTableModel(List<Conta> listaDeContas) {
        linhas = new ArrayList<Conta>(listaDeContas);
    }
    
    public void setList (List<Conta> listaDeContas) {
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
            case MAT:
                return String.class;
            case NOME:
                return String.class;
            case DATAE:
                return String.class;
            case DATAV:
                return String.class;
            case DATAP:
                return String.class;
            case VALOR:
                return String.class;
            case SITUACAO:
                return String.class;
            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        Conta c = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return c.getId();
            case MAT:
                return c.getMatricula();
            case NOME:
                return c.getNome();
            case DATAE:
                return c.getLancamento();
            case DATAV:
                return c.getVencimento();
            case DATAP:
                return c.getDatapago();
            case VALOR:
                return c.getValor_total();
            case SITUACAO:
                return c.getSituacao();

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

            case MAT:
                c.setMatricula((int) aValue);
                break;

            case NOME:
                c.setNome((String) aValue);
                break;
            case DATAE:
                c.setLancamento((String) aValue);
                break;

            case DATAV:
                c.setVencimento((String) aValue);
                break;

            case DATAP:
                c.setDatapago((String) aValue);
                break;

            case VALOR:
                c.setValor_total((float) aValue);
                break;

            case SITUACAO:
                c.setSituacao((String) aValue);
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

    public void addRow(int idConta, int mat, String nome, String dataE, String dataV, String dataP, Float valor, String situacao, int row) {
        c = new Conta();
        c.setId(idConta);
        c.setMatricula(mat);
        c.setNome(nome);
        c.setLancamento(dataE);
        c.setVencimento(dataV);
        c.setDatapago(dataP);
        c.setValor_total(valor);
        c.setSituacao(situacao);
        linhas.add(c);
        fireTableRowsInserted(row + 1, row + 1);
    }

    public void updateRow(int idConta, int mat, String nome, String dataE, String dataV, String dataP, Float valor, String situacao, int row) {
        c = new Conta();
        c.setId(idConta);
        c.setMatricula(mat);
        c.setNome(nome);
        c.setLancamento(dataE);
        c.setVencimento(dataV);
        c.setDatapago(dataP);
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
