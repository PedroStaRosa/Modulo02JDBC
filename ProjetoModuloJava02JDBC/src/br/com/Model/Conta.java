package br.com.Model;

public class Conta {

	private int idConta;
	private double limite, saldo;
	
	public Conta(int idConta, double limite, double saldo) {
		super();
		this.idConta = idConta;
		this.limite = limite;
		this.saldo = saldo;
	}

	
	public Conta() {
		super();
	}


	
	public int getIdConta() {
		return idConta;
	}


	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}


	public double getLimite() {
		return limite;
	}


	public void setLimite(double limite) {
		this.limite = limite;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

}
