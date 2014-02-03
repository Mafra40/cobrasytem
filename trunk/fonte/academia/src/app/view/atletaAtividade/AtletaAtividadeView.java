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
package app.view.atletaAtividade;

import app.controller.AtividadeController;
import app.view.atleta.AtletaView;

/**
 *
 * @author WISE
 */
public class AtletaAtividadeView extends javax.swing.JDialog {

    public static int atletaId;
    public static int atividadeId;
    public static String nomeAtividade;

    public int linhaSelecionada;
    private AtividadeController ac;

    /**
     * Creates new form AtletaAtividadeView
     */
    public AtletaAtividadeView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        AtividadeController ac = new AtividadeController();
        atletaAtividadeTabela.setModel(ac.listarAtletaAtividade(AtletaView.matricula));
        atletaId = ac.recuperaIdAtleta(AtletaView.matricula);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        atletaAtividadeTabela = new javax.swing.JTable();
        incluirBt = new javax.swing.JButton();
        removerBt = new javax.swing.JButton();
        fecharBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Atividade do atleta");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        atletaAtividadeTabela.setModel(new javax.swing.table.DefaultTableModel(
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
        atletaAtividadeTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atletaAtividadeTabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(atletaAtividadeTabela);

        incluirBt.setText("Incluir");
        incluirBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incluirBtActionPerformed(evt);
            }
        });

        removerBt.setText("Remover");
        removerBt.setEnabled(false);
        removerBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerBtActionPerformed(evt);
            }
        });

        fecharBt.setText("Fechar");
        fecharBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(removerBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(incluirBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fecharBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(incluirBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removerBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fecharBt)))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Definir Atividade", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecharBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharBtActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_fecharBtActionPerformed

    private void incluirBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incluirBtActionPerformed
        AtletaAtividadeForm af = new AtletaAtividadeForm(null, true);
        af.setVisible(true);
    }//GEN-LAST:event_incluirBtActionPerformed

    private void removerBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerBtActionPerformed
        ac = new AtividadeController();
        
        if (ac.removeAtividadeAtleta(nomeAtividade, linhaSelecionada) == true) {
           removerBt.setEnabled(false);
        }
    }//GEN-LAST:event_removerBtActionPerformed

    private void atletaAtividadeTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atletaAtividadeTabelaMouseClicked
        if (atletaAtividadeTabela.getModel().getRowCount() > 0) {
            linhaSelecionada = atletaAtividadeTabela.getSelectedRow();
            int coluna = atletaAtividadeTabela.getSelectedColumn();

            nomeAtividade = (String) atletaAtividadeTabela.getModel().getValueAt(linhaSelecionada, 0);

            removerBt.setEnabled(true);
        }
    }//GEN-LAST:event_atletaAtividadeTabelaMouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AtletaAtividadeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtletaAtividadeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtletaAtividadeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtletaAtividadeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AtletaAtividadeView dialog = new AtletaAtividadeView(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTable atletaAtividadeTabela;
    private javax.swing.JButton fecharBt;
    private javax.swing.JButton incluirBt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton removerBt;
    // End of variables declaration//GEN-END:variables
}