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

import app.model.Atleta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author WISE
 */
public class AtletaTableModel  extends AbstractTableModel{
    
    Atleta a;
    private List<Atleta> linhas;
    private String[] colunas = new String[]{"Matrícula","Nome","RG", "Ativo"};

    private static final int MAT = 0;
    private static final int NOME = 1;
    private static final int RG = 2;
    private static final int ATIVO = 3;

    // Cria um Func sem nenhuma linha
    public AtletaTableModel() {
        linhas = new ArrayList<Atleta>();
    }

    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public AtletaTableModel(List<Atleta> listaDeAtleta) {
        linhas = new ArrayList<Atleta>(listaDeAtleta);
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
            case RG:
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
        a = new Atleta();
        a = linhas.get(rowIndex);

        switch (columnIndex) {
            case MAT:
                return a.getMatricula();
            case NOME:
                return a.getNome();
            case RG:
                return a.getRg();
            case ATIVO:
                return a.getAtivo();
            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       
         a = linhas.get(rowIndex); // Se dé erro. A model não foi pego da tabela.
         
        switch (columnIndex) {
            case MAT:
                a.setMatricula((int) aValue);
                break;
            case NOME:
                a.setNome((String) aValue);
                break;
            case RG:
                a.setRg((String) aValue);
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

    public void addRow(int mat, String nome, String rg, String ativo, int row) {
         Atleta a = new Atleta();
        a.setMatricula(mat);
        a.setNome(nome);
        a.setRg(rg);
        a.setAtivo(ativo);
        linhas.add(a);
        fireTableRowsInserted(row + 1, row + 1);
    }

    public void updateRow(int mat, String nome, String rg, String ativo, int row) {
        Atleta a = new Atleta();
        a.setMatricula(mat);
        a.setNome(nome);
        a.setRg(rg);
        a.setAtivo(ativo);

        fireTableRowsUpdated(row, row);

    }
    
}
