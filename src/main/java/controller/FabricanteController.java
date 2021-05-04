package controller;

import java.util.List;

import dao.FabricanteDao;
import model.Fabricante;

public class FabricanteController {
	
	FabricanteDao dao = new FabricanteDao();

	public void incluir(Fabricante fabricante) {
		dao.incluir(fabricante);
	}
	
	public List<Fabricante> listarFabricantes() {
		return dao.listarFabricantes();
	}
	
	public void excluir(Fabricante fabricante) {
		dao.excluir(fabricante);
	}
	
	public void alterar(Fabricante fabricante) {
		dao.alterar(fabricante);
	}
	
	public Fabricante fabricantePorCodigo(Long codigo) {
		return dao.fabricantePorCodigo(codigo);
	}
	
	public boolean procurarFabricante(Long codigo) {
		return dao.procurarFabricante(codigo);
	}
}
