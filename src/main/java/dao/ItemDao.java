package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import connection.ConnectionFactory;
import model.Item;

public class ItemDao {
	
	public void incluir(Item item) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.persist(item);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}
	
	public List<Item> listarItems() {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Item> items = new ArrayList<Item>();
		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
			Root<Item> root = query.from(Item.class);
			query.select(root);
			//query.orderBy(criteriaBuilder.asc(root.get("descricao"))); 
			items = manager.createQuery(query).getResultList();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		return items;
	}
	
	public boolean procurarItem(Long codigo) {
		EntityManager manager = new ConnectionFactory().getConnection();
		boolean existeItem;
		
		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("select codigo from "
					+ "Item i where i.codigo"
					+ " = :codigo");
			
			query.setParameter("codigo", codigo);
			
			
			existeItem = query.getResultList().isEmpty();
			manager.getTransaction().commit();
			
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		
		return existeItem;
	}
	
	public void excluir(Item item) {
		 EntityManager manager = new ConnectionFactory().getConnection();
		 
		 try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(item));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		 
	}
	
	public void alterar(Item item) {
		 EntityManager manager = new ConnectionFactory().getConnection();
		 
		 try {
			manager.getTransaction().begin();
			manager.merge(manager.merge(item));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

}
