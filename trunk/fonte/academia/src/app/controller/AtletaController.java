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
package app.controller;

import app.model.Atleta;
import app.model.AtletaModel;
import app.model.tablemodel.AtletaTableModel;
import app.view.atleta.AtletaCadastro;
import app.view.atleta.AtletaEditar;
import app.view.atleta.AtletaView;
import app.view.atletaAtividade.AtletaAtividadeForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import libs.Validador;

/**
 *
 * @author WISE
 */
public class AtletaController {

    private AtletaModel am;
    private AtletaCadastro acv;
    private AtletaEditar ae;
    private Atleta a;
    private AtletaTableModel atm;
    private AtletaView av;

    public static String arquivoImagem = "";
    public static String arquivoImagemCaminho = "";
    public static int linhaSelecionadaTabelela;

    public static String rgAntigo; //Edição de atleta.

    private ImageIcon imIcon;

    public void despachar() {
        av = new AtletaView(null, true);
        av.setVisible(true);
    }

    public boolean cadastrar(Atleta a) {
        am = new AtletaModel();

        if (am.checarMatriculaExistente(a.getMatricula()) == true) {

            if (am.checarRgExistente(a.getRg())) {

                if (!"".equals(arquivoImagem)) {/*checa se tem imagem.*/

                    arquivoImagemCaminho = am.savarImagem(a.getMatricula(), arquivoImagem);
                    a.setFoto(arquivoImagemCaminho);
                }

                if (am.cadastrar(a) == false) {
                    JOptionPane.showMessageDialog(null, "Falha ao gravar.", "Alerta", JOptionPane.ERROR_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(null, "Cadastro realizado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                    atm = (AtletaTableModel) AtletaView.atletaTabela.getModel();
                    int count = atm.getRowCount();
                    atm.addRow(a.getMatricula(), a.getNome(), a.getRg(), a.getAtivo(), count);
                    return true;
                }

            } else {
                JOptionPane.showMessageDialog(null, "RG em uso.", "Alerta", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Matricula em uso.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;

    }

    /**
     *
     * @param operacao C = Cadastra , E = Editar, isso irá escolher qual label
     * da view pegar.
     */
    public void escolherImagem(String operacao) {
        am = new AtletaModel();
        arquivoImagem = am.escolherImagem();

        if (null != arquivoImagem) {

            if ("1".equals(arquivoImagem)) {

                if ("C".equals(operacao)) {
                    AtletaCadastro.imageLb.setIcon(null);
                } else {
                    AtletaEditar.imageLb.setIcon(null);
                }

                JOptionPane.showMessageDialog(null, "Nenhuma imagem foi selecionada.", "Alerta", JOptionPane.ERROR_MESSAGE);
            } else {

                if (Validador.validaFormato(arquivoImagem) == true) {

                    if ("C".equals(operacao)) {
                        AtletaCadastro.imageLb.setIcon(new ImageIcon(arquivoImagem));
                        AtletaCadastro.imageLb.setIcon(am.setaImagem(arquivoImagem));
                    }
                    if ("E".equals(operacao)) {
                        AtletaEditar.imageLb.setIcon(new ImageIcon(arquivoImagem));
                        AtletaEditar.imageLb.setIcon(am.setaImagem(arquivoImagem));
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Formato de imagem não permitido. Formatos permitidos: jpg, jpeg, png", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }

    public String gerarMatricula() {
        am = new AtletaModel();
        return am.gerarMatricula();
    }

    /**
     * FIM Cadastro *
     */
    public AtletaTableModel listar() {
        am = new AtletaModel();
        return am.listar();

    }

    public  void editar(int matricula) {

        ae = new AtletaEditar(null, true);
        am = new AtletaModel();

        a = am.retorna_atleta(matricula);
        ae.matriculaTxt.setText(String.valueOf(a.getMatricula()));
        ae.nomeTxt.setText(a.getNome());
        ae.rgTxt.setText(a.getRg());

        rgAntigo = a.getRg(); //seta o rg antigo. caso for tracado.
        String data = Validador.dataFormatada(a.getData_nascimento());
        ae.dataNascTxt.setText(data);

        ae.enderecoTxt.setText(a.getEndereco());
        ae.cidadeTxt.setText(a.getCidade());
        ae.bairroTxt.setText(a.getBairro());
        ae.cepTxt.setText(a.getCep());
        ae.telefoneTxt.setText(a.getTelefone());
        ae.observacaoTxt.setText(a.getObservacao());

        if ("S".equals(a.getAtivo())) {
            ae.ativoCheck.setSelected(true);
        } else {
            ae.ativoCheck.setSelected(false);
        }

        if ("M".equals(a.getSexo())) {
            ae.sexoCombo.setSelectedIndex(0);
        } else {
            ae.sexoCombo.setSelectedIndex(1);
        }

        am.mostrarImagem(a.getFoto());

        ae.setVisible(true);

    }

    public Boolean salvaEdicao(Atleta a, int matricula) {
        am = new AtletaModel();

        if (am.checarAtleta(matricula, a.getMatricula()) == true) {

            if (am.chegarAtletaRg(a.getRg(), rgAntigo)) {

                if (!"".equals(arquivoImagem)) {/*checa se tem imagem.*/

                    arquivoImagemCaminho = am.savarImagem(a.getMatricula(), arquivoImagem);
                    a.setFoto(arquivoImagemCaminho);

                }

                if (am.editar(a, matricula) == true) {
                    JOptionPane.showMessageDialog(null, "Registro alterado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

                    atm = (AtletaTableModel) AtletaView.atletaTabela.getModel();
                    atm.setValueAt(a.getMatricula(), linhaSelecionadaTabelela, 0);
                    atm.setValueAt(a.getNome(), linhaSelecionadaTabelela, 1);
                    atm.setValueAt(a.getRg(), linhaSelecionadaTabelela, 2);
                    atm.setValueAt(a.getAtivo(), linhaSelecionadaTabelela, 3);
                    atm.fireTableDataChanged();
                    return true;

                }
            } else {
                JOptionPane.showMessageDialog(null, "Rg em uso.", "Alerta", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Matricula em uso.", "Alerta", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }

    public boolean deletar(int matricula, int linhaSelecionada) {
        am = new AtletaModel();

        if (am.deletar(matricula) == true) {
            JOptionPane.showMessageDialog(null, "Registro deletado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            atm = new AtletaTableModel();
            atm = (AtletaTableModel) AtletaView.atletaTabela.getModel();
            atm.removeRow(linhaSelecionada);

            return true;
        } 

        return false;
    }

    public void filtrar(String valor, String filtro) {
        am = new AtletaModel();
        List<Atleta> lista = new ArrayList<Atleta>();

        lista = am.filtrar(filtro, valor);

        atm = (AtletaTableModel) AtletaView.atletaTabela.getModel();
        
        atm.removeAll();

        if (lista.size() > 0) {

            for (int i = 0; i < lista.size(); i++) {
                atm.addRow(lista.get(i).getMatricula(), lista.get(i).getNome(), lista.get(i).getRg(), lista.get(i).getAtivo(), i);
                atm.fireTableDataChanged();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /* Atleta atividade*/
    public Atleta retornaAtleta(int matricula) {
        am = new AtletaModel();

        return a = am.retorna_atleta(matricula);
    }

    public void despacharAtvidadeForm(Atleta a) {
        AtletaAtividadeForm aa = new AtletaAtividadeForm(null, true, a.getId());
        aa.setVisible(true);
    }

    /*Fim atleta atividade*/
}
