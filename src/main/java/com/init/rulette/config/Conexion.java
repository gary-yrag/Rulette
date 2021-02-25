package com.init.rulette.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	Connection con;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl", user = "GESTIONSALUD", pass = "GESTIONSALUD";

	/**
	 * Conection class
	 * @return conection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Connection conectar() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,user,pass);
		return con;
	}
}
