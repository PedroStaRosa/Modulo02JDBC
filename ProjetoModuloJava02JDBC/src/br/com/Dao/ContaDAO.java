package br.com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.Model.Conta;
import br.com.Model.Pessoa;
import br.com.Model.Util.ConnectionBD;


public class ContaDAO {

	ConnectionBD conexao = new ConnectionBD();


	public Conta selectContaPessoa_Conta(Conta conta) {
		
		Conta ct = new Conta();
		String sql = "select * from conta where idConta = "+conta.getIdConta()+"";
		Connection conn = conexao.getConnection();
		PreparedStatement stmt;
		try {
			
			stmt = conn.prepareStatement(sql);
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {

				ct.setIdConta(result.getInt("idconta"));
				ct.setLimite(result.getInt("limite"));
				ct.setSaldo(result.getInt("saldo"));
			}	
			
		} catch (SQLException e) {
			System.out.println("Erro ao selecioar a conta - ERRO: "+e.getMessage());
		} finally {
			
			conexao.fecharConn(conn);
			
		}
		
		return ct;
		
		
	}
	
	public void insertContaDAO (Conta conta) {
		
		String sql = "insert into conta (idconta,limite,saldo)"
				+ "values (?,?,?)";
		Connection conn = conexao.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			

			stmt.setInt(1, conta.getIdConta());
			stmt.setDouble(2, conta.getLimite());
			stmt.setDouble(3, conta.getSaldo());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {	
			System.out.println("Erro ao inserir uma conta - ERRO: "+ e.getMessage());
		} finally {
			conexao.fecharConn(conn);
		}
		
	}
	
	public void updateContaDAO(Pessoa pessoaConta, String campoAlterado, String dadoAlterado) {
		String sql =  "update conta set "+campoAlterado+" = '"+dadoAlterado+"' where idConta = "+pessoaConta.getConta().getIdConta()+"";
		System.out.println("sql: "+sql);
		Connection conn = conexao.getConnection();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao modificar conta - ERRO: "+e.getMessage());
		} finally {
			conexao.fecharConn(conn);
		}

		
	}
}
