package br.com.caelum.notasfiscais.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CriaEntityManagerFactoryParaAtualizarBanco {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("notas");
	}
	
}
