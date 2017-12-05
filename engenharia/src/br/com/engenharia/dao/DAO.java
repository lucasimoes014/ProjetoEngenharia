package br.com.engenharia.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	public Connection getConexao() {
		Connection conexao = null;
		String usuario = "root";
		String senha = "100";
		String nomeBancoDados = "projetoeng";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nomeBancoDados,
					 usuario, senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}