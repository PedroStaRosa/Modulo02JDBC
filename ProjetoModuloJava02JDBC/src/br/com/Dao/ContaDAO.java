package br.com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.Model.Conta;
import br.com.Model.Pessoa;
import br.com.Model.Util.ConnectionBD;


public class ContaDAO {

	private Connection connection;
	
	public ContaDAO() throws SQLException {
		this.connection = new ConnectionBD().getConnection();
	}

	public Conta selectContaPessoa_Conta(Conta conta) throws SQLException {
		
		Conta ct = new Conta();
		String sql = "select * from conta where idConta = "+conta.getIdConta()+"";
		
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		
		ResultSet result = stmt.executeQuery();
		
		while(result.next()) {

			ct.setIdConta(result.getInt("idconta"));
			ct.setLimite(result.getInt("limite"));
			ct.setSaldo(result.getInt("saldo"));

		}

		return ct;
		
	}
	
	public void insertContaDAO (Conta conta) {
		
		String sql = "insert into conta (idconta,limite,saldo)"
				+ "values (?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			

			stmt.setInt(1, conta.getIdConta());
			stmt.setDouble(2, conta.getLimite());
			stmt.setDouble(3, conta.getSaldo());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {	
			throw new RuntimeException(e);
		}
		
	}
	
	public void updateContaDAO(Pessoa pessoaConta, String campoAlterado, String dadoAlterado) throws SQLException {
		String sql =  "update conta set "+campoAlterado+" = '"+dadoAlterado+"' where idConta = "+pessoaConta.getConta().getIdConta()+"";
		System.out.println("sql: "+sql);
		System.out.println("Cliente alterado com sucesso!");
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.execute();
		stmt.close();
		
	}
}
