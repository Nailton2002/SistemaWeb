package br.com.web.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import javax.faces.model.ListDataModel;
import br.com.web.dao.FornecedoresDao;
import br.com.web.domain.Fornecedores;
import br.com.web.util.JSFUtil;

//ESTA É A CLASSE QUE FAZ COMUNICAÇÃO COM AS PÁGINAS WEB
@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	// VARIÁVEIS FORNECEDORES
	private Fornecedores fornecedores;
	// private ListDataModel<Fornecedores> itens;
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;

	// METÓDOS GETTES AND SETTES
	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	/*
	 * public ListDataModel<Fornecedores> getItens(){ return itens; }
	 * 
	 * public void setItens(ListDataModel<Fornecedores> itens) { this.itens = itens;
	 * }
	 */
	// ESTE METODO FAZ COMUNICAÇÃO COM O BANCO DE DADOS E COM A PÁGINA WEB
	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDao fdao = new FornecedoresDao();
			/* ArrayList<Fornecedores> lista */ itens = fdao.listar();
			// itens = new ListDataModel<Fornecedores>(lista);
		} catch (SQLException e) {
			// MOSTRA QUAL ERRO QUE ACONTECEU!!!
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}

	// ESTE METOD SERVE PARA SALVAR OS FORNECEDORES PELA PÁGINA WEG
	public void novo() {
		try {
			FornecedoresDao fdao = new FornecedoresDao();
			fdao.salvar(fornecedores);

			/* ArrayList<Fornecedores> */ itens = fdao.listar();
			// itens = new ListDataModel<Fornecedores>(lista);

			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!!!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	/*
	 * public void prepararExcluir() { fornecedores = itens.getRowData(); }
	 */
	public void excluir() {
		try {
			FornecedoresDao fdao = new FornecedoresDao();
			fdao.excluir(fornecedores);

			/* ArrayList<Fornecedores> lista */ itens = fdao.listar();
			// itens = new ListDataModel<Fornecedores>(lista);

			JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!!!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado!");
			e.printStackTrace();
		}

	}

	/*
	 * public void prepararEditar() { fornecedores = itens.getRowData(); }
	 */
	public void editar() {
		try {
			FornecedoresDao fdao = new FornecedoresDao();
			fdao.editar(fornecedores);

			/* ArrayList<Fornecedores> lista */ itens = fdao.listar();
			// itens = new ListDataModel<Fornecedores>(lista);

			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!!!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage");
			e.printStackTrace();
		}

	}

}
