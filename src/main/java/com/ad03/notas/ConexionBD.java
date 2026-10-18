package com.ad03.notas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBD {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/dbnotas"
			+ "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	private static final String USUARIO = "ad03_user";
	private static final String PASSWORD = "ad03_pass";

	private ConexionBD() {
	}

	public static Connection obtener() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, PASSWORD);
	}
}
