package br.com.caelum.notasfiscais.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@SuppressWarnings("serial")
public class NotaFiscalDataModel extends LazyDataModel<NotaFiscal> {

	private DAO<NotaFiscal> dao;
	
	public NotaFiscalDataModel(DAO<NotaFiscal> dao) {
		this.dao = dao;
	}
	
	@Override
	public List<NotaFiscal> load(int inicio, int quantidade, String campoOrdenacao,
			SortOrder sentidoOrdenacao, Map<String, String> filtros) {
		return dao.listaTodosPaginada(inicio, quantidade);
	}

}
