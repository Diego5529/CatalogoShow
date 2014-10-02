package br.com.catalogoshow.banco;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ComandosSQL {

	private Properties propertiesSQL = null;

	private static ComandosSQL comandosSQL = new ComandosSQL();

	private ComandosSQL() {
	}

	public static ComandosSQL getInstance() {
		return comandosSQL;
	}

	public synchronized Properties getComandosSQL() {
		this.getPropertiesSQL();
		try {
			InputStream fisSQL = getClass().getClassLoader()
					.getResourceAsStream(
							"br/com/catalogoshow/banco/sql.properties");
			this.getPropertiesSQL().load(fisSQL);
			fisSQL.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.getPropertiesSQL();
	}

	public synchronized Properties getStartOID() {
		this.getPropertiesSQL();
		try {
			InputStream fisSQL = getClass().getClassLoader()
					.getResourceAsStream("com/mieeweb/banco/oid.properties");
			this.getPropertiesSQL().load(fisSQL);
			fisSQL.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.getPropertiesSQL();
	}

	private Properties getPropertiesSQL() {
		if (this.propertiesSQL == null) {
			this.setPropertiesSQL(new Properties());
		}
		return propertiesSQL;
	}

	private void setPropertiesSQL(Properties propertiesSQL) {
		this.propertiesSQL = propertiesSQL;
	}
}