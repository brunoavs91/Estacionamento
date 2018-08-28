package br.com.estacionamento.dao;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import br.com.estacionamento.domain.RegistroEntradaSaida;
import br.com.estacionamento.util.HibernateUtil;

public class RegistroEntradaSaidaDAO extends GenericDAO<RegistroEntradaSaida>{
	
	@SuppressWarnings("deprecation")
	public RegistroEntradaSaida buscarPlaca(String placa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
		
			TypedQuery<RegistroEntradaSaida> query = sessao.createQuery("from RegistroEntradaSaida reg where reg.placa = :placa", RegistroEntradaSaida.class);
			query.setParameter("placa", placa);
			return query.getSingleResult();
			
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
