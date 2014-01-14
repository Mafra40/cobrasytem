/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WISE
 */
public class FuncionarioModel {

    private Statement stm;
    private ResultSet rs;

    public List<Funcionario> funcs = new ArrayList<Funcionario>();

    /*Lista o usuários do sistema. FUncionários.
     */
    public List<Funcionario> Listar() {
        String Query = "SELECT * FROM funcionario";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(Query);

            int i = 0;
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setCpf(rs.getString("cpf"));
                func.setNome(rs.getString("nome"));
                func.setEndereco(rs.getString("endereco"));
                func.setCidade(rs.getString("cidade"));
                func.setBairro(rs.getString("bairro"));
                func.setCep(rs.getString("cep"));
                func.setSexo(rs.getString("sexo"));
                funcs.add(func);

            }

            /**
             * Lista numa Array List FuncionarioModel FM = new
             * FuncionarioModel(); FM.Listar(); for(Funcionario f : FM.funcs){
             * System.out.println(f.getNome()); System.out.println(f.getCpf());
             *
             * }
             *
             *
             */
        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();

        return funcs;
    }

}
