package model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_produto")
	private Long codigo;

	@Column(name = "nome", length = 255, nullable = false)
	private String nome;

	@Column(name = "preco", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	@Column(name = "descricao", length = 255, nullable = false)
	private String descricao;
	
	//Chave estrangeira é sempre um objeto
	
	//manyToOne = muitos produtos para somente um fabricante
	//fetch EAGER = carrega o obj produto com o obj fabricante junto
	//joincolumn = define a fk. define a coluna estrangeira que liga a tabela produto com a fabricante
	//referencedColumnName = o nome da pk que é fk em produto
	
	@ManyToOne(fetch = FetchType.EAGER, cascade =  CascadeType.REFRESH)
	@JoinColumn(name = "fabricante_codigo_fabricante", referencedColumnName = "codigo_fabricante", nullable = false)
	private Fabricante fabricante;
	
	
	// Getters e setters

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
}
