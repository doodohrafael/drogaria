package teste;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dao.VendaDao;
import model.Venda;
import model.Funcionario;

public class VendaJunitTeste {

	@Test
	@Ignore
	public void salvar() {
		Venda venda = new Venda();
		VendaDao dao = new VendaDao();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCodigo(23L);
		
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(26.45D));
		venda.setFuncionario(funcionario);
		
		dao.incluir(venda);
		System.out.println("Inserido");
		
	}
	
	@Test
	@Ignore
	public void listar() {
		VendaDao dao = new VendaDao();
		List<Venda> vendas = dao.listarVendas();
		
		for(Venda venda : vendas) {
			System.out.println(venda.getValorTotal());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void procurarVenda() {
		VendaDao dao = new VendaDao();
		boolean teste = dao.procurarVenda(53L);
		if(teste == true) {
			System.out.println("Venda NÃO EXISTE!");
		}else {
			System.out.println("Venda EXISTE!");
		}
		
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		VendaDao dao = new VendaDao();
		Venda venda = new Venda();
		venda.setCodigo(41L);
	
		dao.excluir(venda);
	}
	
	@Test
	@Ignore
	public void editar() {
		VendaDao dao = new VendaDao();
		Venda venda = new Venda();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCodigo(46L);
		
		venda.setCodigo(53L);
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(50D));
		venda.setFuncionario(funcionario);
		
		dao.alterar(venda);
		System.out.println("Depois: "+venda.getValorTotal());
	}
}
