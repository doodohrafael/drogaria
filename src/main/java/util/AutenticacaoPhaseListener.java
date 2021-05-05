package util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import comunicacao.LoginMBean;
import model.Funcionario;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener{

	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		String paginaAtual = uiViewRoot.getViewId();
		System.out.println("PAGINA ATUAL: " + paginaAtual);
		
		//paginas publicas
		boolean paginaAutenticacao = paginaAtual.contains("login.xhtml");
		System.out.println("Pagina Autenticação: " + paginaAutenticacao);
		
		//paginas privadas
		if(!paginaAutenticacao) {
			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap();
			LoginMBean loginMBean = (LoginMBean) mapa.get("loginMBean");
			
			//antes de clicar nos botoes de acesso a paginas, ele estara null
			System.out.println("LoginMBean: " + loginMBean);
			
			Funcionario funcionario = loginMBean.getFuncionarioLogado();
			System.out.println("Funcionário: " + funcionario.toString());
			
			if(funcionario.getFuncao() == null) {
				Application application = facesContext.getApplication();
				NavigationHandler navigationHandler = application.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, Constantes.telaLogin);
				Mensagens.adicionarMensagemErro("Funcionário não autenticado.");
			} 
		}
	}

	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}


