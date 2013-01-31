package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.notasfiscais.modelo.QuantidadePorProduto;

@SuppressWarnings("serial")
public class ProdutoDAO implements Serializable {

	@Inject
	EntityManager em;	
	
	public List<QuantidadePorProduto> relatorioQuantidadePorProduto() {
		return em.createQuery("select new br.com.caelum.notasfiscais.modelo.QuantidadePorProduto(sum(i.quantidade), i.produto) from Item i group by i.produto").getResultList();
	}
	
	/*
	public List<Produto> pesquisa(int inicio, int quantidade, String nome,
			String descricao) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Produto> criteria = builder
				.createQuery(Produto.class);

		Root<Produto> root = criteria.from(Produto.class);

		Predicate conjunction = builder.conjunction();

		if (nome != null) {

			conjunction = builder.and(conjunction, builder.equal(
					root.<TipoMovimentacao> get("tipoMovimentacao"),
					tipoMovimentacao));

		}

		criteria.where(conjunction);

		return em.createQuery(criteria).setFirstResult(inicio).setMaxResults(quantidade).getResultList();
	}
	*/
	
}