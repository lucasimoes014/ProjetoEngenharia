package br.com.engenharia.dao;

import br.com.engenharia.dao.hibernate.AlunoHibernateDAO;
import br.com.engenharia.dao.jdbc.ProfessorDAO;
import br.com.engenharia.dao.jdbc.ProfessorJDBCDAO;
import br.com.engenharia.dao.jdbc.TurmaDAO;
import br.com.engenharia.dao.jdbc.TurmaJDBCDAO;
import br.com.engenharia.dao.jdbc.UsuarioJDCBDAO;

public class DAOFactory {

	public static AlunoDAO getAlunoDAO() {
		return new AlunoHibernateDAO();
	}

	public static ProfessorDAO getProfessorDAO() {
		return new ProfessorJDBCDAO();
	}

	public static TurmaDAO getTurmaDAO() {
		return new TurmaJDBCDAO();
	}

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioJDCBDAO();
	}
}