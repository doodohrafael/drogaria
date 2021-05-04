package teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dao.FabricanteDao;
import model.Fabricante;

public class FabricanteJunitTeste {

	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		FabricanteDao dao = new FabricanteDao();
		
		fabricante.setDescricao("");
		dao.incluir(fabricante);
		
		//sysout
		System.out.println(fabricante.getDescricao());
	}
	
	@Test
	@Ignore
	public void listar() {
		FabricanteDao dao = new FabricanteDao();
		List<Fabricante> fabricantes = dao.listarFabricantes();
		
		for(Fabricante fabricante : fabricantes) {
			System.out.println(fabricante.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void procurarFabricante() {
		FabricanteDao dao = new FabricanteDao();
		boolean teste = dao.procurarFabricante(1L);
		if(teste == true) {
			System.out.println("FABRICANTE NÂO EXISTE!");
		}else {
			System.out.println("Funcionario EXISTE!");
		}
		
	}
	
	
	@Test
	public void excluir() {
		FabricanteDao dao = new FabricanteDao();
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(3L);
	
		dao.excluir(fabricante);
	}
	
	@Test
	@Ignore
	public void editar() {
		FabricanteDao dao = new FabricanteDao();
		Fabricante fabricante = new Fabricante();
		
		fabricante.setCodigo(1L);
		fabricante.setDescricao("FabriCafé");
		
		dao.alterar(fabricante);
		System.out.println("Depois: "+ fabricante.getCodigo()+" "+fabricante.getDescricao());
	}
}
