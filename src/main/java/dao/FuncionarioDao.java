package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import connection.ConnectionFactory;
import model.Fabricante;
import model.Funcionario;

public class FuncionarioDao {

	public void incluir(Funcionario funcionario) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.persist(funcionario);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public List<Funcionario> listarFuncionarios() {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Funcionario> query = criteriaBuilder.createQuery(Funcionario.class);
			Root<Funcionario> root = query.from(Funcionario.class);
			query.select(root);
			query.orderBy(criteriaBuilder.asc(root.get("nome")));
			funcionarios = manager.createQuery(query).getResultList();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		return funcionarios;
	}

	public boolean procurarFuncionario(String cpf) {
		EntityManager manager = new ConnectionFactory().getConnection();
		boolean existeFuncionario;

		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("select cpf from " + "Funcionario f where f.cpf" + " = :cpf");

			query.setParameter("cpf", cpf);

			existeFuncionario = !query.getResultList().isEmpty();
			manager.getTransaction().commit();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

		return existeFuncionario;
	}

	public void excluir(Funcionario funcionario) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(funcionario));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

	public void alterar(Funcionario funcionario) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.merge(manager.merge(funcionario));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

	public Funcionario funcionarioPorCodigo(Long codigo) {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario funcionario = new Funcionario();

		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Funcionario> query = criteriaBuilder.createQuery(Funcionario.class);
			Root<Funcionario> root = query.from(Funcionario.class);

			query.select(root);
			query.where(criteriaBuilder.equal(root.get("codigo"), codigo));

			funcionarios = manager.createQuery(query).getResultList();

			for (Funcionario fun : funcionarios) {
				if (fun.getCodigo().equals(codigo)) {
					funcionario = fun;
					return funcionario;
				}
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			manager.close();
		}

		return funcionario;
	}


	public Funcionario autenticarFuncionario(String cpf, String senha) {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Funcionario funcionario = new Funcionario();

		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Funcionario> query = criteriaBuilder.createQuery(Funcionario.class);
			Root<Funcionario> root = query.from(Funcionario.class);
			query.select(root);
			query.where(criteriaBuilder.equal(root.get("cpf"), cpf));
			

			funcionarios = manager.createQuery(query).getResultList();

			for (Funcionario fun : funcionarios) {
				if (fun.getCpf().equals(cpf) && fun.getSenha().equals(senha)) {
					funcionario = fun;
					return funcionario;
				}
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			manager.close();
		}

		return funcionario;
	}

}
