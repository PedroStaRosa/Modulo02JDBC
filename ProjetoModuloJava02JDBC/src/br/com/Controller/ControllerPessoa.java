package br.com.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.Dao.ContaDAO;
import br.com.Dao.EnderecoDAO;
import br.com.Dao.PessoaDAO;
import br.com.Model.Conta;
import br.com.Model.Endereco;
import br.com.Model.Pessoa;



public class ControllerPessoa {

	public void insert () {
		
		Scanner sc = new Scanner(System.in);
		
		Pessoa p = new Pessoa();
		Conta c = new Conta();
		Endereco end = new Endereco();
		ControllerConta controlConta = new ControllerConta();
		ControllerEndereco controlEnd = new ControllerEndereco();
		
		String cpf;
		
		System.out.println("Olá vamos cadastrar uma pessoa?");
		/*
		System.out.println("CPF: ");
		p.setCpf(sc.nextLine());
		*/

		// VALIDA CPF !!!
		do {
			System.out.println("CPF: ");
			cpf = sc.nextLine();
			if(ValidaOper.isCPF(cpf) == true) {
				System.out.println("CPF valido =)");
				p.setCpf(cpf);
			} else System.out.println("CPF invalido!! =(");
		} while (ValidaOper.isCPF(cpf) == false);
		

		System.out.println("Nome:");
		p.setNome(sc.nextLine());
		System.out.println("Idade");
		p.setIdade(sc.nextInt());
		sc.nextLine();
		System.out.println("Sexo: (M/F)");
		p.setSexo(sc.nextLine());
		System.out.println("Numero da conta:");
		c.setIdConta(sc.nextInt());
		sc.nextLine();
		
		p.setConta(c);

		System.out.println("Endereço:");
		System.out.println("Rua: ");
		end.setRua(sc.nextLine());
		System.out.println("Numero: ");
		end.setNumero(sc.nextInt());
		sc.nextLine();
		System.out.println("Complemento: (CASA/APT)");
		end.setComplemento(sc.nextLine());

 
		PessoaDAO pDAO = new PessoaDAO();
		p.setEndereco(controlEnd.insertEndereco(end));
		controlConta.insertConta(c);
		pDAO.insertPersonDAO(p);
		System.out.println("Cadastrado com Sucesso!!! :)) ");
	}

	public void SelectAllPerson() throws SQLException {
		
		PessoaDAO pDAO = new PessoaDAO();
		List<Pessoa> listaPEssoas = pDAO.SelectAllPersonDAO();
		
		System.out.println("LISTA DE CLIENTES:");
		
		if(listaPEssoas != null && !listaPEssoas.isEmpty()) { // VERIFICA SE A LISTA ESTA VAZIA.
			
			for (Pessoa person : listaPEssoas) {

				String listaCliente ="---------------------------------------"
						   +"\n#\tNome: "+person.getNome()
						   +"\n#\tCPF: "+person.getCpf()
						   +"\n#\tIdade: "+person.getIdade()
						   +"\n#\tSexo: "+person.getSexo()
						   +"\n#\t========== CONTA ==========="
						   +"\n#\tNumero da conta: "+person.getConta().getIdConta()
						   +"\n#\tLimite da Conta: "+person.getConta().getLimite()
						   +"\n#\tSaldo em Conta: R$ "+person.getConta().getSaldo()
						   +"\n#\t========== ENDEREÇO =========="
						   +"\n#\tRua: "+person.getEndereco().getRua()
						   +"\n#\tNumero: "+person.getEndereco().getNumero()
						   +"\n#\tComplemento: "+person.getEndereco().getComplemento()
						   +"\n";
				
			System.out.println(listaCliente);
			}
		} else {
			
			System.out.println("Nenhum cliente Cadastrado.");
		}

		
	}

	public void SelectOnlyPerson(String cpfCliente) throws SQLException {
		
		PessoaDAO pDAO = new PessoaDAO();
		Pessoa cliente = pDAO.SelectOnlyPersonDAO(cpfCliente);
		
		System.out.println("CLIENTE:");


			String srtCliente ="---------------------------------------"
					   +"\n#\tNome: "+cliente.getNome()
					   +"\n#\tCPF: "+cliente.getCpf()
					   +"\n#\tIdade: "+cliente.getIdade()
					   +"\n#\tSexo: "+cliente.getSexo()
					   +"\n#\t================== CONTA ==================="
					   +"\n#\tNumero da conta: "+cliente.getConta().getIdConta()
				   	   +"\n#\tLimite da Conta: "+cliente.getConta().getLimite()
					   +"\n#\tSaldo em Conta: R$ "+cliente.getConta().getSaldo()
					   +"\n#\t================== ENDEREÇO =================="
					   +"\n#\tRua: "+cliente.getEndereco().getRua()
					   +"\n#\tNumero: "+cliente.getEndereco().getNumero()
					   +"\n#\tComplemento: "+cliente.getEndereco().getComplemento()
					   +"";
			
		System.out.println(srtCliente);
		
	}

