package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.annotation.Transactional;
import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.datamodel.NotaFiscalDataModel;
import br.com.caelum.notasfiscais.modelo.EntityInterface;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class NotaFiscalBean implements Serializable {

	@Inject
	protected NotaFiscal entity;
	
	protected List<NotaFiscal> list;
	protected LazyDataModel<NotaFiscal> lazyList;
	
	private Item item = new Item();
	private Long idProduto;
	
	@Inject
	private DAO<NotaFiscal> dao;

	@Inject
	private DAO<Produto> daoProduto;

	
	@PostConstruct
	public void init() {
		lazyList = new NotaFiscalDataModel(dao);
	}

	public Item getItem() {
		return item;
	}
	
	public void setIdProduto(Long id) {
		idProduto = id;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public void guardaItem() {
		
		Produto produto = daoProduto.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
	
		entity.getItens().add(item);
		item.setNotaFiscal(entity);
		
		item = new Item();
		
	}
	
	public double getTotal() {

		double total = 0.0;

		for (Item i : entity.getItens()) {
			total += i.getQuantidade() * i.getValorUnitario();
		}
		
		return total;
	}
	
	public String cancela() {
		return "notafiscal";
	}
	
	public NotaFiscal getEntity() {
		return entity;
	}

	public void setEntity(NotaFiscal entity) {
		this.entity = entity;
	}

	public List<NotaFiscal> getList() {
		if (list == null) {
			list = dao.listaTodos();
		}
		return list; 
	}
	
	public LazyDataModel<NotaFiscal> getLazyList() {
		
		lazyList.setRowCount(dao.contaTodos());
		lazyList.setPageSize(5);
		
		return lazyList;
	}
	
	@Transactional
	public void atualiza(NotaFiscal entity) {
		
		if (((EntityInterface) entity).getId() != null) { 
			dao.atualiza(entity); 
		}
		 
		list = dao.listaTodos();
	}
	
	@Transactional
	public void grava() {
		
		if (((EntityInterface) entity).getId() == null) { 
			dao.adiciona(entity);
			limpa();
		} else {
			dao.atualiza(entity); 
		}
		 
		list = dao.listaTodos();
	}

	@Transactional
	public void remove(NotaFiscal entity) {
		dao.remove(entity);
		list = dao.listaTodos();
	}
	
	protected void limpa() {
		entity = new NotaFiscal();
	}
}
