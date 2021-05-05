package comunicacao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import controller.FuncionarioController;
import model.Funcionario;
import util.Constantes;
import util.Mensagens;
import util.Validacao;

@ManagedBean
@SessionScoped
public class FuncionarioMBean {

	public FuncionarioMBean() {
		if (funcionario == null) {
			setFuncionario(new Funcionario());
		}
		setColecaoFuncionarios(funcionarioController.listarFuncionarios());
	}

	private Funcionario funcionario;
	private FuncionarioController funcionarioController = new FuncionarioController();

	private List<Funcionario> colecaoFuncionarios;

	private String confirmarSenha;
	private Boolean cpfAlterar;

	public void salvar() {
		try {
			cpfAlterar = false;
			if (validarCampos()) {
				//Cripitografar senha
				funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
				funcionarioController.incluir(funcionario);
				setFuncionario(new Funcionario());
				Mensagens.adicionarMensagemInfo(Constantes.MSG_SALVO_SUCESSO);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			setFuncionario(new Funcionario());
			Mensagens.adicionarMensagemInfo(Constantes.MSG_NAO_SALVO_SUCESSO);
		}
	}

	public void consultarFuncionarios() {
		try {
			setFuncionario(new Funcionario());
			setColecaoFuncionarios(funcionarioController.listarFuncionarios());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_DADOS_NAO_ENCONTRADOS);
		}
	}

	public void excluir(Funcionario funcionario) {
		try {
			if(!LoginMBean.funcionarioLogado.equals(funcionario)) {
				funcionarioController.excluir(funcionario);
				Mensagens.adicionarMensagemInfo(Constantes.MSG_EXCLUIDO_SUCESSO);
			} else {
				Mensagens.adicionarMensagemAlerta(Constantes.MSG_USUARIO_LOGADO);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_NAO_EXCLUIDO_SUCESSO);
		}
	}

	public String telaAlterar() {
		return Constantes.telaAlterarFuncionario;
	}

	public String alterar() {
		try {
			cpfAlterar = true;
			if (validarCampos()) {
				funcionario.setSenha(DigestUtils.md5Hex(funcionario.getSenha()));
				funcionarioController.alterar(funcionario);
				setFuncionario(new Funcionario());
				return Constantes.telaConsultarFuncionario;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_NAO_ALTERADO_SUCESSO);
		}
		return null;
	}

	public Boolean validarCampos() {
		Validacao validar = new Validacao();

		boolean cpfDuplicado = true;
		
		boolean nomeOK = validar.notNullnotEmpty(funcionario.getNome(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_NOME);
		
		boolean cpfOK = validar.notNullnotEmpty(funcionario.getCpf(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_CPF);

		if (!cpfAlterar) {
			cpfDuplicado = funcionarioController.procurarFuncionario(funcionario.getCpf());
			if (cpfDuplicado) {
				Mensagens.adicionarMensagemAlerta(Constantes.MSG_CAMPO_CPF_DUPLICADO);
				cpfDuplicado = false;
			} else {
				cpfDuplicado = true;
			}
		}

		boolean senhaOK = validar.camposSenhaNotNullnotEmpty(funcionario.getSenha(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_SENHA);

		boolean confirmarSenhaOK = validar.camposSenhaNotNullnotEmpty(confirmarSenha, Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_CONFIRMAR_SENHA);

		boolean senhasIguaisOK = validar.camposSenhaIguais(funcionario.getSenha(), confirmarSenha);

		boolean funcaoOK = validar.notNullnotEmpty(funcionario.getFuncao(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_FUNCAO);

		return nomeOK && cpfOK && cpfDuplicado && senhaOK && confirmarSenhaOK && senhasIguaisOK && funcaoOK;
	}
	
	public void atualizarSessao() {
		setFuncionario(new Funcionario());
	}

	// getters and setters
	public FuncionarioController getFuncionarioController() {
		return funcionarioController;
	}

	public void setFuncionarioController(FuncionarioController funcionarioController) {
		this.funcionarioController = funcionarioController;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getColecaoFuncionarios() {
		return colecaoFuncionarios;
	}

	public void setColecaoFuncionarios(List<Funcionario> colecaoFuncionarios) {
		this.colecaoFuncionarios = colecaoFuncionarios;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
