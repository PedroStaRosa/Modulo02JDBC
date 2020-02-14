package br.com.Controller;


import java.util.Scanner;

import br.com.Dao.EnderecoDAO;
import br.com.Model.Endereco;
import br.com.Model.Pessoa;


public class ControllerEndereco {

	//FUNCTION
		public Endereco insertEndereco (Endereco endereco) {
			
			EnderecoDAO endDAO;
			Endereco enderecoRetorno = new Endereco();
			endDAO = new EnderecoDAO();	
			enderecoRetorno.setIdEndereco(endDAO.insertEnderecoDAO(endereco).getIdEndereco());
		
			return enderecoRetorno;
			
		}	
	
		public void updateEndereco (Pessoa pessoa) {
			Scanner sc = new Scanner(System.in);
			EnderecoDAO endDAO = new EnderecoDAO();
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
			
			endDAO.updateEnderecoDAO(pessoa, ruaAlterar, numeroAlterar, complementoAlterar);
			
		}

		public void deleteEndereco (Pessoa pessoa) {
			EnderecoDAO endDAO = new EnderecoDAO();
			endDAO.DeleteEnderecoDAO(pessoa);
		}
		
}
