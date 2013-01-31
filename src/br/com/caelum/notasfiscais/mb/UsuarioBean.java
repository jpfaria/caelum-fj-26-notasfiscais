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
import br.com.caelum.notasfiscais.datamodel.UsuarioDataModel;
import br.com.caelum.notasfiscais.modelo.EntityInterface;
import br.com.caelum.notasfiscais.modelo.Usuario;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	@Inject
	protected Usuario entity;
	
	protected List<Usuario> list;
	protected LazyDataModel<Usuario> lazyList;
	
	@Inject
	private DAO<Usuario> dao;
	
	public UsuarioBean() {
	}
	
	@PostConstruct
	public void init() {
		lazyList = new UsuarioDataModel(dao);
	}
	
	public String cancela() {
		return "usuario";
	}
	
	public Usuario getEntity() {
		return entity;
	}

	public void setEntity(Usuario entity) {
		this.entity = entity;
	}

	public List<Usuario> getList() {
		if (list == null) {
			list = dao.listaTodos();
		}
		return list; 
	}
	
	public LazyDataModel<Usuario> getLazyList() {
		
		lazyList.setRowCount(dao.contaTodos());
		lazyList.setPageSize(5);
		
		return lazyList;
	}
	
	@Transactional
	public void atualiza(Usuario entity) {
		
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
	public void remove(Usuario entity) {
		dao.remove(entity);
		list = dao.listaTodos();
	}
	
	protected void limpa() {
		entity = new Usuario();
	}

}