	public void DeletePerson (String cpfCliente) throws SQLException {
		
		PessoaDAO pDAO = new PessoaDAO();
		EnderecoDAO endDAO = new EnderecoDAO();
		
		Pessoa pessoa = pDAO.SelectOnlyPersonDAO(cpfCliente);
		
		pDAO.DeletePersonDAO(cpfCliente);
		endDAO.DeleteEnderecoDAO(pessoa);
		
		System.out.println("Cliente: "+pessoa.getNome()+" excluido com sucesso!!");
	}
	
	public void updatePerson(String cpfCliente) throws SQLException {
		
		boolean sair = false;
		int campoAlterar=0;
		String dadoAlterado="";
		int atualizado = 0;
		PessoaDAO pDAO = new PessoaDAO();
		ContaDAO cDAO = new ContaDAO();
		EnderecoDAO endDAO = new EnderecoDAO();
		
		Scanner sc = new Scanner(System.in);
		
		do {
			Pessoa cliente = pDAO.SelectOnlyPersonDAO(cpfCliente);
			System.out.println("CLIENTE A SER ALTERADO:");

			String srtCliente ="---------------------------------------"
					   +"\n#\t1. Nome: "+cliente.getNome()
					   +"\n#\t* CPF: "+cliente.getCpf()
					   +"\n#\t2. Idade: "+cliente.getIdade()
					   +"\n#\t3. Sexo: "+cliente.getSexo()
					   +"\n#\t================== CONTA ==================="
					   +"\n#\t* Numero da conta: "+cliente.getConta().getIdConta()
				   	   +"\n#\t4. Limite da Conta: "+cliente.getConta().getLimite()
					   +"\n#\t* Saldo em Conta: R$ "+cliente.getConta().getSaldo()
					   +"\n#\t================ 5. ENDEREÇO =================="
					   +"\n#\t* Rua: "+cliente.getEndereco().getRua()
					   +"\n#\t* Numero: "+cliente.getEndereco().getNumero()
					   +"\n#\t* Complemento: "+cliente.getEndereco().getComplemento()
					   +"\n-----------------"
					   + "\n#\t8. Sair"
					   + "\n";	
			
			System.out.println(srtCliente);
			atualizado++;
			System.out.println("-> Qual campo deseja alterar?");
			campoAlterar = sc.nextInt();
			sc.nextLine();
			
			switch (campoAlterar) {
			case 1: //OK
				System.out.println("Digite o novo nome: ");
				dadoAlterado = sc.nextLine();
				pDAO.updatePersonDAO(cliente.getCpf(), "nome",dadoAlterado);
				break;
				
			case 2: // OK
				System.out.println("Digite a nova idade: ");
				dadoAlterado = sc.nextLine();
				pDAO.updatePersonDAO(cliente.getCpf(), "idade",dadoAlterado);
				break;

			case 3: // OK
				System.out.println("Digite o novo sexo: ");
				dadoAlterado = sc.nextLine();
				pDAO.updatePersonDAO(cliente.getCpf(), "sexo",dadoAlterado);
				break;
			case 4:
				//ALTERAR LIMITE DA CONTA
				System.out.println("Digite o novo limite da conta: ");
				dadoAlterado = sc.nextLine();
				cDAO.updateContaDAO(cliente, "limite" ,dadoAlterado);
				break;
			case 5:
				//ALTERAR RUA
				String ruaAlterar;
				int numeroAlterar;
				String complementoAlterar;
				
				System.out.println("Digite o novo endereco do cliente: ");
				System.out.println("Rua:");
				ruaAlterar = sc.nextLine();
				System.out.println("Numero: ");
				numeroAlterar = sc.nextInt();
				sc.nextLine();
				System.out.println("Complemento: (CASA / APT )");
				complementoAlterar = sc.nextLine();
				
				endDAO.updateEnderecoDAO(cliente, ruaAlterar, numeroAlterar, complementoAlterar);
				
				break;
			case 8:
				sair= true;
				System.out.println("Voltando ao menu principal...");
				break;
				
			default:
				System.out.println("Opção invalida, tente novamente!");
				break;
			}
			
		} while (sair!=true); 
		

		
		
	}
	
}
