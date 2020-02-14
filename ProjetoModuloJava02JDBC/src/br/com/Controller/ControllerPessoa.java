package br.com.Controller;

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
		System.out.println("Login:");
		p.setLogin(sc.nextLine());
		System.out.println("Senha:");
		p.setPassword(sc.nextLine());
		System.out.println("Nivel de acesso: (1) Administrativo / (2) Cliente");
		p.setNivelAcesso(sc.nextInt());
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

	public void SelectAllPerson() {
		
		int numeroDeClientes = 0;
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
			numeroDeClientes++;
			}
			System.out.println("=========================");
			System.out.println("CLIENTES CADASTRADOS: "+numeroDeClientes);
			System.out.println("=========================");
			
		} else {
			
			System.out.println("Nenhum cliente Cadastrado.");
		}

		
	}

	public void SelectOnlyPerson(String cpfCliente){
		
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
					   +"\n";
			
		System.out.println(srtCliente);
		
	}

	public void DeletePerson (String cpfCliente) {
		
		PessoaDAO pDAO = new PessoaDAO();
		ControllerEndereco controlEnd = new ControllerEndereco();
		Pessoa pessoa = pDAO.SelectOnlyPersonDAO(cpfCliente);
		
		pDAO.DeletePersonDAO(cpfCliente);
		controlEnd.deleteEndereco(pessoa);
		
		System.out.println("Cliente: "+pessoa.getNome()+" excluido com sucesso!!");
	}
	
	public void updatePerson(String cpfCliente) {
		
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
				// ALTERAR NOME
				System.out.println("Digite o novo nome: ");
				dadoAlterado = sc.nextLine();
				pDAO.updatePersonDAO(cliente.getCpf(), "nome",dadoAlterado);
				break;
				
			case 2: // OK
				// ALTERAR IDADE
				System.out.println("Digite a nova idade: ");
				dadoAlterado = sc.nextLine();
				pDAO.updatePersonDAO(cliente.getCpf(), "idade",dadoAlterado);
				break;

			case 3: // OK
				// ALTERAR SEXO
				System.out.println("Digite o novo sexo: ");
				dadoAlterado = sc.nextLine();
				pDAO.updatePersonDAO(cliente.getCpf(), "sexo",dadoAlterado);
				break;
			case 4:
				// ALTERAR LIMITE DA CONTA
				ControllerConta controlConta = new ControllerConta();
				controlConta.updateConta(cliente);
				break;
			case 5:
				// ALTERAR ENDEREÇO
				 ControllerEndereco controlEndereco = new ControllerEndereco();
				controlEndereco.updateEndereco(cliente);
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
	
	public Pessoa login(int nivel) {

		Scanner sc = new Scanner(System.in);
		
		String login, senha;
		System.out.println("Entre com seu login e senha");
		System.out.println("Login: ");
		login = sc.nextLine();
		System.out.println("Senha:");
		senha = sc.nextLine();
		PessoaDAO pDAO = new PessoaDAO();
		Pessoa p = pDAO.efetuarLoginDAO(login, senha);
		
		if(p != null) {
			// LIBERA TODO SISTEMA AO NIVEL DE ACESSO 1
			if(p.getNivelAcesso() == 1) {
				return p;
			}
			if (p.getNivelAcesso() != nivel) {
				System.out.println("Desculpe, você não tem permissão ao menu admistrativo.");
				return null;
			}
			return p;
		}else {
			System.out.println("Dados incorreto!!");
		}
		
		return null;
		
	}
}
