package br.com.web.dao;

import br.com.web.domain.Fornecedores;
import br.com.web.factory.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedoresDao {
	// CRIANDO METODO DE SALVAR
	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores");
		sql.append("(descricao) ");
		sql.append("VALUE (?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	// CRIANDO METODO DE EXCLUIR
	public void excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, f.getCodigo());
		comando.executeUpdate();

	}

	// CRIANDO METODO DE EDITAR
	public void editar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.setInt(2, f.getCodigo());
		comando.executeUpdate();

	}

	// CRIANDO METODO DE BUSCAPORCODIGO
	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setInt(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}

	// METOD DE LISTAGEM
	public ArrayList<Fornecedores> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while (resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}
		return lista;
	}

	// METODO DE BUSCAR POR DESCRICAO
	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while (resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getInt("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}
		return lista;
	}

	// TESTANDO OS METODOS
	public static void main(String[] args) {

		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("Mendes de Paula");

		Fornecedores f2 = new Fornecedores();
		f2.setDescricao("DESCRICAO 4");

		FornecedoresDao fdao = new FornecedoresDao();
		try {
			fdao.salvar(f1);
			fdao.salvar(f2);
			System.out.println("Salvo com sucesso!!!");

		} catch (SQLException e) {
			System.out.println("Erro ao salvar!");
			e.printStackTrace();
		}

		/*
		 * Fornecedores f1 = new Fornecedores(); f1.setCodigo(3);
		 * 
		 * FornecedoresDao fdao = new FornecedoresDao(); try { fdao.excluir(f1);
		 * System.out.println("Deletado com sucesso!!!");
		 * 
		 * } catch (SQLException e) { System.out.println("Erro ao Deletar!");
		 * e.printStackTrace(); }
		 */

		/*
		 * Fornecedores f1 = new Fornecedores(); f1.setCodigo(1);
		 * 
		 * Fornecedores f2 = new Fornecedores(); f2.setCodigo(5);
		 * 
		 * FornecedoresDao fdao = new FornecedoresDao(); try { Fornecedores f3 =
		 * fdao.buscaPorCodigo(f1); Fornecedores f4 = fdao.buscaPorCodigo(f2);
		 * System.out.println("Busca feita com sucesso!!!");
		 * System.out.println("Resultado 1: " + f3); System.out.println("Resultado 2: "
		 * + f4);
		 * 
		 * } catch (SQLException e) { System.out.println("Erro na busca ao Editar!");
		 * e.printStackTrace(); }
		 */
		/*
		 * FornecedoresDao fdao = new FornecedoresDao(); try { ArrayList<Fornecedores>
		 * lista = fdao.listar(); for (Fornecedores f : lista) {
		 * System.out.println("Resultados " + f); } } catch (SQLException e) {
		 * System.out.println("Erro ao buscar"); e.printStackTrace(); }
		 */
		/*
		 * Fornecedores f1 = new Fornecedores(); f1.setDescricao("Nai"); FornecedoresDao
		 * fdao = new FornecedoresDao(); try { ArrayList<Fornecedores> lista =
		 * fdao.buscarPorDescricao(f1); for (Fornecedores f : lista) {
		 * System.out.println("Resultados " + f); } } catch (SQLException e) {
		 * System.out.println("Erro ao buscar"); e.printStackTrace(); }
		 */

	}

}
