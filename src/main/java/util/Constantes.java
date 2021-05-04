package util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "constantesMBean")
@ApplicationScoped
public class Constantes {

	//Páginas
	public static final String telaAlterarFabricante = "/paginas/alterarFabricante.xhtml?faces-redirect=true";
	public static final String telaConsultarFabricante = "/paginas/consultarFabricante.xhtml?faces-redirect=true";
	
	public static final String telaAlterarFuncionario = "/paginas/alterarFuncionario.xhtml?faces-redirect=true";
	public static final String telaConsultarFuncionario = "/paginas/consultarFuncionario.xhtml?faces-redirect=true";
	
	public static final String telaAlterarProduto = "/paginas/alterarProduto.xhtml?faces-redirect=true";
	public static final String telaConsultarProduto = "/paginas/consultarProduto.xhtml?faces-redirect=true";
	
	public static final String telaLogin = "/paginas/login.xhtml?faces-redirect=true";
	public static final String telaPrincipal = "/paginas/paginaPrincipal.xhtml?faces-redirect=true";
	
	
	// Colunas
	public static final String LABEL_CODIGO = "Código";
	public static final String LABEL_DESCRICAO = "Descrição";
	public static final String LABEL_NOME = "Nome";
	public static final String LABEL_FUNCAO = "Função";
	public static final String LABEL_CPF = "Cpf";
	public static final String LABEL_SENHA = "Senha";
	public static final String LABEL_CONFIRMAR_SENHA = "Confirmar senha";
	public static final String LABEL_OPERADORES = "Operadores";
	
	public static final String LABEL_FABRICANTES = "Fabricantes";
	public static final String LABEL_FABRICANTE = "Fabricante";
	public static final String LABEL_PRECO = "Preço";
	public static final String LABEL_QUANTIDADE = "Quantidade";
	public static final String LABEL_PRODUTO = "Produto";
	public static final String LABEL_VALOR_PARCIAL = "Valor Parcial";
	

	// Botões
	public static final String BOTAO_PESQUISAR = "Pesquisar";
	public static final String BOTAO_ADICIONAR = "Adicionar";
	public static final String BOTAO_SALVAR = "Salvar";
	public static final String BOTAO_VOLTAR = "Voltar";
	public static final String BOTAO_EXCLUIR = "Excluir";
	public static final String BOTAO_ALTERAR = "Alterar";
	public static final String BOTAO_ENTRAR = "Entrar";

	// Mensagens
	public static final String MSG_SALVO_SUCESSO = "Salvo com sucesso.";
	public static final String MSG_NAO_SALVO_SUCESSO = "Erro ao tentar salvar.";
	public static final String MSG_REGISTRO_NAO_ENCONTRADO = "Nenhum registro encontrado.";
	public static final String MSG_DADOS_NAO_ENCONTRADOS = "Dados não encontrados.";
	public static final String MSG_EXCLUIDO_SUCESSO = "Excluído com sucesso.";
	public static final String MSG_NAO_EXCLUIDO_SUCESSO = "Erro ao tentar excluir.";
	public static final String MSG_ALTERADO_SUCESSO = "Alterado com sucesso.";
	public static final String MSG_NAO_ALTERADO_SUCESSO = "Erro ao tentar alterar.";
	
	// Mensagens Campos Obrigatórios
	public static final String MSG_CAMPO_DESCRICAO_OBG = "O campo descrição é obrigatório.";
	public static final String MSG_CAMPO_DESCRICO_MIN = "O campo descrição precide de no mínimo 3 caracteres.";
	public static final String MSG_CAMPO_NOME_OBG = "O campo nome é obrigatório.";
	public static final String MSG_CAMPO_CPF_OBG = "O campo cpf é obrigatório.";
	public static final String MSG_CAMPO_CPF_DUPLICADO = "O cpf informado já é cadastrado.";
	
	public static final String MSG_CAMPO_OBRIGATORIO = "O campo % é obrigatório.";
	
}
