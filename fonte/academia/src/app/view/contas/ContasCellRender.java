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

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (value != null) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String texto = label.getText();

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
        // return null;

    }

}
