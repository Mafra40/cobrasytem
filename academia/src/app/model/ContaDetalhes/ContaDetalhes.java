/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.contadetalhes;

import app.model.conta.Conta;

/**
 *
 * @author MafraWise
 */
public class ContaDetalhes extends Conta {

    private int id_contas_receber;
    private int id_atividade;
    private float valor;

    public int getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(int id_atividade) {
        this.id_atividade = id_atividade;
    }

    public int getId_contas_receber() {
        return id_contas_receber;
    }

    public void setId_contas_receber(int id_contas_receber) {
        this.id_contas_receber = id_contas_receber;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
