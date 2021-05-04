package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class TesteJunitFactory {

	@Test
	public void gerar() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("drogaria");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trs = manager.getTransaction();
	}
	
	
}
