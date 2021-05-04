package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import connection.ConnectionFactory;
import model.Venda;

public class VendaDao {
	
	public void incluir(Venda venda) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.persist(venda);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}
	
	public List<Venda> listarVendas() {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Venda> vendas = new ArrayList<Venda>();
		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Venda> query = criteriaBuilder.createQuery(Venda.class);
			Root<Venda> root = query.from(Venda.class);
			query.select(root);
			//query.orderBy(criteriaBuilder.asc(root.get("descricao"))); 
			vendas = manager.createQuery(query).getResultList();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		return vendas;
	}
	
	public boolean procurarVenda(Long codigo) {
		EntityManager manager = new ConnectionFactory().getConnection();
		boolean existeVenda;
		
		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("select codigo from "
					+ "Venda v where v.codigo"
					+ " = :codigo");
			
			query.setParameter("codigo", codigo);
			
			
			existeVenda = query.getResultList().isEmpty();
			manager.getTransaction().commit();
			
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		
		return existeVenda;
	}
	
	public void excluir(Venda venda) {
		 EntityManager manager = new ConnectionFactory().getConnection();
		 
		 try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(venda));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		 
	}
	
	public void alterar(Venda venda) {
		 EntityManager manager = new ConnectionFactory().getConnection();
		 
		 try {
			manager.getTransaction().begin();
			manager.merge(manager.merge(venda));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

}
