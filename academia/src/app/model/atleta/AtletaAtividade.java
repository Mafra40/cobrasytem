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
package app.model.atleta;

/**
 *
 * @author WISE
 */
public class AtletaAtividade {

    private int atletaId;
    private int atividadeId;
    private String nomeAtividade;
    private String nomeAtleta;
    private String ativoAtividade;
    private Float valorAtividade;
    private int matricula;

    
    
    
     public AtletaAtividade() {
        
    }
    
    /**
     * Construturo de cadastro.
     *
     * @param atletaId
     * @param atividadeId
     */
    public AtletaAtividade(int atletaId, int atividadeId) {
        this.atletaId = atletaId;
        this.atividadeId = atividadeId;
    }
    /**Construtor de listagem
     * 
     * @param atletaId
     * @param atividadeId
     * @param nomeAtividade
     * @param ativoAtividade
     * @param valorAtividade 
     */
    public AtletaAtividade(int atletaId, int atividadeId, String nomeAtividade, String ativoAtividade, Float valorAtividade) {
        this.atletaId = atletaId;
        this.atividadeId = atividadeId;
        this.nomeAtividade = nomeAtividade;
        this.ativoAtividade = ativoAtividade;
        this.valorAtividade = valorAtividade;
    }
    
    

    public int getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(int atletaId) {
        this.atletaId = atletaId;
    }

    public int getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(int atividadeId) {
        this.atividadeId = atividadeId;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
    }

    public String getAtivoAtividade() {
        return ativoAtividade;
    }

    public void setAtivoAtividade(String ativoAtividade) {
        this.ativoAtividade = ativoAtividade;
    }

    public Float getValorAtividade() {
        return valorAtividade;
    }

    public void setValorAtividade(Float valorAtividade) {
        this.valorAtividade = valorAtividade;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    
}
