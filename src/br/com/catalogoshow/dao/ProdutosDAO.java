package br.com.catalogoshow.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.com.catalogoshow.dominio.Produtos;
import br.com.catalogoshow.banco.ComandosSQL;
import br.com.catalogoshow.banco.ConectaBanco;

public class ProdutosDAO implements Serializable {	
	// conexão com o banco de dados ...
	
	private static final long serialVersionUID = 8255667107681326838L;
	/**
	 * Classe Connection - Responsável pela conexão com o banco de Dados. 
	 */
	private Connection conn=null;
	
	/*
	 * PreparedStatement - responsável pela criação - preparação da query 
	 * que irá realizar a operação no banco de dados. 
	 * 
	 * 
	 */
	
	private PreparedStatement stmt = null;
	
	/**
	 * ResultSet - guarda o resultado de uma pesquisa numa estrutura de dados 
	 * que pode ser percorrida, de forma que você possa ler os dados do banco. 
	 * 
	 */
	private ResultSet resultSetProdutos = null;
	private Produtos produtos = null;
	
	/*
	 * Propeerties - O arquivo de propriedade é uma ótima opção para passar configurações 
	 * para uma determinada aplicação que necessitada de configurações externas e a mesma 
	 * em si não pode ser alterada.
	 */
	private Properties propertiesProdutos = null;
	
	
	private boolean sucesso;

	private final static boolean VERDADEIRO = true ;
    private final static boolean FALSO   = false; 
    
    private String buscaProdutos;
	private String insertProdutos;
	private String updateProdutos;
	private String deleteProdutos;
	private String buscaListaProdutos;
	
