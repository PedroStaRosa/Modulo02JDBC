package br.com.Controller;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.Dao.ContaDAO;
import br.com.Model.Conta;
import br.com.Model.Pessoa;


public class ControllerConta {


	// FUNCTION
	public void insertConta (Conta conta) {
		
		ContaDAO ctDAO;
		ctDAO = new ContaDAO();
		conta.setSaldo(0);
		ctDAO.insertContaDAO(conta);

	}
	
	public void updateConta(Pessoa pessoa) {
		//ALTERAR LIMITE DA CONTA
		Scanner sc = new Scanner(System.in);
		String dadoAlterado="";
		ContaDAO cDAO = new ContaDAO();
		System.out.println("Digite o novo limite da conta: ");
		dadoAlterado = sc.nextLine();
		cDAO.updateContaDAO(pessoa, "limite" ,dadoAlterado);
	}
	
}
