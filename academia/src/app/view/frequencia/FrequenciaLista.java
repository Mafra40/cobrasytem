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
package app.view.frequencia;

import app.controller.FrequenciaController;
import conf.Global;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author WISE
 */
public class FrequenciaLista extends javax.swing.JDialog {

    private FrequenciaController fc;

    public static int idAtividade;
    public static String nomeAtividade;

    /**
     * Creates new form frequenciaLista
     */
    public FrequenciaLista(java.awt.Frame parent, boolean modal, int id, String nome) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        idAtividade = id;
        nome = nomeAtividade;

        popularTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        frequenciaTabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fecharBt = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        prensentesLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        faltantesLabel = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Frequência");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        frequenciaTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(frequenciaTabela);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Lista de atletas ativos.");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText(dateFormat.format(date));

        jLabel3.setText("Data:");

        fecharBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-cross.png"))); // NOI18N
        fecharBt.setText("Fechar");
        fecharBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharBtActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Total:");

        totalLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        totalLabel.setText("jLabel5");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Presentes:");

        prensentesLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        prensentesLabel.setForeground(new java.awt.Color(0, 51, 255));
        prensentesLabel.setText("jLabel6");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Faltantes:");

        faltantesLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        faltantesLabel.setForeground(new java.awt.Color(255, 0, 0));
        faltantesLabel.setText("jLabel7");

        titulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo.setText("nenhuma");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(prensentesLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(faltantesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fecharBt))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel2))
                                .addComponent(jLabel1))
                            .addGap(226, 226, 226)
                            .addComponent(titulo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titulo)
                        .addGap(20, 20, 20)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecharBt)
                    .addComponent(jLabel4)
                    .addComponent(totalLabel)
                    .addComponent(jLabel5)
                    .addComponent(prensentesLabel)
                    .addComponent(jLabel6)
                    .addComponent(faltantesLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecharBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharBtActionPerformed
        this.setVisible(false);
        idAtividade = 0;
        this.dispose();
    }//GEN-LAST:event_fecharBtActionPerformed

    public void popularTabela() {
        fc = new FrequenciaController();

        frequenciaTabela.setModel(fc.listaFrequencia(idAtividade));

        frequenciaTabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        frequenciaTabela.getColumnModel().getColumn(1).setPreferredWidth(250);
        frequenciaTabela.getColumnModel().getColumn(2).setPreferredWidth(20);

        TableCellRenderer defaultRenderer = frequenciaTabela.getDefaultRenderer(Object.class);
        TableCellRenderer r = new FrequenciaCellRender(defaultRenderer);

        frequenciaTabela.getColumnModel().getColumn(2).setCellRenderer(r);

        frequenciaTabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        frequenciaTabela.getColumnModel().getColumn(3).setPreferredWidth(5);

        int total = frequenciaTabela.getModel().getRowCount();
        totalLabel.setText(String.valueOf(total));

        int presentes = 0, faltantes = 0;
        for (int i = 0; i < frequenciaTabela.getModel().getRowCount(); i++) {
            if (frequenciaTabela.getModel().getValueAt(i, 2).equals("P")) {
                presentes++;
            } else {
                faltantes++;
            }

        }

        prensentesLabel.setText(String.valueOf(presentes));
        faltantesLabel.setText(String.valueOf(faltantes));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (Global.TEMA.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrequenciaLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrequenciaLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrequenciaLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrequenciaLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrequenciaLista dialog = new FrequenciaLista(new javax.swing.JFrame(), true, idAtividade, nomeAtividade);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel faltantesLabel;
    private javax.swing.JButton fecharBt;
    private javax.swing.JTable frequenciaTabela;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel prensentesLabel;
    public javax.swing.JLabel titulo;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
