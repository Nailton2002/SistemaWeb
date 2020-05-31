package br.com.web.test;

import br.com.web.domain.Fornecedores;
import br.com.web.domain.Produtos;
import br.com.web.dao.ProdutoDao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

public class ProdutoDaoTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produtos p1 = new Produtos();
		p1.setDescricao("ESSENCIAL");
		p1.setPreco(2.99);
		p1.setQuantidade(2);

		Fornecedores f = new Fornecedores();
		f.setCodigo(13);
		p1.setFornecedores(f);

		ProdutoDao fdao = new ProdutoDao();
		fdao.salvar(p1);

	}

	@Test
	@Ignore
	public void listar() throws SQLException {

		ProdutoDao fdao = new ProdutoDao();
		ArrayList<Produtos> lista = fdao.listar();

		for (Produtos p : lista) {
			System.out.println("Código do Produto: " + p.getCodigo());
			System.out.println("Descrição do Produto: " + p.getDescricao());
			System.out.println("Valor do Produto: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Código do Fornecedor: " + p.getFornecedores().getCodigo());
			System.out.println("Descrição do Fornecedor: " + p.getFornecedores().getDescricao());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void excluir() throws SQLException {

		Produtos p = new Produtos();
		p.setCodigo(3);

		ProdutoDao dao = new ProdutoDao();
		dao.excluir(p);
	}

	@Test
	public void editar() throws SQLException {

		Produtos p = new Produtos();
		p.setCodigo(2);
		p.setDescricao("perfumeTeste");
		p.setPreco(15.75);
		p.setQuantidade(3);

		Fornecedores f = new Fornecedores();
		f.setCodigo(10);
		p.setFornecedores(f);
		
		ProdutoDao dao = new ProdutoDao();
		dao.editar(p);

		
	}
}
