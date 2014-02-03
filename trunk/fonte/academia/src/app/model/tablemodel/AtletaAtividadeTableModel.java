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

import app.model.AtletaAtividade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author WISE
 */
public class AtletaAtividadeTableModel extends AbstractTableModel{
    
    
     private List<AtletaAtividade> linhas;
    private String[] colunas = new String[]{"Nome", "Valor", "Ativo"};

   
    private static final int NOME =0;
    private static final int VALOR = 1;
    private static final int ATIVO = 2;
     //private static final int ID = 0;

    // Cria um Func sem nenhuma linha
    public AtletaAtividadeTableModel() {
        linhas = new ArrayList<AtletaAtividade>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public AtletaAtividadeTableModel(List<AtletaAtividade> listaDeAtletaAtividade) {
        linhas = new ArrayList<AtletaAtividade>(listaDeAtletaAtividade);
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
        AtletaAtividade a = linhas.get(rowIndex);

        switch (columnIndex) {
            
            case NOME:
                return a.getNomeAtividade();
            case VALOR:
                return a.getValorAtividade();
            case ATIVO:
                return a.getAtivoAtividade();
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
            
            case NOME:
                a.setNomeAtividade((String) aValue);
                break;
            case VALOR:
                a.setValorAtividade((Float) aValue);
                break;
            case ATIVO:
                a.setAtivoAtividade((String) aValue);
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

    public void addRow( String nome, Float valor, String ativo, int row) {
        AtletaAtividade a = new AtletaAtividade();
       // a.setId(id);
        a.setNomeAtividade(nome);
        a.setValorAtividade(valor);
        a.setAtivoAtividade(ativo);
        linhas.add(a);
        fireTableRowsInserted(row + 1, row + 1);
    }

    public void updateRow(String nome, Float valor, String ativo, int row) {
       AtletaAtividade a = new AtletaAtividade();
       // a.setId(id);
        a.setNomeAtividade(nome);
        a.setValorAtividade(valor);
        a.setAtivoAtividade(ativo);

        fireTableRowsUpdated(row, row);

    }
    
}
