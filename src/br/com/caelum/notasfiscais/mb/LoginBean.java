package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDAO;
import br.com.caelum.notasfiscais.modelo.Usuario;

@SuppressWarnings("serial")
@Named
@RequestScoped
public class LoginBean implements Serializable {

	@Inject
	private Usuario usuario;
	
	@Inject
	private UsuarioDAO dao;

	@Inject 
	private UsuarioLogado usuarioLogado;
	
	public String efetuaLogin(){
		
		boolean loginValido = dao.existe(this.usuario);
		
		if (loginValido) {
			usuarioLogado.setUsuario(usuario);
			return "produto?faces-redirect=true";
		} else {
			resetaUsuario();
			return "login";
		}
		
	}
	
	public String sair(){
		this.resetaUsuario();
		return "login?faces-redirect=true";
	}	
		
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public boolean isLogado() {
		return usuarioLogado.isLogado();
	}
	
	public void resetaUsuario() {
		usuarioLogado.setUsuario(null);
	}
	
}
