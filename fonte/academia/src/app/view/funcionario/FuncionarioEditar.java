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
package app.view.funcionario;

import app.controller.FuncionarioController;
import app.model.Funcionario;
import conf.Global;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import libs.Validador;

/**
 *
 * @author WISE
 */
public class FuncionarioEditar extends javax.swing.JDialog {

    private FuncionarioController fc;

    /**
     * Creates new form FuncionarioEditar
     */
    public FuncionarioEditar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btCadastrar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        guiaPrincipal = new javax.swing.JTabbedPane();
        painelDados = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        nomeTxt = new javax.swing.JTextField();
        lbEndereco = new javax.swing.JLabel();
        enderecoTxt = new javax.swing.JTextField();
        lbCidade = new javax.swing.JLabel();
        cidadeTxt = new javax.swing.JTextField();
        lbBairro = new javax.swing.JLabel();
        baixoTxt = new javax.swing.JTextField();
        cpfTxt = new javax.swing.JFormattedTextField();
        lbCep = new javax.swing.JLabel();
        lbTelefone = new javax.swing.JLabel();
        lbCpf = new javax.swing.JLabel();
        cepTxt = new javax.swing.JFormattedTextField();
        telefoneTxt = new javax.swing.JFormattedTextField();
        painelAcesso = new javax.swing.JTabbedPane();
        painelAutenticacao = new javax.swing.JPanel();
        lbLogin = new javax.swing.JLabel();
        loginTxt = new javax.swing.JTextField();
        msgSenha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-check.png"))); // NOI18N
        btCadastrar.setText("Alterar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-cross.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        lbNome.setText("Nome");

        lbEndereco.setText("Endereço");

        lbCidade.setText("Cidade");

        lbBairro.setText("Bairro");

        try {
            cpfTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbCep.setText("CEP");

        lbTelefone.setText("Telefone");

        lbCpf.setText("CPF");

        try {
            cepTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            telefoneTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout painelDadosLayout = new javax.swing.GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNome)
                    .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(painelDadosLayout.createSequentialGroup()
                            .addComponent(lbCidade)
                            .addGap(127, 127, 127)
                            .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(baixoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbBairro))
                            .addGap(35, 35, 35))
                        .addComponent(nomeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEndereco)
                            .addComponent(enderecoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(cidadeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCpf)
                            .addComponent(cpfTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTelefone)
                            .addComponent(telefoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCep)
                            .addComponent(cepTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCpf)
                    .addComponent(lbTelefone)
                    .addComponent(lbCep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpfTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cepTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEndereco)
                    .addComponent(lbCidade)
                    .addComponent(lbBairro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enderecoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cidadeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baixoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        guiaPrincipal.addTab("Dados do Funcionário", painelDados);

        lbLogin.setText("Login");

        javax.swing.GroupLayout painelAutenticacaoLayout = new javax.swing.GroupLayout(painelAutenticacao);
        painelAutenticacao.setLayout(painelAutenticacaoLayout);
        painelAutenticacaoLayout.setHorizontalGroup(
            painelAutenticacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAutenticacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelAutenticacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLogin)
                    .addComponent(loginTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        painelAutenticacaoLayout.setVerticalGroup(
            painelAutenticacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAutenticacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        painelAcesso.addTab("Dados de Autenticação", painelAutenticacao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelAcesso, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(guiaPrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(msgSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guiaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btCadastrar)
                        .addGap(32, 32, 32)
                        .addComponent(msgSenha))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
         String id, nome, cpf, endereco, cidade, bairro, cep, senha, senha2, telefone, login;
        String msg = "";

        Validador.valida_textField(nomeTxt, 100, true, false, 8, "Nome");
        Validador.valida_textField(enderecoTxt, 100, false, false, 1, "Endereço");
        Validador.valida_textField(cidadeTxt, 45, false, false, 2, "Cidade");
        Validador.valida_textField(baixoTxt, 45, false, false, 3, "Bairro");
        Validador.valida_textField(cepTxt, 15, true, false, 4, "CEP");
        Validador.valida_textField(telefoneTxt, 10, false, false, 6, "Telefone");
        Validador.valida_textField(loginTxt, 35, true, false, 7, "Login");
        Validador.cpf(cpfTxt.getText());
        
        if (Validador.erros == 0) {

            nome = nomeTxt.getText();
            cpf = cpfTxt.getText();
            endereco = enderecoTxt.getText();
            cidade = cidadeTxt.getText();
            bairro = baixoTxt.getText();
            cep = cepTxt.getText();
            telefone = telefoneTxt.getText();
            login = loginTxt.getText();

            Funcionario f = new Funcionario(cpf, nome, endereco, cidade, bairro, cep, telefone, login);
            FuncionarioController fc = new FuncionarioController();
            fc.salvar_edicao(f);

        } else {

            for (int i = 0; i < 9; i++) {//Lista os erros
                for (int j = 0; j < 3; j++) {
                    if (null != Validador.arrayErros[i][j]) {
                        msg = msg + Validador.arrayErros[i][j];
                    }

                }
            }

            JOptionPane.showMessageDialog(null, msg, "Erros", JOptionPane.ERROR_MESSAGE);
            Validador.erros = 0;
            for (int i = 0; i < 9; i++) {// Apaga os erros para não se acumular.
                for (int j = 0; j < 3; j++) {
                    if (null != Validador.arrayErros[i][j]) {
                        Validador.arrayErros[i][j] = "";
                    }

                }
            }
        }

        
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FuncionarioEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncionarioEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncionarioEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionarioEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FuncionarioEditar dialog = new FuncionarioEditar(new javax.swing.JFrame(), true);
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
    public javax.swing.JTextField baixoTxt;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelar;
    public javax.swing.JFormattedTextField cepTxt;
    public javax.swing.JTextField cidadeTxt;
    public javax.swing.JFormattedTextField cpfTxt;
    public javax.swing.JTextField enderecoTxt;
    private javax.swing.JTabbedPane guiaPrincipal;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCep;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbEndereco;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbTelefone;
    public javax.swing.JTextField loginTxt;
    private javax.swing.JLabel msgSenha;
    public javax.swing.JTextField nomeTxt;
    private javax.swing.JTabbedPane painelAcesso;
    private javax.swing.JPanel painelAutenticacao;
    private javax.swing.JPanel painelDados;
    public javax.swing.JFormattedTextField telefoneTxt;
    // End of variables declaration//GEN-END:variables

    private int validarCampos() {
        int erros = 0;
        
        Border borderR = BorderFactory.createLineBorder(Color.red);
        Border borderG = BorderFactory.createLineBorder(Color.GREEN);

        if ("".equals(nomeTxt.getText())) {//Nome
            nomeTxt.setBorder(borderR);
            erros++;
        } else {
            nomeTxt.setBorder(borderG);
        }

        if ("".equals(cpfTxt.getText())) { //cpf
            cpfTxt.setBorder(borderR);
            erros++;
        } else {
            cpfTxt.setBorder(borderG);
        }

       

        if ("".equals(loginTxt.getText())) {//login
            loginTxt.setBorder(borderR);
            erros++;
        } else {
            loginTxt.setBorder(borderG);
        }

        
        return erros;

    }
}