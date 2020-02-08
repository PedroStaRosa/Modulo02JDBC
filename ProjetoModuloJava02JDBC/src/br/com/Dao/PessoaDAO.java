package br.com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Model.Conta;
import br.com.Model.Endereco;
import br.com.Model.Pessoa;
import br.com.Model.Util.ConnectionBD;


public class PessoaDAO {

	private Connection connection;
	
	public PessoaDAO() throws SQLException {
		this.connection = new ConnectionBD().getConnection();
	}

	public void insertPersonDAO (Pessoa pessoa) {
		
		String sql = "insert into pessoa (cpf,nome,idade,sexo,numero_conta,id_endereco)"
				+ "values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, pessoa.getCpf());
			stmt.setString(2, pessoa.getNome());
			stmt.setInt(3, pessoa.getIdade());
			stmt.setString(4, pessoa.getSexo());
			stmt.setInt(5, pessoa.getConta().getIdConta());
			stmt.setInt(6, pessoa.getEndereco().getIdEndereco());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {	
			throw new RuntimeException(e);
		}
		
	}

	public List<Pessoa> SelectAllPersonDAO() throws SQLException {
		
		String sqlSelect = "Select * from Pessoa " + "order by nome";
		
		List<Pessoa> pessoaList = new ArrayList<Pessoa>();
		
		PreparedStatement stmt = this.connection.prepareStatement(sqlSelect);
		
		ResultSet result = stmt.executeQuery();
		
			while(result.next()) {
				
				Pessoa pessoa = new Pessoa();
	
				pessoa.setCpf(result.getString("cpf"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setIdade(result.getInt("idade"));
				pessoa.setSexo(result.getString("sexo"));
				
				// MONTAR CONTA DO CLIENTE
				
				/* No resultado fazer a busca pelo ContaDAO
				 * para montar um objeto conta e setar o numero da conta em pessoa pelo
				 * resultado */
				Conta conta = new Conta();
				ContaDAO contaDAO = new ContaDAO();
				Conta contaRetorno = new Conta();
				
				conta.setIdConta(result.getInt("numero_conta"));
	
				contaRetorno.setIdConta(contaDAO.selectContaPessoa_Conta(conta).getIdConta());
				contaRetorno.setLimite(contaDAO.selectContaPessoa_Conta(conta).getLimite());
				contaRetorno.setSaldo(contaDAO.selectContaPessoa_Conta(conta).getSaldo());
				
				pessoa.setConta(contaRetorno);
				
				//###### MONTAR ENDEREÇO DO CLIENTE ######################
				
				Endereco endereco = new Endereco();
				EnderecoDAO endDAO = new EnderecoDAO();
				Endereco enderecoRetorno = new Endereco();
				
				endereco.setIdEndereco(result.getInt("id_Endereco"));
				enderecoRetorno.setRua(endDAO.selectEnderecoPessoa(endereco).getRua());
				enderecoRetorno.setNumero(endDAO.selectEnderecoPessoa(endereco).getNumero());
				enderecoRetorno.setComplemento(endDAO.selectEnderecoPessoa(endereco).getComplemento());
				
				pessoa.setEndereco(enderecoRetorno);
				
				// ADD PESSOA
				pessoaList.add(pessoa);
				
			}
		stmt.close();
		//connection.close();
		return pessoaList;
			
		}
	
	public Pessoa SelectOnlyPersonDAO(String cpfCliente) throws SQLException {
		
		String sqlSelectOnlyPerson = "Select * from Pessoa " + "where cpf = "+cpfCliente+"";
		
		// Pessoa cliente = new Pessoa();
		Pessoa pessoa = new Pessoa();
		
		PreparedStatement stmt = this.connection.prepareStatement(sqlSelectOnlyPerson);
		
		ResultSet result = stmt.executeQuery();
		
			while(result.next()) {
				
				
	
				pessoa.setCpf(result.getString("cpf"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setIdade(result.getInt("idade"));
				pessoa.setSexo(result.getString("sexo"));
				
				// MONTAR CONTA DO CLIENTE
				
				/* No resultado fazer a busca pelo ContaDAO
				 * para montar um objeto conta e setar o numero da conta em pessoa pelo
				 * resultado */
				Conta conta = new Conta();
				ContaDAO contaDAO = new ContaDAO();
				Conta contaRetorno = new Conta();
				
				conta.setIdConta(result.getInt("numero_conta"));
				contaRetorno.setIdConta(contaDAO.selectContaPessoa_Conta(conta).getIdConta());
				contaRetorno.setLimite(contaDAO.selectContaPessoa_Conta(conta).getLimite());
				contaRetorno.setSaldo(contaDAO.selectContaPessoa_Conta(conta).getSaldo());
				
				pessoa.setConta(contaRetorno);
				
				//###### MONTAR ENDEREÇO DO CLIENTE ######################
				
				Endereco endereco = new Endereco();
				EnderecoDAO endDAO = new EnderecoDAO();
				Endereco enderecoRetorno = new Endereco();
				
				endereco.setIdEndereco(result.getInt("id_Endereco"));
				enderecoRetorno.setIdEndereco(endDAO.selectEnderecoPessoa(endereco).getIdEndereco());
				enderecoRetorno.setRua(endDAO.selectEnderecoPessoa(endereco).getRua());
				enderecoRetorno.setNumero(endDAO.selectEnderecoPessoa(endereco).getNumero());
				enderecoRetorno.setComplemento(endDAO.selectEnderecoPessoa(endereco).getComplemento());
				
				pessoa.setEndereco(enderecoRetorno);
				
				// ADD PESSOA

				
			}
		stmt.close();
		//connection.close();
		return pessoa;
			
		} 
	
	public void DeletePersonDAO(String cpfCliente) throws SQLException {
		
		String sqlDeletePerson = "Delete from Pessoa " + "where cpf = "+cpfCliente+"";

		try {
			
			PreparedStatement stmt = this.connection.prepareStatement(sqlDeletePerson);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePersonDAO(String cpfCLiente, String campoAlterado, String dadoAlterado) throws SQLException {
		
			String sql = "update pessoa set "+campoAlterado+" = '"+dadoAlterado+"' where cpf = "+cpfCLiente+"";
			//System.out.println("sql: "+sql);
			System.out.println("Cliente alterado com sucesso!");
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		
	}
	
}
