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
package app.view.atividade;

import app.view.atleta.*;
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
public class AtividadeCellRender extends DefaultTableCellRenderer {

    ImageIcon icon;

    AtividadeCellRender(TableCellRenderer defaultRenderer) {

    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (value != null) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String texto = label.getText();

            switch (texto) {
                

                case "S":

                    icon = new ImageIcon(getClass().getResource("/images/tick-small.png"));

                    label.setText("");
                    label.setToolTipText("Ativo");
                    label.setIcon(icon);
                    label.setVerticalAlignment(SwingConstants.CENTER);

                    break;

                case "N":

                    icon = new ImageIcon(getClass().getResource("/images/cross-small.png"));

                    label.setText("");
                    label.setToolTipText("Desativado");
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
