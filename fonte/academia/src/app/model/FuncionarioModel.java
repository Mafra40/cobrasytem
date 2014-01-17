/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import app.model.tablemodel.FuncionarioTableModel;
import app.view.funcionario.FuncionarioEditar;
import app.view.funcionario.FuncionarioView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author WISE
 */
public class FuncionarioModel {

    private Funcionario func;
    private PreparedStatement pstm;
    private Statement stm;
    private ResultSet rs;
    private String query;
    public List<Funcionario> funcs = new ArrayList<Funcionario>();
    private FuncionarioTableModel fModel;

    /*Lista o usuários do sistema. FUncionários.
     */
    public List<Funcionario> Listar() {
        query = "SELECT cpf, nome, cidade, bairro, login, telefone FROM funcionario";

        DB.conectar();

        try {
            stm = DB.con.createStatement();
            rs = stm.executeQuery(query);
           
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setCpf(rs.getString("cpf"));
                func.setNome(rs.getString("nome"));
               // func.setCidade(rs.getString("cidade"));
                func.setBairro(rs.getString("bairro"));
                func.setLogin(rs.getString("login"));
                func.setTelefone(rs.getString("telefone"));
                funcs.add(func);

            }

            
        } catch (SQLException ex) {
            System.out.println("ERRO");
        }

        DB.desconectar();

        return funcs;
    }

    public void cadastaFuncionario(Funcionario func) {
        query = "INSERT INTO `academia`.`funcionario`"
                + "(`cpf`,\n"
                + "`nome`,\n"
                + "`endereco`,\n"
                + "`cidade`,\n"
                + "`bairro`,\n"
                + "`cep`,\n"
                + "`senha`,\n"
                + "`telefone`,\n"
                + "`login`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?)";

        DB.conectar();
        try {
            pstm = DB.con.prepareStatement(query);

            pstm.setString(1, func.getCpf());
            pstm.setString(2, func.getNome());
            pstm.setString(3, func.getEndereco());
            pstm.setString(4, func.getCidade());
            pstm.setString(5, func.getBairro());
            pstm.setString(6, func.getCep());
            pstm.setString(7, func.getSenha());
            pstm.setString(8, func.getTelefone());
            pstm.setString(9, func.getLogin());

            pstm.execute();
            pstm.close();
            
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DB.desconectar();

    }
    
    public void atualizar_tabela(){
        
          Listar();
           
           List<Funcionario> lista = new ArrayList<Funcionario>();
           for (Funcionario f : funcs) {
           lista.add(new Funcionario(f.getCpf(),f.getNome(),f.getBairro(), f.getTelefone(), f.getLogin()));
           fModel = new FuncionarioTableModel(lista);
           FuncionarioView.funcionarioTable.setModel(fModel);
        
             }
    
    }
    
    public void  deletar(String cpf){
         query = "DELETE FROM funcionario "
                 + "WHERE cpf=?";

        DB.conectar();

        try {
            pstm = DB.con.prepareCall(query);
            pstm.setString(1, cpf);
            pstm.execute();
            pstm.close();
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DB.desconectar();

    }
    
    
    public Funcionario retorna_funcionario(String cpf){
        
        query = "SELECT * FROM funcionario "
                + " WHERE cpf='"+cpf+"'";
        
        DB.conectar();
       
        try {
            stm = DB.con.createStatement();
             rs = stm.executeQuery(query);
             
             if(rs.next()){
                func = new Funcionario();
                func.setCpf(rs.getString("cpf"));
                func.setNome(rs.getString("nome"));
                func.setCidade(rs.getString("cidade"));
                func.setBairro(rs.getString("bairro"));
                func.setCep(rs.getString("cep"));
                func.setEndereco(rs.getString("endereco"));
                func.setTelefone(rs.getString("telefone"));
                func.setLogin(rs.getString("login"));
             }
            
           
             
             
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        DB.desconectar();
        return func; 
    }
    

    /**
     * Valida CPF
     *
     * @param   CPF
     * @return
     */
    public static boolean isCPF(String CPF) {
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11)) {
            return (false);
        }
        char dig10, dig11;
        int sm, i, r, num, peso;

// Calculo do 1o. Digito Verificador 
        sm = 0;
        peso = 10;
        for (i = 0; i < 9; i++) {
// converte o i-esimo caractere do CPF em um numero: 
// por exemplo, transforma o caractere '0' no inteiro 0 
// (48 eh a posicao de '0' na tabela ASCII)
            num = (int) (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }
        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) {
            dig10 = '0';
        } else {
            dig10 = (char) (r + 48);
// converte no respectivo caractere numerico 
// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
                // Verifica se os digitos calculados conferem com os digitos informados.
                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                    return (true);
                } else {
                    return (false);
                }

            }
        }
        return false;

    }
}
