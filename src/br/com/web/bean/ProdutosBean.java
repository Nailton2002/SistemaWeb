package br.com.web.bean;

import br.com.web.dao.FornecedoresDao;
import br.com.web.dao.ProdutoDao;
import br.com.web.domain.Fornecedores;
import br.com.web.domain.Produtos;
import br.com.web.util.JSFUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//ESTA É A CLASSE QUE FAZ COMUNICAÇÃO COM AS PÁGINAS WEB
@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutosBean {

	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	// VARIAVEL PARA O COMBOBOX
	private ArrayList<Fornecedores> comboFornecedores;

	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			ProdutoDao fdao = new ProdutoDao();
			itens = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {

		try {
			produtos = new Produtos();
			FornecedoresDao dao = new FornecedoresDao();
			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void novo() {
		try {
			ProdutoDao fdao = new ProdutoDao();
			fdao.salvar(produtos);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!!!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ProdutoDao fdao = new ProdutoDao();
			fdao.excluir(produtos);

			JSFUtil.adicionarMensagemSucesso("Produtos excluido com sucesso!!!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void editar() {
		try {
			ProdutoDao fdao = new ProdutoDao();
			fdao.editar(produtos);

			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!!!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage");
			e.printStackTrace();
		}

	}

	public void prepararEditar() {

		try {
			produtos = new Produtos();
			FornecedoresDao dao = new FornecedoresDao();
			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

}
