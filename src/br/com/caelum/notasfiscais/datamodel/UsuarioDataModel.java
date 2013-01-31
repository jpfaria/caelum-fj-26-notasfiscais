package br.com.caelum.notasfiscais.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Usuario;

@SuppressWarnings("serial")
public class UsuarioDataModel extends LazyDataModel<Usuario> {

	private DAO<Usuario> dao;
	
	public UsuarioDataModel(DAO<Usuario> dao) {
		this.dao = dao;
	}
	
	@Override 
	public List<Usuario> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao,
			Map<String, String> filtros) {
		
		return dao.listaTodosPaginada(inicio, quantidade);
	}

}
