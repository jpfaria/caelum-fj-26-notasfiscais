package br.com.caelum.notasfiscais.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

@SuppressWarnings("serial")
public class ProdutoDataModel extends LazyDataModel<Produto> {

	private DAO<Produto> dao;
	
	public ProdutoDataModel(DAO<Produto> dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Produto> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao,
			Map<String, String> filtros) {
		
		return dao.listaTodosPaginada(inicio, quantidade);
	}
	

}
