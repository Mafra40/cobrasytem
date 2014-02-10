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

import app.controller.ContaController;
import app.model.Conta;
import app.model.tablemodel.ContaTableModel;
import app.view.atleta.AtletaView;
import conf.Global;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import libs.Validador;

/**
 *
 * @author WISE
 */
public class ContasAtletasView extends javax.swing.JDialog {

    public static int linhaSelecionada;
    public static int linhaSelecionadaMov;
    public static float valorAtividade;
    private static int idAtividade;
    private static int idMov;
    private static int cliques;
    private ContaController cc;
    private Conta c;
    private TableCellRenderer MyRenderer;
    private static String situacaoMov;
    private ContaTableModel ctm;

    /**
     * Creates new form ContasView
     */
    public ContasAtletasView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         setLocationRelativeTo(null);
        cc = new ContaController();

        tabelaMovimentacao.setModel(cc.listarContas(AtletaView.matricula));

        tabelaMovimentacao.getColumnModel().getColumn(0).setPreferredWidth(10); //ID.
        
        TableCellRenderer defaultRenderer = tabelaMovimentacao.getDefaultRenderer(Object.class);
        TableCellRenderer r = new ContasCellRender(defaultRenderer);
        
        tabelaMovimentacao.getColumnModel().getColumn(4).setCellRenderer(r);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMovimentacao = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        salvarBt = new javax.swing.JButton();
        dadosPagamento = new javax.swing.JPanel();
        dataVencimentoTxt = new javax.swing.JFormattedTextField();
        dataEmissaoTxt = new javax.swing.JFormattedTextField();
        matriculaTxt = new javax.swing.JTextField();
        matriculaLabel = new javax.swing.JLabel();
        valorLabel = new javax.swing.JLabel();
        dataEmissaoLabel = new javax.swing.JLabel();
        dataVencimentoLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaAtividades = new javax.swing.JTable();
        atletaLabel = new javax.swing.JLabel();
        nomeAtletaTxt = new javax.swing.JTextField();
        valorTxt = new javax.swing.JFormattedTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        obsTxt = new javax.swing.JTextArea();
        obsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        contaTxt = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        quitarBt = new javax.swing.JButton();
        removerBt = new javax.swing.JButton();
        fecharBt = new javax.swing.JButton();
        atualizarBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Financeiro");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Movimentação"));

        tabelaMovimentacao.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaMovimentacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMovimentacaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaMovimentacao);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        salvarBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-check.png"))); // NOI18N
        salvarBt.setText("Salvar");
        salvarBt.setEnabled(false);
        salvarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(salvarBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(salvarBt)
        );

        dadosPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do pagamento"));

        try {
            dataVencimentoTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            dataEmissaoTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataEmissaoTxt.setToolTipText("Clique duas vezes para atlerar a data de emissão.");
        dataEmissaoTxt.setEnabled(false);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(date);
        dataEmissaoTxt.setText(data);
        dataEmissaoTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataEmissaoTxtMouseClicked(evt);
            }
        });

        matriculaTxt.setEnabled(false);

        matriculaLabel.setText("Matrícula");

        valorLabel.setText("Valor");

        dataEmissaoLabel.setText("Data. Emissão");

        dataVencimentoLabel.setForeground(new java.awt.Color(255, 51, 51));
        dataVencimentoLabel.setText("Data. Vencimento");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Atividades"));
        jPanel3.setToolTipText("Clique aqui para escolher uma atividade.");

        tabelaAtividades.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaAtividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAtividadesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaAtividades);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        atletaLabel.setText("Atleta");

        nomeAtletaTxt.setEnabled(false);

        valorTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        obsTxt.setColumns(20);
        obsTxt.setRows(5);
        jScrollPane3.setViewportView(obsTxt);

        obsLabel.setText("Observação");

        jLabel1.setText("Conta");

        contaTxt.setEnabled(false);

        javax.swing.GroupLayout dadosPagamentoLayout = new javax.swing.GroupLayout(dadosPagamento);
        dadosPagamento.setLayout(dadosPagamentoLayout);
        dadosPagamentoLayout.setHorizontalGroup(
            dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dadosPagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dadosPagamentoLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dadosPagamentoLayout.createSequentialGroup()
                                .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataEmissaoLabel)
                                    .addComponent(dataEmissaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dataVencimentoLabel)
                                    .addComponent(dataVencimentoTxt))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dadosPagamentoLayout.createSequentialGroup()
                                        .addComponent(valorLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(valorTxt)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dadosPagamentoLayout.createSequentialGroup()
                                .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(obsLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(dadosPagamentoLayout.createSequentialGroup()
                        .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matriculaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(matriculaLabel))
                        .addGap(18, 18, 18)
                        .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeAtletaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(atletaLabel))
                        .addGap(18, 18, 18)
                        .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dadosPagamentoLayout.setVerticalGroup(
            dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dadosPagamentoLayout.createSequentialGroup()
                .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matriculaLabel)
                    .addComponent(atletaLabel)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matriculaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeAtletaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dadosPagamentoLayout.createSequentialGroup()
                        .addGroup(dadosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dadosPagamentoLayout.createSequentialGroup()
                                .addComponent(dataEmissaoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataEmissaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dadosPagamentoLayout.createSequentialGroup()
                                .addComponent(dataVencimentoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataVencimentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dadosPagamentoLayout.createSequentialGroup()
                                .addComponent(valorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(obsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        quitarBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hand_thumbsup.png"))); // NOI18N
        quitarBt.setText("Quitar");
        quitarBt.setEnabled(false);
        quitarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarBtActionPerformed(evt);
            }
        });

        removerBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-trash.png"))); // NOI18N
        removerBt.setText("Remover");
        removerBt.setEnabled(false);
        removerBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerBtActionPerformed(evt);
            }
        });

        fecharBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-cross.png"))); // NOI18N
        fecharBt.setText("Fechar");
        fecharBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharBtActionPerformed(evt);
            }
        });

        atualizarBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-refresh3.png"))); // NOI18N
        atualizarBt.setText("Atualizar");
        atualizarBt.setEnabled(false);
        atualizarBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quitarBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(removerBt, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
            .addComponent(fecharBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(atualizarBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(quitarBt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(atualizarBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removerBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(fecharBt))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dadosPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dadosPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(236, 236, 236)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecharBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharBtActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_fecharBtActionPerformed

    private void tabelaAtividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAtividadesMouseClicked
        if (tabelaAtividades.getModel().getRowCount() > 0) {
            linhaSelecionada = tabelaAtividades.getSelectedRow();
            int coluna = tabelaAtividades.getSelectedColumn();

            idAtividade = (int) tabelaAtividades.getModel().getValueAt(linhaSelecionada, 0);
            valorAtividade = (float) tabelaAtividades.getModel().getValueAt(linhaSelecionada, 2);

            cc = new ContaController();
            contaTxt.setText(cc.retornaIdConta());

            /*Se tive setado os valores vai resetar*/
            valorTxt.setText(Float.toString(valorAtividade).replace(".", ","));
            dataVencimentoTxt.setText("");
            obsTxt.setText("");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String data = sdf.format(date);
            dataEmissaoTxt.setText(data);

            salvarBt.setEnabled(true);
            atualizarBt.setEnabled(false);
        }
    }//GEN-LAST:event_tabelaAtividadesMouseClicked

    private void salvarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarBtActionPerformed
        cc = new ContaController();
        String msg = "";

        String dataE = Validador.data(dataEmissaoTxt.getText(), 10);
        String dataV = Validador.data(dataVencimentoTxt.getText(), 10);

        if (Validador.erros == 0) {
            int matricula = Integer.parseInt(matriculaTxt.getText());
            c = new Conta();
            c.setId(Integer.parseInt(contaTxt.getText()));
            c.setLancamento(dataE);
            c.setValor_total(Float.parseFloat(valorTxt.getText().toString().replace(",", ".")));
            c.setSituacao("A");
            c.setVencimento(dataV);
            c.setObservacao(obsTxt.getText());

            if (cc.cadastrarConta(c, matricula) == true) {
                salvarBt.setEnabled(false);
                valorTxt.setText("");
                dataVencimentoTxt.setText("");
                contaTxt.setText("");
                tabelaAtividades.clearSelection();
            }
        } else {
            for (int i = 0; i < 15; i++) {//Lista os erros
                for (int j = 0; j < 5; j++) {
                    if (null != Validador.arrayErros[i][j]) {
                        msg = msg + Validador.arrayErros[i][j];
                    }

                }
            }

            JOptionPane.showMessageDialog(null, msg, "Erros", JOptionPane.ERROR_MESSAGE);
            Validador.erros = 0;
            for (int i = 0; i < 15; i++) {// Apaga os erros para não se acumular.
                for (int j = 0; j < 5; j++) {
                    if (null != Validador.arrayErros[i][j]) {
                        Validador.arrayErros[i][j] = "";
                    }

                }
            }
        }
    }//GEN-LAST:event_salvarBtActionPerformed

    private void dataEmissaoTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataEmissaoTxtMouseClicked
        int clique = evt.getClickCount();
        if (clique > 1) {
            dataEmissaoTxt.setEnabled(true);
        }
    }//GEN-LAST:event_dataEmissaoTxtMouseClicked

    private void tabelaMovimentacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMovimentacaoMouseClicked
        cliques = evt.getClickCount();

        if (cliques > 1) {
            cc = new ContaController();
            List<Conta> cl;
            cl = cc.retornaContaSelecionada(idMov);

            contaTxt.setText(String.valueOf(cl.get(0).getId()));
            dataEmissaoTxt.setText(cl.get(0).getLancamento());
            dataVencimentoTxt.setText(cl.get(0).getVencimento());
            String valor = Float.toString(cl.get(0).getValor_total());
            valorTxt.setText(valor.replace(".", ","));
            obsTxt.setText(cl.get(0).getObservacao());

            atualizarBt.setEnabled(true);
            salvarBt.setEnabled(false);
        }

        if (tabelaMovimentacao.getModel().getRowCount() > 0) {
            linhaSelecionadaMov = tabelaMovimentacao.getSelectedRow();
            int coluna = tabelaMovimentacao.getSelectedColumn();

            idMov = (int) tabelaMovimentacao.getModel().getValueAt(linhaSelecionadaMov, 0);
            situacaoMov = (String) tabelaMovimentacao.getModel().getValueAt(linhaSelecionadaMov, 4);

            removerBt.setEnabled(true);
            quitarBt.setEnabled(true);
        }
    }//GEN-LAST:event_tabelaMovimentacaoMouseClicked

    private void removerBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerBtActionPerformed
        cc = new ContaController();
        if (cc.removerConta(idMov) == true) {

            ctm = (ContaTableModel) ContasAtletasView.tabelaMovimentacao.getModel();
            ctm.removeRow(ContasAtletasView.linhaSelecionadaMov);
            tabelaMovimentacao.clearSelection();
            quitarBt.setEnabled(false);
            removerBt.setEnabled(false);
        }

    }//GEN-LAST:event_removerBtActionPerformed

    private void quitarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarBtActionPerformed
        cc = new ContaController();

        if (situacaoMov.equals("Pago")) {
            JOptionPane.showMessageDialog(null, "A conta ja está quitada.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

        } else {

            if (cc.quitarConta(idMov) == true) {
                ctm = new ContaTableModel();
                ctm = (ContaTableModel) ContasAtletasView.tabelaMovimentacao.getModel();
                ctm.setValueAt("Pago", ContasAtletasView.linhaSelecionadaMov, 4);
                tabelaMovimentacao.clearSelection();
                quitarBt.setEnabled(false);
                removerBt.setEnabled(false);
            }
        }
    }//GEN-LAST:event_quitarBtActionPerformed

    private void atualizarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarBtActionPerformed
        cc = new ContaController();

        String msg = "";

        String dataE = Validador.data(dataEmissaoTxt.getText(), 10);
        String dataV = Validador.data(dataVencimentoTxt.getText(), 10);

        if (Validador.erros == 0) {

            c = new Conta();
            c.setId(Integer.parseInt(contaTxt.getText()));
            c.setLancamento(dataE);
            c.setValor_total(Float.parseFloat(valorTxt.getText().toString().replace(",", ".")));
            c.setVencimento(dataV);
            c.setObservacao(obsTxt.getText());

            if (cc.atualizar(c) == true) {
                tabelaMovimentacao.clearSelection();
                ctm = new ContaTableModel();
                ctm = (ContaTableModel) ContasAtletasView.tabelaMovimentacao.getModel();
                ctm.setValueAt(c.getId(), ContasAtletasView.linhaSelecionadaMov, 0);
                ctm.setValueAt(ContasAtletasView.dataEmissaoTxt.getText(), ContasAtletasView.linhaSelecionadaMov, 1);
                ctm.setValueAt(ContasAtletasView.dataVencimentoTxt.getText(), ContasAtletasView.linhaSelecionadaMov, 2);
                ctm.setValueAt(c.getValor_total(), ContasAtletasView.linhaSelecionadaMov, 3);
                quitarBt.setEnabled(false);
                removerBt.setEnabled(false);
                atualizarBt.setEnabled(false);
            }
        } else {
            for (int i = 0; i < 15; i++) {//Lista os erros
                for (int j = 0; j < 5; j++) {
                    if (null != Validador.arrayErros[i][j]) {
                        msg = msg + Validador.arrayErros[i][j];
                    }

                }
            }

            JOptionPane.showMessageDialog(null, msg, "Erros", JOptionPane.ERROR_MESSAGE);
            Validador.erros = 0;
            for (int i = 0; i < 15; i++) {// Apaga os erros para não se acumular.
                for (int j = 0; j < 5; j++) {
                    if (null != Validador.arrayErros[i][j]) {
                        Validador.arrayErros[i][j] = "";
                    }

                }
            }
        }


    }//GEN-LAST:event_atualizarBtActionPerformed

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
            java.util.logging.Logger.getLogger(ContasAtletasView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContasAtletasView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContasAtletasView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContasAtletasView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ContasAtletasView dialog = new ContasAtletasView(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel atletaLabel;
    private javax.swing.JButton atualizarBt;
    public javax.swing.JTextField contaTxt;
    private javax.swing.JPanel dadosPagamento;
    private javax.swing.JLabel dataEmissaoLabel;
    public static javax.swing.JFormattedTextField dataEmissaoTxt;
    private javax.swing.JLabel dataVencimentoLabel;
    public static javax.swing.JFormattedTextField dataVencimentoTxt;
    private javax.swing.JButton fecharBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel matriculaLabel;
    public javax.swing.JTextField matriculaTxt;
    public javax.swing.JTextField nomeAtletaTxt;
    private javax.swing.JLabel obsLabel;
    private javax.swing.JTextArea obsTxt;
    private javax.swing.JButton quitarBt;
    private javax.swing.JButton removerBt;
    private javax.swing.JButton salvarBt;
    public static javax.swing.JTable tabelaAtividades;
    public static javax.swing.JTable tabelaMovimentacao;
    private javax.swing.JLabel valorLabel;
    private javax.swing.JFormattedTextField valorTxt;
    // End of variables declaration//GEN-END:variables
}