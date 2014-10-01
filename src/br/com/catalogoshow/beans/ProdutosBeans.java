package br.com.catalogoshow.beans;

import br.com.catalogoshow.dominio.Produtos;
import br.com.catalogoshow.servico.ProdutosServicos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="produtosBeans")
@RequestScoped
public class ProdutosBeans implements Serializable {
	private static final long serialVersionUID = -7423756338487510646L;

	private Produtos produtos;
	
	private ProdutosServicos produtosServicos;
	
	private List<Produtos> listaProdutos;
	
	public void ProdutosBean(){
		this.inicializaProdutos();	
	}
	
	public void saveProdutos(){
		//
		produtos.setIdProduto(0);
		this.getProdutosServicos().saveProdutos(produtos);
		this.inicializaProdutos();
	}
    
	public void updateProdutos(){
		this.getProdutosServicos().getProdutos();
		this.inicializaProdutos();
		
	}
	
	public void deleteProdutos(){
		
		this.inicializaProdutos();
		
	}
	
	public void consultaProdutos(){
		
	}
    
	public void inicializaProdutos(){
		produtos = new Produtos();
		listaProdutos = new ArrayList<>();
		//
		produtos.setIdProduto(0);
	}
	
	public ProdutosServicos getProdutosServicos() {
		if (this.produtosServicos == null){
			this.setProdutosServicos(new ProdutosServicos());
		}
		return produtosServicos;
	}

	public void setProdutosServicos(ProdutosServicos produtosServicos) {
		this.produtosServicos = produtosServicos;
	}

	public Produtos getProdutos() {
		if (produtos==null){
			this.setProdutos(new Produtos());
		}
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	public List<Produtos> getListaProdutos() {
		return listaProdutos;
	}
	
	public void setListaProdutos(List<Produtos> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
}