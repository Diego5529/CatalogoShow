package br.com.catalogoshow.dominio;

import java.io.Serializable;
import java.util.Date;

public class Produtos implements Serializable {
	

	private static final long serialVersionUID = -6657999664766687446L;
	
	private Integer idProduto;
	private String nomeProduto;
	private String descricaoProduto;
	private String imagemProduto;
	private Integer tipoProduto;
	private Integer categoriaProduto;
	private Integer empresaProduto;
	
	public Produtos() {
	}

	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public String getImagemProduto() {
		return imagemProduto;
	}
	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}

	public Integer getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(Integer tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public Integer getCategoriaProduto() {
		return categoriaProduto;
	}
	public void setCategoriaProduto(Integer categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}
	public Integer getEmpresaProduto() {
		return empresaProduto;
	}
	public void setEmpresaProduto(Integer empresaProduto) {
		this.empresaProduto = empresaProduto;
	}
}