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

import app.model.Atividade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author WISE
 */
public class AtividadeTableModel extends AbstractTableModel {

    private List<Atividade> linhas;
    private String[] colunas = new String[]{"Id","Nome", "Valor", "Ativo"};

    private static final int ID = 0;
    private static final int NOME = 1;
    private static final int VALOR = 2;
    private static final int ATIVO = 3;

    // Cria um Func sem nenhuma linha
    public AtividadeTableModel() {
        linhas = new ArrayList<Atividade>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public AtividadeTableModel(List<Atividade> listaDeAtividades) {
        linhas = new ArrayList<Atividade>(listaDeAtividades);
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
            case ATIVO:
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
        Atividade a = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return a.getId();
            case NOME:
                return a.getNome();
            case VALOR:
                return a.getValor();
            case ATIVO:
                return a.getAtivo();
            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        Atividade a = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                a.setId((int) aValue);
                break;
            case NOME:
                a.setNome((String) aValue);
                break;
            case VALOR:
                a.setValor((Float) aValue);
                break;
            case ATIVO:
                a.setAtivo((String) aValue);
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
        Atividade a = new Atividade();
        a.setId(id);
        a.setNome(nome);
        a.setValor(valor);
        a.setAtivo(ativo);
        linhas.add(a);
        fireTableRowsInserted(row + 1, row + 1);
    }

    public void updateRow(int id, String nome, Float valor, String ativo, int row) {
        Atividade a = new Atividade();
        a.setId(id);
        a.setNome(nome);
        a.setValor(valor);
        a.setAtivo(ativo);

        fireTableRowsUpdated(row, row);

    }

}
