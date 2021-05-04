package controller;

import java.util.List;

import dao.ItemDao;
import model.Item;

public class ItemController {
	
	ItemDao dao = new ItemDao();

	public void incluir(Item item) {
		dao.incluir(item);
	}
	
	public List<Item> listarItems() {
		return dao.listarItems();
	}
	
	public void excluir(Item item) {
		dao.excluir(item);
	}
	
	public void alterar(Item item) {
		dao.alterar(item);
	}
	
}
