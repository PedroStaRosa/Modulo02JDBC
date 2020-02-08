package br.com.Controller;

import java.sql.SQLException;

import br.com.Dao.ContaDAO;
import br.com.Model.Conta;


public class ControllerConta {


	// FUNCTION
	public void insertConta (Conta conta) {
		
		ContaDAO ctDAO;
		ctDAO = new ContaDAO();
		conta.setSaldo(0);
		ctDAO.insertContaDAO(conta);

	}
	
}