	public ProdutosDAO() {

	}
	
	
	public boolean saveProdutos( Produtos produtos ){
		this.getConectaBanco();
		try {
			if (this.getConn() != null || !this.getConn().isClosed())  {
				this.getConn().setAutoCommit(false);
				this.setStmt(this.getBuscaProdutos());
   			    this.getStmt().setInt(1, produtos.getIdProduto());
   			    
   			    this.setResultSetProdutos(this.getStmt().executeQuery());
   			    
	 		    if ( ! this.getResultSetProdutos().next() ) {
	 			    this.setStmt(this.getInsertProdutos());
	 			    this.getStmt().setString(1, produtos.getNomeProduto());
	 			    this.getStmt().setString(2, produtos.getDescricaoProduto());
	 			    this.getStmt().setString(3, produtos.getImagemProduto());
	 			   this.getStmt().setInt(4, produtos.getCategoriaProduto());
	 			  this.getStmt().setInt(5,produtos.getTipoProduto());
	 			  this.getStmt().setInt(6, produtos.getEmpresaProduto());
		 		    this.getStmt().execute();
	 	            this.setSucesso(VERDADEIRO);
	 	            this.getConn().commit();
	 		   }
	 		}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
	        if (this.getConn()!= null ){
				try {
					this.getConn().rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        }	
	    	this.stopConectaBanco();
	    }
		return this.isSucesso();
	}

	
	public boolean updateProdutos(Produtos produtos) {
		   this.getConectaBanco();
		   try {
		 		if (this.getConn() != null || !this.getConn().isClosed())  {
		 		   this.getConn().setAutoCommit(false);
		 		   this.setStmt(this.getUpdateProdutos());
		 		 
		           this.getStmt().execute();
		           this.setSucesso(VERDADEIRO);
		           this.getConn().commit();
		 		} 
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    } finally {
		        if (this.getConn()!= null ){
		        	try {
						this.getConn().rollback();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		        }
		    	this.stopConectaBanco();
	      }
		return this.isSucesso();
	}
	
	
	public boolean deleteProdutos(Produtos produtos){
		this.getConectaBanco();
    	try {
	 		if (this.getConn()!= null || !this.getConn().isClosed())  {
	 		   this.getConn().setAutoCommit(false);
	 		   this.setStmt(this.getDeleteProdutos());
	 		   this.getStmt().setInt(1, produtos.getIdProduto());
	           this.getStmt().execute();
	           this.setSucesso(VERDADEIRO);
	           this.getConn().commit();
	 		} 
	 		 
	    } catch (SQLException e) {
	         e.printStackTrace();    
	    } finally {
	    	 if (this.getConn()!= null ){
		         try {
					this.getConn().rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		     }
	    	this.stopConectaBanco();
	   }
    	return this.isSucesso();
	}

	public Produtos buscaProdutos(int idProduto){
		produtos = this.getProdutos();
		this.getConectaBanco();
		try {
   			if (this.getConn() != null || !this.getConn().isClosed())  {
   			    this.getConn().setAutoCommit(false);
   			    this.setStmt(this.getBuscaProdutos());
   			    this.getStmt().setInt(1, idProduto);
   	    	    this.setResultSetProdutos(this.getStmt().executeQuery());
	 		    if ( this.getResultSetProdutos().next() ) {
	 		    	produtos.setIdProduto(this.getResultSetProdutos().getInt("idProduto"));
	 			    produtos.setNomeProduto(this.getResultSetProdutos().getString("nomeProduto"));
	 			    produtos.setDescricaoProduto(this.getResultSetProdutos().getString("descricaoProduto"));
	 			    produtos.setImagemProduto(this.getResultSetProdutos().getString("imagemProduto"));
	 			    produtos.setTipoProduto(this.getResultSetProdutos().getInt("tipoProduto"));
	 			    produtos.setCategoriaProduto(this.getResultSetProdutos().getInt("categoriaProduto"));
	 			    produtos.setEmpresaProduto(this.getResultSetProdutos().getInt("empresaProduto"));
	 			   }    
	 		    this.getConn().commit();
	 		    }
   		} catch (SQLException e) {
            e.printStackTrace();
     	} finally {
	        if (this.getConn()!= null ){
	        	try {
					this.getConn().rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
    		this.stopConectaBanco();
     	} 
      	return produtos;
	}
	
	public List<Produtos> listaProdutos()  {
		this.getConectaBanco();
		List<Produtos> listaProdutos = new ArrayList<Produtos>();
		try {
			if (this.getConn() != null || !this.getConn().isClosed())  {
				this.setStmt(this.getBuscaListaProdutos());
				this.setResultSetProdutos(this.getStmt().executeQuery());
				this.getConn().setAutoCommit(false);
				while (this.getResultSetProdutos().next()) {
	                Produtos produtos = new Produtos ();
					produtos.setIdProduto(this.getResultSetProdutos().getInt("idProduto"));
					listaProdutos.add(produtos);
				}
				this.getConn().commit();
			}
		} catch (SQLException e) {
              e.printStackTrace();
              if (this.getConn()!= null ){
  				try {
  					this.getConn().rollback();
  				} catch (SQLException e1) {
  					e1.printStackTrace();
  				}
  			}
		} finally {
			this.stopConectaBanco();
		}
		return listaProdutos;
	}
	
	
	private void getConectaBanco()  { 
		this.lerComandosSqlProdutos();
		this.setConn(ConectaBanco.getInstance().getConexao());
		this.setSucesso(FALSO);
	}


	private void stopConectaBanco()  {
		ConectaBanco.fecharRs(this.getResultSetProdutos());
		ConectaBanco.fecharStmt(this.getStmt());
		ConectaBanco.fecharConexao(this.getConn());
	}

	
	private void lerComandosSqlProdutos(){ 
		this.setPropertiesProdutos(ComandosSQL.getInstance().getComandosSQL());
		this.setBuscaListaProdutos(this.getPropertiesProdutos().getProperty("produtos.buscaLista"));
		this.setInsertProdutos(this.getPropertiesProdutos().getProperty("produtos.insert"));
        this.setUpdateProdutos(this.getPropertiesProdutos().getProperty("produtos.update"));		
        this.setDeleteProdutos(this.getPropertiesProdutos().getProperty("produtos.delete"));
        this.setBuscaProdutos(this.getPropertiesProdutos().getProperty("produtos.busca"));
		
	}
	
	private Connection getConn() {
		return conn;
	}
	
	private void setConn(Connection conn) {
		this.conn = conn;
	}
	
	private PreparedStatement getStmt() {
		return stmt;
	}
	
	private void setStmt(String stmt) {
		try {
			this.stmt = this.getConn().prepareStatement(stmt);
		} catch (SQLException e) {
		   e.printStackTrace();
		};
	}
	
	private ResultSet getResultSetProdutos() {
		return resultSetProdutos;
	}
	
	private void setResultSetProdutos(ResultSet resultSetProdutos) {
		this.resultSetProdutos = resultSetProdutos;
	}
	
	private Produtos getProdutos() {
		if (this.produtos==null){
			this.setProdutos(new Produtos());
		}
		return produtos;
	}
	
	private void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	private Properties getPropertiesProdutos() {
		return propertiesProdutos;
	}
	
	private void setPropertiesProdutos(Properties propertiesProdutos) {
		this.propertiesProdutos = propertiesProdutos;
	}
	
	private boolean isSucesso() {
		return sucesso;
	}
	
	private void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	
	private String getBuscaProdutos() {
		return buscaProdutos;
	}
	
	private void setBuscaProdutos(String buscaProdutos) {
		this.buscaProdutos = buscaProdutos;
	}
	
	private String getInsertProdutos() {
		return insertProdutos;
	}
	
	private void setInsertProdutos(String insertProdutos) {
		this.insertProdutos = insertProdutos;
	}
	
	private String getUpdateProdutos() {
		return updateProdutos;
	}
	
	private void setUpdateProdutos(String updateProdutos) {
		this.updateProdutos = updateProdutos;
	}
	
	private String getDeleteProdutos() {
		return deleteProdutos;
	}
	
	private void setDeleteProdutos(String deleteProdutos) {
		this.deleteProdutos = deleteProdutos;
	}
	
	private String getBuscaListaProdutos() {
		return buscaListaProdutos;
	}
	
	private void setBuscaListaProdutos(String buscaListaProdutos) {
		this.buscaListaProdutos = buscaListaProdutos;
	}
}