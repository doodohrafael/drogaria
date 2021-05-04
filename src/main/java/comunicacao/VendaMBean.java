package comunicacao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.FuncionarioController;
import controller.ItemController;
import controller.ProdutoController;
import controller.VendaController;
import model.Item;
import model.Produto;
import model.Venda;
import util.Constantes;
import util.Mensagens;
import util.Validacao;

@ManagedBean
@SessionScoped
public class VendaMBean {

	public VendaMBean() {
		if (produto == null) {
			setProduto(new Produto());
		}
		setColecaoProdutos(produtoController.listarProdutos());
		setColecaoItens(new ArrayList<Item>());
		setVenda(new Venda());
		venda.setValorTotal(new BigDecimal("0.00"));
	}

	private Produto produto;
	private ProdutoController produtoController = new ProdutoController();

	private ItemController itemController = new ItemController();

	private List<Produto> colecaoProdutos;
	private List<Produto> colecaoProdutosItens;

	private Venda venda;
	private VendaController vendaController = new VendaController();

	public List<Produto> getColecaoProdutosItens() {
		return colecaoProdutosItens;
	}

	public void setColecaoProdutosItens(List<Produto> colecaoProdutosItens) {
		this.colecaoProdutosItens = colecaoProdutosItens;
	}

	private List<Item> colecaoItens;

	public void adicionarItens(Produto produto) {

		int posicaoEncontrada = -1;
		for (int p = 0; p < colecaoItens.size() && posicaoEncontrada < 0; p++) {
			Item itemTemporario = colecaoItens.get(p);
			if (itemTemporario.getProduto().equals(produto)) {
				posicaoEncontrada = p;
			}
		}

		Item item = new Item();
		item.setProduto(produto);
		if (posicaoEncontrada < 0 && produto.getQuantidade() > 0) {
			item.setQuantidade(1);
			item.setValorParcial(produto.getPreco());
			colecaoItens.add(item);
		} else if (produto.getQuantidade() > 0) {
			Item itemTemporario = colecaoItens.get(posicaoEncontrada);
			item.setQuantidade(itemTemporario.getQuantidade() + 1);
			item.setValorParcial(itemTemporario.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())));
			colecaoItens.set(posicaoEncontrada, item);

		}

		if (produto.getQuantidade() > 0) {
			produto.setQuantidade(produto.getQuantidade() - 1);
			produtoController.alterar(produto);

			venda.setValorTotal(venda.getValorTotal().add(produto.getPreco()));

		} else if (produto.getQuantidade() == 0) {
			Mensagens.adicionarMensagemAlerta("Produto deste tipo sem estoque.");
		}

		System.out.println("VALOR TOTAL: " + venda.getValorTotal());

	}

	public void removerItem(Item item) {
		int posicaoEncontrada = -1;
		for (int p = 0; p < colecaoItens.size() && posicaoEncontrada < 0; p++) {
			Item itemDaLista = colecaoItens.get(p);
			if (itemDaLista.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = p;
			}
		}
		colecaoItens.remove(posicaoEncontrada);

		// if (item.getProduto().getQuantidade() >= 0) {//
		item.getProduto().setQuantidade(item.getProduto().getQuantidade() + item.getQuantidade());
		produtoController.alterar(item.getProduto());

		venda.setValorTotal((venda.getValorTotal().subtract(item.getValorParcial())));
		// }
	}

	public void listarProdutos() {
		try {
			// setProduto(new Produto());
			setColecaoProdutos(produtoController.listarProdutos());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_DADOS_NAO_ENCONTRADOS);
		}
	}

	public void finalizarVenda() {
		venda.setHorario(new Date());

		FuncionarioController funcionarioController = new FuncionarioController();
		venda.setFuncionario(funcionarioController.funcionarioPorCodigo(20L));
	}

	public void salvar() {
		try {
			vendaController.incluir(venda);
			if(venda.getCodigo() != null) {
				for(Item item: colecaoItens) {
					item.setVenda(venda);
					itemController.incluir(item);
				}
			}
			
			setColecaoItens(new ArrayList<Item>());
			setVenda(new Venda());
			venda.setValorTotal(new BigDecimal("0.00"));
			Mensagens.adicionarMensagemInfo(Constantes.MSG_SALVO_SUCESSO);
		} catch (RuntimeException e) {
			e.printStackTrace();
			Mensagens.adicionarMensagemErro(Constantes.MSG_NAO_SALVO_SUCESSO);
		}
	}

	// getters and setters
	public ProdutoController getProdutoController() {
		return produtoController;
	}

	public void setProdutoController(ProdutoController produtoController) {
		this.produtoController = produtoController;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getColecaoProdutos() {
		return colecaoProdutos;
	}

	public void setColecaoProdutos(List<Produto> colecaoProdutos) {
		this.colecaoProdutos = colecaoProdutos;
	}

	public List<Item> getColecaoItens() {
		return colecaoItens;
	}

	public void setColecaoItens(List<Item> colecaoItens) {
		this.colecaoItens = colecaoItens;
	}

	public ItemController getItemController() {
		return itemController;
	}

	public void setItemController(ItemController itemController) {
		this.itemController = itemController;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public VendaController getVendaController() {
		return vendaController;
	}

	public void setVendaController(VendaController vendaController) {
		this.vendaController = vendaController;
	}

}
