package br.com.engenharia.dao.jdbc;

import java.util.List;

import br.com.engenharia.entidade.Turma;

public interface TurmaDAO {

	public abstract void alterar(Turma turma);

	public abstract void excluir(Turma turma);

	public abstract boolean existe(Turma turma);

	public abstract void inserir(Turma turma);

	public abstract List<Turma> listar();

	public abstract Turma consultar(Turma turma);

}