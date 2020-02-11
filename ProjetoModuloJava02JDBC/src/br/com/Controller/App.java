package br.com.Controller;

import java.util.Scanner;


public class App {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		/*Connection conn = new ConnectionBD().getConnection();
		System.out.println("Connection OK !!! :)");*/
	login();	
	//menuAdmistrativo();
	

	}
	
	
	public static void login() {
		
		String menu = "\n\t#Olá"
				+ "\n\t1. Menu Administrativo."
				+ "\n\t2. Menu Usuario."
				+ "\n\t3. Sair.";
		int escolha = 0;
		boolean sair = false;
		do {
			
			System.out.println(menu);
			escolha = sc.nextInt(); sc.nextLine();
			
			switch (escolha) {
			case 1:
				
				/**
				 * EFETUAR UM METODO DE AUTENTICAR O USUARIO
				 * */			
				menuAdmistrativo();
				break;
			case 2:
				System.out.println("Ola 02");
				break;
			case 3:
				//SAIR DO APP.
				sair = true;
				System.out.println("Bye!! Ate Logo =)");
				break;
			default:
				String messagemErro = "\t#Opção invalida."
						+ "\n\t#Escolha uma opção.";
				System.out.println(messagemErro);
				break;
			}
			
		} while (sair!=true);

		
	}
	
	
	public static void menuAdmistrativo () {
		
		
		boolean sair = false;
		int escolha = 0;
		String pesquisa="";
		ControllerPessoa controlPessoa = new ControllerPessoa();
		String menu ="---------MENU--------------------------"
				   +"\n#\t 1. Cadastrar Cliente."+"         #"
				   +"\n#\t 2. Alterar Cliente"+"            #"
				   +"\n#\t 3. Pesquisar Cliente"+"          #"
				   +"\n#\t 4. Remover Cliente"+"            #"
				   +"\n#\t 5. Listar todos clientes"+"      #"
				   +"\n----------------------------------------"
				   +"\n#\t 6. Fechar APP."+"                #"
				   +"\n----------------------------------------";
		
		do {
			
			System.out.println(menu);
			escolha = sc.nextInt();
			sc.nextLine();
			
			switch (escolha) {
			
			case 1:
				//CADASTAR CLIENTE.
					controlPessoa.insert();

			break;
			
			case 2:
				//ALTERAR DADOS DO CLIENTE
				System.out.println("Entre com o CPF do cliente a ser alterado:");
				pesquisa = sc.nextLine();
				controlPessoa.updatePerson(pesquisa);
				
			break;
			
			case 3:
				//PROCURA CLIENTE ESPECIFICO.
				System.out.println("Entre com o CPF do cliente:");
				pesquisa = sc.nextLine();
				controlPessoa.SelectOnlyPerson(pesquisa);
			
			break;
			
			case 4:
				// REMOVE CLIENTE.
				System.out.println("Entre com o CPF do cliente a ser deletado:");
				pesquisa = sc.nextLine();
				controlPessoa.DeletePerson(pesquisa);
				
			break;
			
			case 5:
				controlPessoa.SelectAllPerson();
				
			break;
			
			case 6:
				//SAIR DO APP.
				//sair = true;
				System.out.println("Bye!! Ate Logo =)");
				System.exit(0); // FECHAR APLICAÇÃO POR COMPLETO.
				
			break;
			
			default:
				System.out.println("Opção invalida, tente novamente!!");
			break;
			}
			
		} while (sair!=true);
		
	}

}
