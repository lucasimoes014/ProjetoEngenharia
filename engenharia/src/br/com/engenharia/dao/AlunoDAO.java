package br.com.engenharia.dao;

import java.util.List;

import br.com.engenharia.entidade.Aluno;

public interface AlunoDAO {

	public void alterar(Aluno aluno);

	public Aluno consultar(Aluno aluno);

	public void excluir(Aluno aluno);

	public boolean existe(Aluno aluno);

	public void inserir(Aluno aluno);

	public List<Aluno> listar();

}