/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.funcionario;

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
    private String telefone;
    private String login;
    private String senha;

    public Funcionario() {

    }

    public Funcionario(String cpf, String nome, String bairro, String telefone, String login) {
        this.cpf = cpf;
        this.nome = nome;
        this.bairro = bairro;
        this.telefone = telefone;
        this.login = login;
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

    /**
     * Cadastro
     *
     * @param cpf
     * @param nome
     * @param endereco
     * @param cidade
     * @param bairro
     * @param cep
     * @param telefone
     * @param login
     * @param senha
     */
    public Funcionario(String cpf, String nome, String endereco, String cidade, String bairro, String cep, String telefone, String login, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }
    
    /**
     * Construtor de Edição.
     * 
     * @param cpf
     * @param nome
     * @param endereco
     * @param cidade
     * @param bairro
     * @param cep
     * @param telefone
     * @param login
     */
     public Funcionario(String cpf, String nome, String endereco, String cidade, String bairro, String cep, String telefone, String login) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.login = login;
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
