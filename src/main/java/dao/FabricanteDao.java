
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

public class FabricanteDao {

	public void incluir(Fabricante fabricante) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.persist(fabricante);
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	/*
	 * public List<Fabricante> listarFabricantes() { EntityManager manager = new
	 * ConnectionFactory().getConnection(); List<Fabricante> fabricantes = new
	 * ArrayList<Fabricante>(); try { CriteriaQuery<Fabricante> query =
	 * manager.getCriteriaBuilder().createQuery(Fabricante.class);
	 * query.select(query.from(Fabricante.class));
	 * 
	 * fabricantes = manager.createQuery(query).getResultList();
	 * 
	 * } catch (RuntimeException e) { manager.getTransaction().rollback(); throw e;
	 * } finally { manager.close(); } return fabricantes; }
	 */

	public List<Fabricante> listarFabricantes() {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Fabricante> fabricantes = new ArrayList<Fabricante>();
		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Fabricante> query = criteriaBuilder.createQuery(Fabricante.class);
			Root<Fabricante> root = query.from(Fabricante.class);
			query.select(root);
			// query.select(query.from(Fabricante.class));
			query.orderBy(criteriaBuilder.asc(root.get("descricao")));
			fabricantes = manager.createQuery(query).getResultList();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}
		return fabricantes;
	}

	public boolean procurarFabricante(Long codigo) {
		EntityManager manager = new ConnectionFactory().getConnection();
		boolean existeFabricante;

		try {
			manager.getTransaction().begin();
			Query query = manager.createQuery("select codigo from " + "Fabricante f where f.codigo" + " = :codigo");

			query.setParameter("codigo", codigo);

			existeFabricante = query.getResultList().isEmpty();
			manager.getTransaction().commit();

		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

		return existeFabricante;
	}

	public void excluir(Fabricante fabricante) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(fabricante));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}

	public void alterar(Fabricante fabricante) {
		EntityManager manager = new ConnectionFactory().getConnection();

		try {
			manager.getTransaction().begin();
			manager.merge(manager.merge(fabricante));
			manager.getTransaction().commit();
		} catch (RuntimeException e) {
			manager.getTransaction().rollback();
			throw e;
		} finally {
			manager.close();
		}

	}
	
	public Fabricante fabricantePorCodigo(Long codigo) {
		EntityManager manager = new ConnectionFactory().getConnection();
		List<Fabricante> fabricantes = new ArrayList<Fabricante>();
		Fabricante fabricante = new Fabricante();
		
		try {
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Fabricante> query = criteriaBuilder.createQuery(Fabricante.class);
			Root<Fabricante> root = query.from(Fabricante.class);
			
			query.select(root);
			query.where(criteriaBuilder.equal(root.get("codigo"), codigo));
			
			fabricantes = manager.createQuery(query).getResultList();
			
			for(Fabricante fab : fabricantes) {
				if(fab.getCodigo().equals(codigo)) {
					fabricante.setCodigo(fab.getCodigo());
					return fabricante;
				}
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			manager.close();
		}

		return fabricante;
	}

}
