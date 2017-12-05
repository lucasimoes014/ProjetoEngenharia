package br.com.engenharia.dao.jdbc;

import java.util.List;

import br.com.engenharia.entidade.Professor;

public interface ProfessorDAO {

	public abstract void alterar(Professor professor);

	public abstract Professor consultar(Professor professor);

	public abstract void excluir(Professor professor);

	public abstract boolean existe(Professor professor);

	public abstract void inserir(Professor professor);

	public abstract List<Professor> listar();

}