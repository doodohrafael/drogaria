package controller;

import java.util.List;

import dao.VendaDao;
import model.Venda;

public class VendaController {
	
	VendaDao dao = new VendaDao();

	public void incluir(Venda venda) {
		dao.incluir(venda);
	}
	
	public List<Venda> listarVendas() {
		return dao.listarVendas();
	}
	
	public void excluir(Venda venda) {
		dao.excluir(venda);
	}
	
	public void alterar(Venda venda) {
		dao.alterar(venda);
	}
	
}
