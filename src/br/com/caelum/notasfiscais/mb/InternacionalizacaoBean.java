package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
public class InternacionalizacaoBean implements Serializable {

	@Inject 
	private FacesContext context;
	
	public void mudaPara(String lingua){
		
		Locale locale = new Locale(lingua);
		context.getViewRoot().setLocale(locale);
		context.getApplication().setDefaultLocale(locale);
		
	}
	
}
