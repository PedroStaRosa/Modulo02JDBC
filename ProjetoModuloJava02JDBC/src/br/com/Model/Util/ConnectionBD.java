package br.com.Model.Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBD {

	/*
	// notebook
	String bd = "jdbc:mysql://localhost/bancofuctura?autoReconnect=true&useSSL=false";
	String user = "root";
	String password = "@Gt0809011";
	*/
	
	/*
	// PC Casa
	String bd = "jdbc:mysql://localhost/cursofuctura?autoReconnect=true&useSSL=false";
	String user = "root";
	String password = "root";
	*/
	
	/**
	 *
	 * */
	
	// BANCO REMOTO
	String bd = "jdbc:mysql://remotemysql.com:3306/avzWm9VmyD?autoReconnect=true&useSSL=false";
	String user = "avzWm9VmyD";
	String password = "7pJbXRuiA0";
	
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(bd,user,password);
			
			if (conn != null) {
				// System.out.println("Banco Conectado.");
			}
		} catch (Exception e) {
			System.out.println("Erro na conexao com o banco: "+e.getMessage());
		}
		
		return conn;
	}
	
	public void fecharConn ( Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println("Erro ao fechar a conexao"+e.getMessage());
		}
	}
	
	
}
