package br.com.Model;

public class Pessoa {

	private String cpf;
	private String nome;
	private int idade;
	private String sexo;
	private String login, password;
	private int nivelAcesso;
	private Endereco endereco;
	private Conta conta;
	public Pessoa(String cpf, String nome, int idade, String sexo,String login, String password, int nivelAcesso, Endereco endereco, Conta conta) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.login = login;
		this.password = password;
		this.nivelAcesso = nivelAcesso;
		this.endereco = endereco;
		this.conta = conta;
	}
	public Pessoa() {
		super();
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
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	
	
	
}
