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
	
	public Conta selectConta(Pessoa pessoa) {
		
		ContaDAO cDAO = new ContaDAO();
		Conta conta = cDAO.selectContaPessoa_Conta(pessoa);
		
		String dadosAtualizados = " Limite: "+ValidaOper.imprimeReal(conta.getLimite())+""
								+ "\n Saldo: "+ValidaOper.imprimeReal(conta.getSaldo())+"";
		
		System.out.println(dadosAtualizados);
		return conta;
		
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
				cDAO.sacar_depositar_DAO(conta,saldoAtualizado);
				System.out.println("\nSAQUE REALIZADO COM SUCESSO!!!\n");
				sair = true;
			}
				
		} while (sair = false);
		
	}

	public void depositar(Conta conta) {
		
		double valorDeposito;
		boolean sair = false;
		Scanner sc = new Scanner(System.in);
		
		do {
			
			System.out.println("Digite o valor que deseja depositar...");
			valorDeposito = sc.nextDouble(); sc.nextLine();
			
			if((conta.getSaldo() + valorDeposito) > conta.getLimite()) {
				System.out.println("Desculpe, limite excedido.");
				System.out.println("Seu limite atual é de "+ValidaOper.imprimeReal(conta.getLimite())+"");
				System.out.println("Saldo atual em conta "+ValidaOper.imprimeReal(conta.getSaldo()));
				System.out.println("Valor disponivel para deposito: "+ValidaOper.imprimeReal(conta.getLimite()-conta.getSaldo()));
			}else {
			
				double saldoAtualizado;
				saldoAtualizado = conta.getSaldo() + valorDeposito;
				ContaDAO cDAO = new ContaDAO();
				cDAO.sacar_depositar_DAO(conta,saldoAtualizado);
				System.out.println("\nDEPOSITO REALIZADO COM SUCESSO!!!\n");
				sair = true;
			}
				
		} while (sair = false);
		
	}

	public void transferir (Conta conta) {
		double valorTransfere;
		int numeroContaBenef; // AG
		String cpfBenef; //CC
		
		
		Scanner sc = new Scanner(System.in);
		ContaDAO cDAO = new ContaDAO();
		
		System.out.println("Digite o numero da conta beneficiada....");
		numeroContaBenef = sc.nextInt(); sc.nextLine();
		
		System.out.println("Digite o cpf do beneficiario...");
		cpfBenef = sc.nextLine();
		
		System.out.println("Digite o valor da transferencia....");
		valorTransfere = sc.nextDouble(); sc.nextLine();
		
		cDAO.transfere_DAO(numeroContaBenef,cpfBenef,valorTransfere);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


}