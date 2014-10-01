package br.com.catalogoshow.dto;

import java.io.Serializable;
import java.util.List;

import br.com.catalogoshow.dominio.Produtos;
import br.com.catalogoshow.dao.ProdutosDAO;

public class ProdutosDTO implements Serializable {
	
	private static final long serialVersionUID = 2163766488751402111L;
	
	private ProdutosDAO produtosDAO;
	private static final boolean FALHA_NA_TRANSACAO = false;
	private static final boolean SUCESSO_NA_TRANSACAO = false;
	
	public ProdutosDTO(){}
	
	public ProdutosDAO getProdutosDAO() {
		if (this.produtosDAO == null){
			this.setProdutosDAO(new ProdutosDAO());
		}
		return produtosDAO;
	}
	
	public void setProdutosDAO(ProdutosDAO produtosDAO) {
		this.produtosDAO = produtosDAO;
	}	

    public boolean saveProdutos(Produtos produtos){
    	if (this.getProdutosDAO().saveProdutos(produtos) == SUCESSO_NA_TRANSACAO ) {
    		return SUCESSO_NA_TRANSACAO;
    	}
    	return FALHA_NA_TRANSACAO;
    }
    
    public boolean updateProdutos(Produtos produtos){
    	if (this.getProdutosDAO().updateProdutos(produtos) == SUCESSO_NA_TRANSACAO) {
    		return SUCESSO_NA_TRANSACAO;
    	}
    	return FALHA_NA_TRANSACAO; 
    }
    
    public boolean deleteProdutos (Produtos produtos){
    	if (this.getProdutosDAO().deleteProdutos(produtos) == SUCESSO_NA_TRANSACAO ) {
    		return SUCESSO_NA_TRANSACAO;
    	}	
    	return FALHA_NA_TRANSACAO;
    }
    
    public Produtos buscaProdutos(int idProduto) {
    	return this.getProdutosDAO().buscaProdutos(idProduto);
   	}
    
    public List<Produtos> ListaProdutos() {
    	return this.getProdutosDAO().listaProdutos();
	}
	 
}
