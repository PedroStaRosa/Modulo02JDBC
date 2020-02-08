package br.com.Controller;


import br.com.Dao.EnderecoDAO;
import br.com.Model.Endereco;


public class ControllerEndereco {

	//FUNCTION
		public Endereco insertEndereco (Endereco endereco) {
			
			EnderecoDAO endDAO;
			Endereco enderecoRetorno = new Endereco();
			endDAO = new EnderecoDAO();	
			enderecoRetorno.setIdEndereco(endDAO.insertEnderecoDAO(endereco).getIdEndereco());
		
			return enderecoRetorno;
			
		}	
	
}
