/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.contadetalhes;

import java.util.List;

/**
 *
 * @author MafraWise
 */
public interface ContasDetalhesIntarface {

    public List<ContaDetalhes> listar(int idContasReceber);

    public boolean cadastrar(List<ContaDetalhes> c);

    public boolean deletar();

    public boolean alterar();

}
