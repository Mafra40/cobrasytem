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

import app.model.Frequencia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author WISE
 */
public class FrequenciaTableModel extends AbstractTableModel{
    
    
    private List<Frequencia> linhas;
    private String[] colunas = new String[]{"Matricula", "Nome", "Presença", "Data", "ID"};

    
    private static final int MAT = 0;
    private static final int NOME = 1;
    private static final int PRE = 2;
    private static final int DATA = 3;
    private static final int ID = 4;
    private Frequencia f;

    // Cria um Func sem nenhuma linha
    public FrequenciaTableModel() {
        linhas = new ArrayList<Frequencia>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public FrequenciaTableModel(List<Frequencia> listaDeFrequencia) {
        linhas = new ArrayList<Frequencia>(listaDeFrequencia);
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
            case MAT:
                return String.class;
            case NOME:
                return String.class;
            case PRE:
                return ImageIcon.class;
            case DATA:
                return String.class;
            case ID:
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
        f = linhas.get(rowIndex);

        switch (columnIndex) {
            case MAT:
                return f.getMatricula();
            case NOME:
                return f.getNome();
            case PRE:
                return f.getPrensenca();
            case DATA:
                return f.getDataF();
            case ID:
                return f.getId();

            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        f = linhas.get(rowIndex);

        switch (columnIndex) {
            case MAT:
                f.setMatricula((int) aValue);
                break;
            case NOME:
                f.setNome((String) aValue);
                break;

            case PRE:
                f.setPrensenca((String) aValue);
                break;

            case DATA:
                f.setDataF((String) aValue);
                break;

            case ID:
                f.setId((int) aValue);
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
      /*  c = new Conta();
        c.setId(idConta);
        c.setLancamento(dataE);
        c.setVencimento(dataV);
        c.setValor_total(valor);
        c.setSituacao(situacao);
        linhas.add(c);
        fireTableRowsInserted(row + 1, row + 1);*/
    }

    public void updateRow(int idConta, String dataE, String dataV, Float valor, String situacao, int row) {
       /* c = new Conta();
        c.setId(idConta);
        c.setLancamento(dataE);
        c.setVencimento(dataV);
        c.setValor_total(valor);
        c.setSituacao(situacao);

        fireTableRowsUpdated(row, row);*/

    }

    public void updateRowSituacao(String situacao, int row) {
       /* c = new Conta();
        c.setSituacao(situacao);

        fireTableRowsUpdated(row, row);
*/
    }

    
}
