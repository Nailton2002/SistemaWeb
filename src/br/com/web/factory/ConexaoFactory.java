package br.com.web.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
private static final String USUARIO = "root";
private static final String SENHA = "2002";
private static final String URL = "jdbc:mysql://localhost:3306/sistema";

public static Connection conectar() throws SQLException{
	//REFERENCIA AO DRIVER MYSQL PARA VERSOES ANTIGAS DO JAVA
	DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
	
	Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
	return conexao;
}

public static void main(String[] args) {
	try{
	@SuppressWarnings("unused")
	Connection conexao = ConexaoFactory.conectar();
	System.out.println("Conectado com sucesso!!");
	}
	
	catch(SQLException ex){
		ex.printStackTrace();
		System.out.println("Conexão falhou!!" + ex);
		
	}
}

}
