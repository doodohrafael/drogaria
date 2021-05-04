package teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dao.ItemDao;
import model.Item;
import model.Produto;
import model.Venda;
import model.Funcionario;

public class ItemJunitTeste {

	@Test
	@Ignore
	public void salvar() {
		Item item = new Item();
		ItemDao dao = new ItemDao();
		
		Venda venda = new Venda();
		Produto produto = new Produto();
		
		venda.setCodigo(53L);
		produto.setCodigo(36L);
		
		item.setQuantidade(5);
		item.setValorParcial(new BigDecimal(105.66D));
		item.setVenda(venda);
		item.setProduto(produto);
		
		dao.incluir(item);
		System.out.println("Inserido");
		
	}
	
	@Test
	@Ignore
	public void listar() {
		ItemDao dao = new ItemDao();
		List<Item> items = dao.listarItems();
		
		for(Item item : items) {
			System.out.println(item.getValorParcial());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void procurarItem() {
		ItemDao dao = new ItemDao();
		boolean teste = dao.procurarItem(55L);
		if(teste == true) {
			System.out.println("Item NÃO EXISTE!");
		}else {
			System.out.println("Item EXISTE!");
		}
		
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		ItemDao dao = new ItemDao();
		Item item = new Item();
		item.setCodigo(41L);
	
		dao.excluir(item);
	}
	
	@Test
	@Ignore
	public void editar() {
		ItemDao dao = new ItemDao();
		Item item = new Item();
		
		Venda venda = new Venda();
		Produto produto = new Produto();
		
		venda.setCodigo(53L);
		produto.setCodigo(36L);
		
		item.setQuantidade(1);
		item.setValorParcial(new BigDecimal(5.66D));
		item.setVenda(venda);
		item.setProduto(produto);
		
		item.setCodigo(56L);
		
		
		dao.alterar(item);
		System.out.println("Depois: "+item.getValorParcial());
	}
}
