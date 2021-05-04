package comunicacao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;

import controller.FabricanteController;
import controller.ProdutoController;
import model.Fabricante;
import model.Produto;
import util.Constantes;
import util.Mensagens;
import util.Validacao;

@ManagedBean
@SessionScoped
public class FabricanteMBean {

	public FabricanteMBean() {
		if (fabricante == null) {
			setFabricante(new Fabricante());
		}
		setColecaoFabricantes(fabricanteController.listarFabricantes());
	}

	private Fabricante fabricante;
	private FabricanteController fabricanteController = new FabricanteController();
	private ProdutoController produtoController = new ProdutoController();

	private List<Fabricante> colecaoFabricantes;

	public void salvar() {
		try {
			if (validarCampos()) {
				fabricanteController.incluir(fabricante);
				setFabricante(new Fabricante());
				Mensagens.adicionarMensagemInfo(Constantes.MSG_SALVO_SUCESSO);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			setFabricante(new Fabricante());
			Mensagens.adicionarMensagemInfo(Constantes.MSG_NAO_SALVO_SUCESSO);
		}
	}

	public void consultarFabricantes() {
		try {
			setFabricante(new Fabricante());
			setColecaoFabricantes(fabricanteController.listarFabricantes());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_DADOS_NAO_ENCONTRADOS);
		}
	}

	public void excluir(Fabricante fabricante) {
		Produto produto = new Produto();
		try {

			fabricanteController.excluir(fabricante);
			Mensagens.adicionarMensagemInfo(Constantes.MSG_EXCLUIDO_SUCESSO);
		} catch (RuntimeException e) {
			produto.setFabricante(fabricante);
			if (!produtoController.procurarProduto(produto.getFabricante().getCodigo())) {
				Mensagens.adicionarMensagemErro("Este Fabricante existe na tabela produtos.");
			}
			
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_NAO_EXCLUIDO_SUCESSO);
		}
	}

	public String telaAlterar() {
		return Constantes.telaAlterarFabricante;
	}

	public String alterar() {
		try {
			if (validarCampos()) {
				fabricanteController.alterar(fabricante);
				setFabricante(new Fabricante());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_NAO_ALTERADO_SUCESSO);
		}
		return Constantes.telaConsultarFabricante;
	}

	public boolean validarCampos() {
		Validacao validar = new Validacao();
		boolean descricaoOK = validar.notNullnotEmpty(fabricante.getDescricao(), Constantes.MSG_CAMPO_OBRIGATORIO,
				Constantes.LABEL_DESCRICAO);
		return descricaoOK;
	}

	public void atualizarSessao() {
		setFabricante(new Fabricante());
	}

	// getters and setters
	public FabricanteController getFabricanteController() {
		return fabricanteController;
	}

	public void setFabricanteController(FabricanteController fabricanteController) {
		this.fabricanteController = fabricanteController;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getColecaoFabricantes() {
		return colecaoFabricantes;
	}

	public void setColecaoFabricantes(List<Fabricante> colecaoFabricantes) {
		this.colecaoFabricantes = colecaoFabricantes;
	}

	public ProdutoController getProdutoController() {
		return produtoController;
	}

	public void setProdutoController(ProdutoController produtoController) {
		this.produtoController = produtoController;
	}
}
