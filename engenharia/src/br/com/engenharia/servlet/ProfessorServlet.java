package br.com.engenharia.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.engenharia.dao.jdbc.ProfessorDAO;
import br.com.engenharia.dao.jdbc.ProfessorJDBCDAO;
import br.com.engenharia.entidade.Professor;

@WebServlet("/ProfessorServlet")
public class ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String destino = "sucessoProfessor.jsp";
		String mensagem = "";
		List<Professor> lista = new ArrayList<>();

		
		Professor professor = new Professor();
		ProfessorDAO dao = new ProfessorJDBCDAO();
		
		try {

			//Se a ação for DIFERENTE de Listar são lidos os dados da tela
			if (!acao.equalsIgnoreCase("Listar")) {
				professor.setMatricula(Long.parseLong(request.getParameter("matricula")));
				professor.setNome(request.getParameter("nome"));
				professor.setTelefone(request.getParameter("telefone"));
				professor.setEmail(request.getParameter("email"));
				
				//Faz a leitura da data de cadastro. Caso ocorra um erro de formatação
				// o sistema utilizará a data atual
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");					
					professor.setDataCadastro(df.parse(request.getParameter("dataCadastro")));
				} catch (Exception e) {
					professor.setDataCadastro(new Date());	
				}
				
			}

			if (acao.equalsIgnoreCase("Incluir")) {
				// Verifica se a matrícula informada já existe no Banco de Dados
				// Se existir enviar uma mensagem senão faz a inclusão
				if (dao.existe(professor)) {
					mensagem = "Matricula informada j� existe!";
				} else {
					dao.inserir(professor);
				}
			} else if (acao.equalsIgnoreCase("Alterar")) {
				dao.alterar(professor);
			} else if (acao.equalsIgnoreCase("Excluir")) {
				dao.excluir(professor);
			} else if (acao.equalsIgnoreCase("Consultar")) {
				request.setAttribute("professor", professor);
				professor = dao.consultar(professor);
				destino = "professor.jsp";
			}
		} catch (Exception e) {
			mensagem += e.getMessage();
			destino = "erro.jsp";
			e.printStackTrace();
		}
		
		// Se a mensagem estiver vazia significa que houve sucesso!
		// Senão será exibida a tela de erro do sistema.
		if (mensagem.length() == 0) {
			mensagem = "Professor Cadastrado com sucesso!";
		} else {
			destino = "erro.jsp";
		}

		// Lista todos os registros existente no Banco de Dados
		lista = dao.listar();
		request.setAttribute("listaProfessor", lista);
		request.setAttribute("mensagem", mensagem);
		

		//O sistema é direcionado para a página 
		//sucesso.jsp Se tudo ocorreu bem
		//erro.jsp se houver algum problema.
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}
}