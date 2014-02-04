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

import app.model.Atividade;
import app.model.AtividadeModel;
import app.model.AtletaAtividade;
import app.model.tablemodel.AtividadeTableModel;
import app.model.tablemodel.AtletaAtividadeTableModel;
import app.view.atividade.AtividadeEditar;
import app.view.atividade.AtividadeForm;
import app.view.atividade.AtividadeView;
import app.view.atletaAtividade.AtletaAtividadeForm;
import app.view.atletaAtividade.AtletaAtividadeView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author WISE
 */
public class AtividadeController {

    public static int linhaSelecionadaTabelela;

    private AtividadeForm afrm;
    private AtividadeView av;
    private AtividadeModel am;
    private AtividadeEditar ae;
    private Atividade a;

    private AtividadeTableModel atm;
    private AtletaAtividadeTableModel aatm;

    /**
     * Cadastro
     */
    public void despachar() {
        av = new AtividadeView(null, true);
        av.setVisible(true);
    }

    public void chamarForm() {
        afrm = new AtividadeForm(null, true);
        afrm.setVisible(true);
    }

    public Boolean cadastrar(Atividade a) {
        am = new AtividadeModel();

        if (am.cadastrar(a) == true) {
            JOptionPane.showMessageDialog(null, "Atividade Cadastrada.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

            atm = (AtividadeTableModel) AtividadeView.tabelaAtividade.getModel();
            int count = atm.getRowCount();
            atm.addRow(a.getId(), a.getNome().toString(), a.getValor(), a.getAtivo(), count);
            return true;
        }

        return false;

    }

    public AtividadeTableModel listar() {
        am = new AtividadeModel();
        return am.listar();

    }

    public void deletar(int id, int LinhaSelecionada) {
        am = new AtividadeModel();
        am.deletar(id);
        JOptionPane.showMessageDialog(null, "Registro deletado.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
        atm = new AtividadeTableModel();
        atm = (AtividadeTableModel) AtividadeView.tabelaAtividade.getModel();
        atm.removeRow(LinhaSelecionada);
    }

    public void editar(int id) {

        ae = new AtividadeEditar(null, true);
        am = new AtividadeModel();

        a = am.retorna_atividade(id);
        ae.nomeTxt.setText(a.getNome());
        ae.valorTxt.setValue(a.getValor());

        if ("S".equals(a.getAtivo())) {
            ae.ativoCbox.setSelected(true);
        } else {
            ae.ativoCbox.setSelected(false);
        }

        ae.setVisible(true);

    }

    public void salvarEdicao(Atividade a, int id) {
        am = new AtividadeModel();
        am.atualizar(a, id);
        atm = (AtividadeTableModel) AtividadeView.tabelaAtividade.getModel();
        JOptionPane.showMessageDialog(null, "Registro atualizado.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

        atm.setValueAt(a.getId(), linhaSelecionadaTabelela, 0);
        atm.setValueAt(a.getNome(), linhaSelecionadaTabelela, 1);
        atm.setValueAt(a.getValor(), linhaSelecionadaTabelela, 2);
        atm.setValueAt(a.getAtivo(), linhaSelecionadaTabelela, 3);
        atm.fireTableDataChanged();

    }

    public void filtrar(String valor) {
        am = new AtividadeModel();
        List<Atividade> lista = new ArrayList<Atividade>();

        lista = am.filtrar(valor);

        atm = (AtividadeTableModel) AtividadeView.tabelaAtividade.getModel();

        atm.removeAll();

        if (lista.size() > 0) {

            for (int i = 0; i < lista.size(); i++) {
                atm.addRow(lista.get(i).getId(), lista.get(i).getNome(), lista.get(i).getValor(), lista.get(i).getAtivo(), i);
                atm.fireTableDataChanged();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * AtletaAtividade
     *
     * @param matricula
     * @return
     */
    public AtletaAtividadeTableModel listarAtletaAtividade(int matricula) {
        am = new AtividadeModel();
        AtletaAtividade atleta = new AtletaAtividade();
        atleta.setAtletaId(am.recuperaIdAtleta(matricula));
        return am.listaAtletaAtividades(atleta);
    }

    public int recuperaIdAtleta(int matricula) {
        am = new AtividadeModel();
        return am.recuperaIdAtleta(matricula);

    }

    public List<Atividade> listarAtividadeAtleta() {
        am = new AtividadeModel();
        return am.listarAtletaAtividade();

    }
    /*Cadastra uma atividade num atleta
     */

    public boolean cadastrarAtletaAtividade(int atividadeId, int atletaId) {

        am = new AtividadeModel();
        if (am.cadastrarAtletaAtividade(atividadeId, atletaId) == true) {
            JOptionPane.showMessageDialog(null, "Atividade incluída.", "Alerta", JOptionPane.INFORMATION_MESSAGE);

            DefaultListModel dm = (DefaultListModel) AtletaAtividadeForm.lista.getModel();
            dm.removeElement(AtletaAtividadeForm.lista.getSelectedValue());

           // int countRegistroTabela = AtletaAtividadeView.atletaAtividadeTabela.getModel().getRowCount();

            aatm = (AtletaAtividadeTableModel) AtletaAtividadeView.atletaAtividadeTabela.getModel();
            int count = aatm.getRowCount();
            aatm.addRow(AtletaAtividadeForm.atividadeNome, Math.abs(Float.parseFloat(AtletaAtividadeForm.atividadeValor)), "S", count);

            return true;
        }

        return false;
    }
    /*Lista as atividades que o atleta não está cadastrado.*/

    public List<Atividade> ListarAtletaAtividades(int atletaId) {
        am = new AtividadeModel();

        List<Atividade> a1 = am.listarAtividade(); /*Atividades completas*/

        List<AtletaAtividade> a2 = am.listaAtletaAtividades2(atletaId); /*Atividades que o atleta já está participando*/


        /*Compara 2 listas se os dados de ambos for iguais remove o que tem a lista de atividades completa*/
        for (int i = 0; i < a2.size(); i++) {
            for (int j = 0; j < a1.size(); j++) {

                if (a2.get(i).getAtividadeId() == a1.get(j).getId()) {
                    a1.remove(j);
                    break;/*Sai do loop*/

                }
            }

        }
        /*Retorna atividades compeltas*/
        return a1;

    }

    /**
     *
     * @param nomeAtividade Pega o id da ativdade pelo nome.
     * @return
     */
    public Boolean removeAtividadeAtleta(String nomeAtividade, int linhaSelecionada) {
        am = new AtividadeModel();

        int id = am.retornaIdAtividade(nomeAtividade);

        if (am.removerAtividade(AtletaAtividadeView.atletaId, id) == true) {
            JOptionPane.showMessageDialog(null, "Registro deletado.", "Alerta.", JOptionPane.INFORMATION_MESSAGE);
            aatm = new AtletaAtividadeTableModel();
            aatm = (AtletaAtividadeTableModel) AtletaAtividadeView.atletaAtividadeTabela.getModel();
            aatm.removeRow(linhaSelecionada);

            return true;
        }

        return false;
    }

    /*Fim AtletaAtividade*/
}
