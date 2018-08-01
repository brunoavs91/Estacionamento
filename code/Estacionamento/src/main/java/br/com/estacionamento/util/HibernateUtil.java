package br.com.estacionamento.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes= criarFabricaDeSessoes();
	
	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}
	
	
	private static SessionFactory criarFabricaDeSessoes() {
		try {
			Configuration configuracao = new Configuration().configure();
			
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
			
			SessionFactory fabrica = configuracao.buildSessionFactory();
			return fabrica;
		}catch (Throwable ex) {
			System.out.println("A fabrica de sessoes nao pode ser criada."+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}

