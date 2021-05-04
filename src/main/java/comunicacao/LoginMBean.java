package comunicacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.FuncionarioController;
import model.Funcionario;
import util.Constantes;
import util.Mensagens;
import util.Validacao;

@ManagedBean
@SessionScoped
public class LoginMBean {

	public LoginMBean() {
		if (funcionarioLogado.equals(null)) {
			setFuncionarioLogado(new Funcionario());
		}
	}

	Funcionario funcionarioLogado = new Funcionario();
	FuncionarioController funcionarioController = new FuncionarioController();

	Validacao validar = new Validacao();

	public String entrar() {
		try {
			if (validarCampos()) {
				funcionarioLogado = funcionarioController.autenticarFuncionario(funcionarioLogado.getCpf(),
						funcionarioLogado.getSenha());
				if (funcionarioLogado.getCpf() != null && funcionarioLogado.getSenha() != null) {
					return Constantes.telaPrincipal;
				} else {
					Mensagens.adicionarMensagemErro("Cpf e/ou Senha incorretos.");
					return null;
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(
					"Erro no login do sistema. " + "Entre em contato com o suporte (99) 9 9999-9999");
		}
		return null;
	}

	public boolean validarCampos() {

		boolean cpfOK = validar.notNullnotEmpty(funcionarioLogado.getCpf(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_CPF);

		boolean senhaOK = validar.camposSenhaNotNullnotEmpty(funcionarioLogado.getSenha(),
				Constantes.MSG_CAMPO_OBRIGATORIO, Constantes.LABEL_SENHA);

		return cpfOK && senhaOK;
	}
	
	public String sair() {
		setFuncionarioLogado(new Funcionario());
		return Constantes.telaLogin;
	}

	// Getters and Setters
	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
}
