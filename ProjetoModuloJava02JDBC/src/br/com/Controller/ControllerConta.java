package br.com.Controller;

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
	
	public void sacar(Conta conta) {
		double valorSacar;
		boolean sair = false;
		Scanner sc = new Scanner(System.in);
		
		do {
			
			System.out.println("Digite o valor que deseja sacar...");
			valorSacar = sc.nextDouble(); sc.nextLine();
			
			if (valorSacar > conta.getSaldo()) {
				System.out.println("Você nao tem saldo sufuciente!!");
				System.out.println("Saldo em conta "+ValidaOper.imprimeReal(conta.getSaldo()));
			sair = false;
			} else {
				double saldoAtualizado;
				saldoAtualizado = conta.getSaldo() - valorSacar;
				ContaDAO cDAO = new ContaDAO();
				cDAO.sacarDAO(conta,saldoAtualizado);
				System.out.println("\nSAQUE REALIZADO COM SUCESSO!!!\n");
				sair = true;
			}
				
		} while (sair = false);
		
	}

	public Conta selectConta(Pessoa pessoa) {
		
		ContaDAO cDAO = new ContaDAO();
		Conta conta = cDAO.selectContaPessoa_Conta(pessoa);
		
		String dadosAtualizados = " Limite: "+ValidaOper.imprimeReal(conta.getLimite())+""
								+ "\n Saldo: "+ValidaOper.imprimeReal(conta.getSaldo())+"";
		
		System.out.println(dadosAtualizados);

		return conta;
		
	}
}
