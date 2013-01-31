package br.com.caelum.notasfiscais.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.caelum.notasfiscais.annotation.Transactional;

@SuppressWarnings("serial")
@Interceptor 
@Transactional
public class TransactionInterceptor implements Serializable {

	@Inject
	private EntityManager em;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		
		System.out.println("Abrindo a transação");
		
		em.getTransaction().begin();
		
		Object resultado = ctx.proceed();
		
		em.getTransaction().commit();
		System.out.println("Comitando a transação");
		
		return resultado;
		
	}
	
}
