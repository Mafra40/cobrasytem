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
package app.view.contas;

import app.view.atletaAtividade.*;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author WISE
 */
public class ContasCellRender extends DefaultTableCellRenderer {

    ImageIcon icon;

    ContasCellRender(TableCellRenderer defaultRenderer) {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (column == 3) {
            if (value != null) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setForeground(Color.blue);
                return label;
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }

        }
        
        if (column == 4) {
            if (value != null) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String texto = label.getText();
                label.setForeground(Color.red);
                
                switch (texto) {

                    case "Aberto":

                        icon = new ImageIcon(getClass().getResource("/images/aberto.png"));

                        label.setText("");
                        label.setToolTipText("Aberto");
                        label.setIcon(icon);
                        label.setVerticalAlignment(SwingConstants.CENTER);

                        break;

                    case "Pendente":

                        icon = new ImageIcon(getClass().getResource("/images/vencido.png"));

                        label.setText("");
                        label.setToolTipText("Vencido");
                        label.setIcon(icon);
                        label.setVerticalAlignment(SwingConstants.CENTER);

                        break;

                    case "Pago":

                        icon = new ImageIcon(getClass().getResource("/images/pago.png"));

                        label.setText("");
                        label.setToolTipText("Pago");
                        label.setIcon(icon);
                        label.setVerticalAlignment(SwingConstants.CENTER);

                        break;

                }
                
                return label;
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }

        }
        
        if (column == 5) {
            if (value != null) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                 String texto = label.getText();
                 if (texto.equals("00/00/0000")){
                     label.setText("");
                 }
                label.setForeground(new Color(70, 184, 79));
                return label;
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }

        }

        if (column == 7) {

            if (value != null) {
                JLabel label2 = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String texto = label2.getText();

                switch (texto) {

                    case "Aberto":

                        icon = new ImageIcon(getClass().getResource("/images/aberto.png"));

                        label2.setText("");
                        label2.setToolTipText("Aberto");
                        label2.setIcon(icon);
                        label2.setVerticalAlignment(SwingConstants.CENTER);

                        break;

                    case "Pendente":

                        icon = new ImageIcon(getClass().getResource("/images/vencido.png"));

                        label2.setText("");
                        label2.setToolTipText("Vencido");
                        label2.setIcon(icon);
                        label2.setVerticalAlignment(SwingConstants.CENTER);

                        break;

                    case "Pago":

                        icon = new ImageIcon(getClass().getResource("/images/pago.png"));

                        label2.setText("");
                        label2.setToolTipText("Pago");
                        label2.setIcon(icon);
                        label2.setVerticalAlignment(SwingConstants.CENTER);

                        break;

                }

                return label2;

            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }

        }

        // return null;
        return null;

    }

}
