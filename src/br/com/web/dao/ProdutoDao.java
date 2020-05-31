package br.com.web.dao;

import br.com.web.domain.Fornecedores;
import br.com.web.domain.Produtos;
import br.com.web.factory.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDao {
	// CRIANDO METODO DE SALVAR
	public void salvar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo) ");
		sql.append("VALUE (?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setInt(4, p.getFornecedores().getCodigo());
		comando.executeUpdate();
	}

	// METOD DE LISTAGEM
	public ArrayList<Produtos> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM Produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produtos> lista = new ArrayList<Produtos>();

		while (resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));

			Produtos p = new Produtos();
			p.setCodigo(resultado.getInt("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setFornecedores(f);

			lista.add(p);
		}
		return lista;
	}

	// CRIANDO METODO DE EXCLUIR
	public void excluir(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Produtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, p.getCodigo());
		comando.executeUpdate();

	}

	// CRIANDO METODO DE EDITAR
	public void editar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo= ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setInt(3, p.getQuantidade());
		comando.setInt(4, p.getFornecedores().getCodigo());
		comando.setInt(5, p.getCodigo());
		comando.executeUpdate();

	}

}
