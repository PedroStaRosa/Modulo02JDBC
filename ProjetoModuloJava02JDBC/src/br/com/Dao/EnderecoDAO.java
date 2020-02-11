package br.com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.Model.Endereco;
import br.com.Model.Pessoa;
import br.com.Model.Util.ConnectionBD;


public class EnderecoDAO {
	
	ConnectionBD conexao = new ConnectionBD();


	public Endereco selectEnderecoPessoa(Endereco end){
		
		Connection conn = conexao.getConnection();
		Endereco endereco = new Endereco();
		String sql1 = "select * from endereco where idendereco = "+end.getIdEndereco()+"";
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql1);
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {

				endereco.setIdEndereco(result.getInt("idEndereco"));
				endereco.setRua(result.getString("rua"));
				endereco.setNumero(result.getInt("numero"));
				endereco.setComplemento(result.getString("complemento"));
							
			}
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conexao.fecharConn(conn);
		}
		return endereco;
	}

	public Endereco insertEnderecoDAO (Endereco endereco) {
		
		String sql = "insert into endereco (Rua,numero,complemento)"
				+ "values (?,?,?)";
		
		Endereco enderecoPessoa = new Endereco();
		Connection conn = conexao.getConnection();
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, endereco.getRua());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getComplemento());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {	
			System.out.println("Erro ao inserir um endereço - ERRO: "+e.getMessage());
		}
		
		//String sql1 = "select MAX(e.idEndereco) AS idEndereco, e.rua, e.numero, e.complemento from endereco e";
		String sql1 = "select idendereco, rua, numero, complemento from endereco" + 
				" where idendereco = (select max(idendereco) from endereco)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql1);
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {

				enderecoPessoa.setIdEndereco(result.getInt("idEndereco"));
				enderecoPessoa.setRua(result.getString("rua"));
				enderecoPessoa.setNumero(result.getInt("numero"));
				enderecoPessoa.setComplemento(result.getString("complemento"));
							
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar maior ID(PK) - ERRO: "+e.getMessage());
		} finally {
			conexao.fecharConn(conn);;
		}
		
		return enderecoPessoa;
	}
	
	public void DeleteEnderecoDAO(Pessoa enderecoPessoa) {

		String sqlDeleteEndereco = "Delete from endereco " + "where idendereco = "+enderecoPessoa.getEndereco().getIdEndereco()+"";
		Connection conn = conexao.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlDeleteEndereco);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar endereço - ERRO: "+e.getMessage());
			
		} finally {
			conexao.fecharConn(conn);
		}
		
		
		
	}

	public void updateEnderecoDAO(Pessoa pessoaEndereco, String ruaAlterar, int numeroAlterar, String complementoAlterar ) {
		
		String sql =  "update endereco set rua = '"+ruaAlterar+"', numero = "+numeroAlterar+", complemento = '"+complementoAlterar+"' "+
				" where idEndereco = "+pessoaEndereco.getEndereco().getIdEndereco()+"";
		System.out.println("sql: "+sql);
		System.out.println("Cliente alterado com sucesso!");
		Connection conn = conexao.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao realizar a atualização do endereço - ERRO: "+e.getMessage());
		} finally {
			conexao.fecharConn(conn);
		}

		
	}
	
}
