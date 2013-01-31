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
import br.com.caelum.notasfiscais.dao.ProdutoDAO;
import br.com.caelum.notasfiscais.datamodel.ProdutoDataModel;
import br.com.caelum.notasfiscais.modelo.EntityInterface;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.modelo.QuantidadePorProduto;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	@Inject
	private Produto entity;
	
	private List<Produto> list;
	
	private LazyDataModel<Produto> lazyList;
	
	@Inject
	private ProdutoDAO produtoDao;
	
	@Inject
	private DAO<Produto> dao;
	
	public ProdutoBean() {
	}

	@PostConstruct
	public void init() {
		lazyList = new ProdutoDataModel(dao);
	}
	
	public List<QuantidadePorProduto> getRelatorioQuantidadePorProduto() {
		return produtoDao.relatorioQuantidadePorProduto();
	}
	
	/*
	public void comecaComMaiuscula(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		
		String valor = value.toString();
		if(!valor.matches("[A-Z].*")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com maiúscula"));
		}
		
	}
	*/
	
	public String cancela() {
		return "produto";
	}
	
	public Produto getEntity() {
		return entity;
	}

	public void setEntity(Produto entity) {
		this.entity = entity;
	}

	public List<Produto> getList() {
		if (list == null) {
			
			list = dao.listaTodos();
		}
		return list; 
	}
	
	public LazyDataModel<Produto> getLazyList() {
		
		lazyList.setRowCount(dao.contaTodos());
		lazyList.setPageSize(5);
		
		return lazyList;
	}
	
	@Transactional
	public void atualiza(Produto entity) {
		
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
	public void remove(Produto entity) {
		dao.remove(entity);
		list = dao.listaTodos();
	}
	
	protected void limpa() {
		entity = new Produto();
	}
	
}
