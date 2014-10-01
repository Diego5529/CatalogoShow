package br.com.catalogoshow.servico;

import java.io.Serializable;

import br.com.catalogoshow.dominio.Produtos;
import br.com.catalogoshow.dto.ProdutosDTO;
import br.com.catalogoshow.util.JsfUtil;

public class ProdutosServicos implements Serializable {
	
	private static final long serialVersionUID = 4063054648924688557L;


		private static boolean SAVE_RECORD = true;
		private static boolean UPDATE_RECORD = true;
		private static boolean DELETE_RECORD = true;
		private static boolean EXIST_RECORD = true;
		private static boolean NO_EXIST_RECORD = false;

		private Produtos produtos;
		private ProdutosDTO produtosDTO;

		public ProdutosServicos() {
			
			this.setProdutos(new Produtos());
			this.setProdutosDTO(new ProdutosDTO());

		}

		public boolean existeProdutos(int idProduto) {
			this.setProdutos(this.getProdutosDTO().buscaProdutos(idProduto));
			//
			if (this.getProdutos() != null && 
					this.getProdutos().getIdProduto() != null && 
					this.getProdutos().getIdProduto() > 0) {
				return EXIST_RECORD;
			} else {
				return NO_EXIST_RECORD;
			}
		}

		public boolean saveProdutos(Produtos produtos) {
			//
			if (produtos != null && existeProdutos(produtos.getIdProduto()) == NO_EXIST_RECORD) {
				this.getProdutosDTO().saveProdutos(produtos);
			    return SAVE_RECORD;
			}
			JsfUtil.addErrorMessage("Jรก Existe um Produto com o id Informado");
			return true;
		}

		public boolean updateProduto() {
			return UPDATE_RECORD;
		}

		public boolean deleteProduto() {
			return DELETE_RECORD;
		}

		public Produtos getProdutos() {
			if (this.produtos == null) {
				this.setProdutos(new Produtos());
			}
			return this.produtos;
		}

		public void setProdutos(Produtos produtos) {
			this.produtos = produtos;
		}

		public ProdutosDTO getProdutosDTO() {
			if (this.produtosDTO == null) {
				this.setProdutosDTO(new ProdutosDTO());
			}
			return produtosDTO;
		}

		public void setProdutosDTO(ProdutosDTO produtosDTO) {
			this.produtosDTO = produtosDTO;
		}

	}