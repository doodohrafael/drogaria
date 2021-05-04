package teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dao.FuncionarioDao;
import model.Funcionario;

public class FuncionarioJunitTeste {

	@Test
	@Ignore
	public void salvar() {
		Funcionario funcionario = new Funcionario();
		FuncionarioDao dao = new FuncionarioDao();
		
		funcionario.setNome("Rafael Audaz");
		funcionario.setCpf("222.691.484-50");
		funcionario.setSenha("0000");
		funcionario.setFuncao("Visitante");
		dao.incluir(funcionario);
		
	}
	
	@Test
	@Ignore
	public void listar() {
		FuncionarioDao dao = new FuncionarioDao();
		List<Funcionario> funcionarios = dao.listarFuncionarios();
		
		for(Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.getNome() +" | "+funcionario.getCpf());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void procurarFuncionario() {
		FuncionarioDao dao = new FuncionarioDao();
		/*
		 * boolean teste = dao.procurarFuncionario(25L); if(teste == true) {
		 * System.out.println("Funcionario NÂO EXISTE!"); }else {
		 * System.out.println("Funcionario EXISTE!"); }
		 */
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = new Funcionario();
		funcionario.setCodigo(24L);
	
		dao.excluir(funcionario);
	}
	
	@Test
	@Ignore
	public void editar() {
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = new Funcionario();
		
		funcionario.setCodigo(55L);
		funcionario.setCpf("107.691.484-00");
		funcionario.setNome("Douglas");
		funcionario.setSenha("1234");
		funcionario.setFuncao("Influencer");
		
		dao.alterar(funcionario);
		System.out.println("Depois: "+ funcionario.getCpf()+" | "+funcionario.getNome());
	}
	
	@Test
	@Ignore
	public void autenticarTeste() {
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf("107.691.484-50");
		funcionario.setSenha("222");
		
		funcionario = dao.autenticarFuncionario(funcionario.getCpf(),funcionario.getSenha());
		
		if(funcionario.getCpf() != null && funcionario.getSenha() != null) {
			System.out.println("FUNCIONARIO EXISTE!");
		} else {
			System.out.println("FUNCIONARIO NÃO EXISTE!");
		}
		
	}
}
