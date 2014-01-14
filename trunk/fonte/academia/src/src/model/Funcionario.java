/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.model;

/**
 *
 * @author WISE
 * 
 * Classe Funcionário.
 */
public class Funcionario {
    
   // private int id;
    private String cpf;
    private String nome;
    private String endereco;
    private String cidade;
    private String bairro;
    private String cep;
    private String sexo;
    private String telefone;
    private String login;
    private String senha;
    
    public Funcionario(){
        
    }

    public Funcionario(String cpf, String nome, String bairro, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.bairro = bairro;
        this.telefone = telefone;
    }
    
    /**
     * Construtor de Autenticação.
     * 
     * @param login
     * @param senha 
     */
    public Funcionario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
    
   
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
            
    
}
