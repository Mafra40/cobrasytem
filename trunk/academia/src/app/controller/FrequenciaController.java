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
import app.model.Conta;
import app.model.Frequencia;
import app.model.FrequenciaModel;
import app.model.tablemodel.FrequenciaTableModel;
import app.model.tablemodel.PendenciasTableModel;
import app.view.frequencia.FrequenciaDiaria;
import app.view.frequencia.FrequenciaManual;
import app.view.frequencia.FrequenciaLista;
import app.view.frequencia.FrequenciaView;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author WISE
 */
public class FrequenciaController {

    private FrequenciaModel fm;
    private FrequenciaLista fv;
    private Atleta f;
    private Atleta a;
    private AtletaModel am;
    private FrequenciaManual frm;
    private FrequenciaTableModel ftm;
    public static List<Frequencia> fl;

    public boolean gerarAta() {
        fm = new FrequenciaModel();

        if (fm.verificaAta() == false) {
            if (fm.insere(fm.gerarAta()) == true) {
                return true;
            }
        } else {
            return true;
        }

        return false;
    }

    public boolean atualizarAta() {
        fm = new FrequenciaModel();
        fm.deletar();
        if (fm.insere(fm.gerarAta()) == true) {
            return true;
        }

        return false;
    }

    public FrequenciaTableModel listaFrequencia() {
        fm = new FrequenciaModel();

        return new FrequenciaTableModel(fm.listaAta());

    }

    public void chamarViewFrequencia() {
        fv = new FrequenciaLista(null, true);
        fv.setVisible(true);
    }

    public void checkin(int matricula) {
        fm = new FrequenciaModel();
        a = fm.retornaAtleta(matricula);

        if (a != null) {/*Se a consulta retornar um Atleta*/

            fm.mostrarImagem(a.getFoto());
            FrequenciaView.nomeTxt.setText(a.getNome());

            FrequenciaView.observacaoTxt.setText(a.getObservacao());

            if (a.getAtivo().equals("S")) { /*Se estiver ativo checa se tem pendência*/

                FrequenciaView.statusTxt.setText("Ativado");

                List<Conta> cl = fm.checarPendencia(a);

                if (cl.size() > 0) {/*Se tiver pendencia, lista e da o acesso negado.*/

                    PendenciasTableModel ptm = new PendenciasTableModel(cl);
                    FrequenciaView.pendenciasTabela.setModel(ptm);
                    FrequenciaView.acessoLabel.setForeground(Color.red);
                    FrequenciaView.acessoLabel.setText("ACESSO NEGADO. PENDENCIAS");

                } else {
                    /*Se não tiver pendencia.*/
                    if (fm.alterarFrequencia(a) == true) {

                        FrequenciaView.acessoLabel.setForeground(Color.BLUE);
                        FrequenciaView.acessoLabel.setText("ACESSO LIBERADO.");

                    }
                }

            } else {/*Se não tiver ativo.*/

                List<Conta> cl = fm.checarPendencia(a);
                if (cl.size() > 0) {/*Mesmo se tiver desativo, tenta achar as pendencias.*/

                    PendenciasTableModel ptm = new PendenciasTableModel(cl);
                    FrequenciaView.pendenciasTabela.setModel(ptm);

                    FrequenciaView.statusTxt.setText("Desativado");
                    FrequenciaView.acessoLabel.setForeground(Color.red);
                    FrequenciaView.acessoLabel.setText("ACESSO NEGADO. POR DESATIVADO");

                }

            }

        } else {

            JOptionPane.showMessageDialog(null, "Atleta inexistente", "Erro", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void chamaChekinManualForm() {
        frm = new FrequenciaManual(null, true);
        frm.setVisible(true);
    }

    public void checkinManual(int matricula) {
        fm = new FrequenciaModel();
        a = fm.retornaAtleta(matricula);

        if (a != null) {/*Se a consulta retornar um Atleta*/

            fm.mostrarImagemManual(a.getFoto());
            FrequenciaManual.nomeTxt.setText(a.getNome());
            FrequenciaManual.observacaoTxt.setText(a.getObservacao());

            if (a.getAtivo().equals("S")) { /*Se estiver ativo checa se tem pendência*/

                FrequenciaManual.statusTxt.setText("Ativado");

                List<Conta> cl = fm.checarPendencia(a);

                if (cl.size() > 0) {/*Se tiver pendencia, lista e da o acesso negado.*/

                    PendenciasTableModel ptm = new PendenciasTableModel(cl);
                    FrequenciaManual.pendenciasTabela.setModel(ptm);

                    FrequenciaManual.acessoLabel.setForeground(Color.red);
                    FrequenciaManual.acessoLabel.setText("ACESSO NEGADO.");

                } else {
                    /*Se não tiver pendencia.*/

                    if (fm.verificarAteltaAta(a) == true) {/*se existir altera*/

                        if (fm.alterarFrequencia(a) == true) {

                            FrequenciaManual.acessoLabel.setForeground(Color.BLUE);
                            FrequenciaManual.acessoLabel.setText("ACESSO LIBERADO.");

                        }

                    } else { /*se não existir insere*/

                        if (fm.insereFaltante(a) == true) {
                            FrequenciaManual.acessoLabel.setForeground(Color.BLUE);
                            FrequenciaManual.acessoLabel.setText("ACESSO LIBERADO.");
                        } else {
                            System.err.println("Erro");
                        }
                    }

                }

            } else {/*Se não tiver ativo.*/

                FrequenciaManual.statusTxt.setText("Desativado");

                FrequenciaManual.acessoLabel.setForeground(Color.red);
                FrequenciaManual.acessoLabel.setText("ACESSO NEGADO");
                List<Conta> cl = fm.checarPendencia(a);
                if (cl.size() > 0) {/*Mesmo se tiver desativo, tenta achar as pendencias.*/

                    PendenciasTableModel ptm = new PendenciasTableModel(cl);
                    FrequenciaManual.pendenciasTabela.setModel(ptm);

                }

            }

        } else {

            JOptionPane.showMessageDialog(null, "Atleta inexistente", "Erro", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void frequenciaDiaria(String data) {

        fm = new FrequenciaModel();
        fl = (fm.frequenciaDiaria(data));

        if (fl.size() > 0) {

            FrequenciaDiaria fd = new FrequenciaDiaria(null, true);

            fd.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ata com esta data não existe.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adiciona um atleta na lista de frequência na data que foi lançado.
     *
     * @param idAtleta
     * @return
     */
    public boolean addNaFrequencia(int idAtleta) {
        fm = new FrequenciaModel();

        return fm.addNaFrequencia(idAtleta);
    }

}
