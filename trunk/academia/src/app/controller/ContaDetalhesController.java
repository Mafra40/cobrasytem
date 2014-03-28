/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.contadetalhes.ContaDetalhes;
import app.model.contadetalhes.ContaDetalhesModel;
import app.model.tablemodel.ContasDetalhesTableModel;
import app.view.contas.ContasDetalhesLista;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MafraWise
 */
public class ContaDetalhesController {

    private List<ContaDetalhes> c;

    private ContaDetalhesModel cm;
    private ContasDetalhesLista cdl;
    private ContasDetalhesTableModel cdtm;

    public List<ContaDetalhes> listar(int idContaReceber) {

        cm = new ContaDetalhesModel();
        c = cm.listar(idContaReceber);
        return c;
    }

    public boolean cadastrar(List<ContaDetalhes> c) {
        cm = new ContaDetalhesModel();
        return cm.cadastrar(c);
    }

    public boolean chamarVieDetalhes(int idContaReceber) {

        c = listar(idContaReceber);

        if (c.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O detalhamento desta conta não está disponivel.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            cdl = new ContasDetalhesLista(null, true, idContaReceber, c);

            cdl.setVisible(true);
            return true;
        }
    }

    /**
     * Função que checa se existe algum detalhe de uma conta de um atleta.
     *
     * @param idUltimaContaReceberExistente Ultimo id da conta de um atleta.
     */
    public List<ContaDetalhes> checarDetalhes(int idUltimaContaReceberExistente) {
        cm = new ContaDetalhesModel();
        return cm.checarDetalhes(idUltimaContaReceberExistente);
    }

    /**
     * Função que retorna um ID de sua ultima conta registrada.
     *
     * @param idAtleta
     * @return
     */
    public int retornaUltimaContaDeUmAtleta(int idAtleta) {
        int ultimoId = 0;
        cm = new ContaDetalhesModel();
        ultimoId = cm.retornaUltimaContaDeUmAtleta(idAtleta);
        return ultimoId;
    }
}
