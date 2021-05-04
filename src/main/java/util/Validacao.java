package util;

import java.math.BigDecimal;

public class Validacao {
	Mensagens mensagem = new Mensagens();
	
	public boolean notNullnotEmpty(String valor, String msg, String campo) {

		if (valor == null || valor.trim().equals("")) {
			msg = msg.replaceAll("%", " " + campo + " ");
			Mensagens.adicionarMensagemAlerta(msg);
			return false;
		} 

		return true;
	}
	
	public boolean notNullnotEmpty(BigDecimal valor, String msg, String campo) {

		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			msg = msg.replaceAll("%", " " + campo + " ");
			Mensagens.adicionarMensagemAlerta(msg);
			return false;
		}

		return true;
	}
	
	public boolean notNullnotEmpty(Integer valor, String msg, String campo) {

		if (valor == null || valor <= 0) {
			msg = msg.replaceAll("%", " " + campo + " ");
			Mensagens.adicionarMensagemAlerta(msg);
			return false;
		} 

		return true;
	}
	
	public boolean notNullnotEmpty(Object valor, String msg, String campo) {

		if (valor == null) {
			msg = msg.replaceAll("%", " " + campo + " ");
			Mensagens.adicionarMensagemAlerta(msg);
			return false;
		} 

		return true;
	}
	
	public boolean camposSenhaNotNullnotEmpty(String valor, String msg, String campo) {

		if (valor == null || valor.trim().equals("")) {
			msg = msg.replaceAll("%", " " + campo + " ");
			Mensagens.adicionarMensagemAlerta(msg);
			return false;
		} 

		if(valor.length() < 6) {
			Mensagens.adicionarMensagemAlerta("A senha precisa de 6 dígitos.");
			return false;
		}
		
		return true;
	}
	
	public boolean camposSenhaIguais(String senha, String confirmaSenha) {

		if (!senha.equals(confirmaSenha)) {
			Mensagens.adicionarMensagemAlerta("Os campos Senha e Confirmar Senha precisam ser iguais.");
			return false;
		} 

		return true;
	}
	
}
