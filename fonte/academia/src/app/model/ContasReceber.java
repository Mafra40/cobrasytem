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
package app.model;

/**
 *
 * @author WISE
 */
public class ContasReceber {

    private int id;
    private int id_atleta;
    private String vencimento;
    private float valor_total;
    private String situacao;
    private String lancamento;
    private String observacao;

    public ContasReceber(int id, int id_atleta, String vencimento, float valor_total, String situacao, String lancamento, String observacao) {
        this.id = id;
        this.id_atleta = id_atleta;
        this.vencimento = vencimento;
        this.valor_total = valor_total;
        this.situacao = situacao;
        this.lancamento = lancamento;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_atleta() {
        return id_atleta;
    }

    public void setId_atleta(int id_atleta) {
        this.id_atleta = id_atleta;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
