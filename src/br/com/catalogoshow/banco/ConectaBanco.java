package br.com.catalogoshow.banco;

import java.beans.PropertyVetoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.catalogshow.banco.ComandosSQL;
import br.com.catalogshow.banco.DadosConexao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConectaBanco {

	private static ConectaBanco instance = new ConectaBanco();

	/**
	 * Classe de conexão com o banco de dados Gerenciando o Pool de Conexões,
	 * fazendo a comunixação da aplicação com o banco de dados através do
	 * servidor de aplicação.
	 * 
	 * TOMCAT, JBOSS, GLASSFISH
	 * 
	 */
	ComboPooledDataSource cpds;

	private ConectaBanco() {

	}

	public static ConectaBanco getInstance() {
		return instance;
	}

	private void configurarDataSource() {
		cpds = new ComboPooledDataSource();
		try {
			DadosConexao.getPegaDadosConexao().getDadosConexao();
			cpds.setDriverClass(DadosConexao.getPegaDadosConexao()
					.getDriverDataBase());
			cpds.setJdbcUrl(DadosConexao.getPegaDadosConexao().getUrlConexao());
			cpds.setUser(DadosConexao.getPegaDadosConexao().getUserConexao());
			cpds.setPassword(DadosConexao.getPegaDadosConexao()
					.getPasswordConexao());

			cpds.setMinPoolSize(1);
			cpds.setAcquireIncrement(10);
			cpds.setMaxPoolSize(10);

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public synchronized Connection getConexao() {
		if (cpds == null) {
			this.configurarDataSource();
		}
		try {
			return cpds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void fecharStmt(Statement ps) {
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fecharRs(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fecharConexao(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}