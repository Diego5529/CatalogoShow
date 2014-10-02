package br.com.catalogoshow.banco;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DadosConexao {

	private String urlConexao = null;
	private String userConexao = null;
	private String passwordConexao = null;
	private String driverDataBase = null;
	private String dataBaseName = null;
	private String urlDataBase = null;
	private String createDB = null;

	private static DadosConexao pegaDadosConexao = new DadosConexao();

	private DadosConexao() {
		this.lerPropriedadesConexao();
	}

	public static DadosConexao getPegaDadosConexao() {

		return pegaDadosConexao;
	}

	public String getUrlConexao() {
		return urlConexao;
	}

	public void setUrlConexao(String urlConexao) {
		this.urlConexao = urlConexao;
	}

	public String getUserConexao() {
		return userConexao;
	}

	public void setUserConexao(String userConexao) {
		this.userConexao = userConexao;
	}

	public String getPasswordConexao() {
		return passwordConexao;
	}

	public void setPasswordConexao(String passwordConexao) {
		this.passwordConexao = passwordConexao;
	}

	public String getDriverDataBase() {
		return driverDataBase;
	}

	public void setDriverDataBase(String driverDataBase) {
		this.driverDataBase = driverDataBase;
	}

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	public synchronized void getDadosConexao() {
		this.lerPropriedadesConexao();
	}

	public String getUrlDataBase() {
		return urlDataBase;
	}

	public void setUrlDataBase(String urlDataBase) {
		this.urlDataBase = urlDataBase;
	}

	public String getCreateDB() {
		return createDB;
	}

	public void setCreateDB(String createDB) {
		this.createDB = createDB;
	}

	private void lerPropriedadesConexao() {
		Properties properties = new Properties();
		try {
			InputStream fis = getClass().getClassLoader().getResourceAsStream(
					"br/com/catalogoshow/banco/jdbc.properties");
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setUserConexao(properties.getProperty("jdbc.user"));
		this.setPasswordConexao(properties.getProperty("jdbc.pass"));
		this.setDriverDataBase(properties.getProperty("jdbc.driver"));
		this.setUrlConexao(properties.getProperty("jdbc.url"));
		this.setDataBaseName(properties.getProperty("jdbc.dataBase"));
		this.setUrlDataBase(properties.getProperty("jdbc.urlDataBase"));
		this.setCreateDB(properties.getProperty("jdbc.createDB"));
	}
}