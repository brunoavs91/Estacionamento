package br.com.estacionamento.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.estacionamento.domain.RegistroEntradaSaida;
import br.com.estacionamento.util.HibernateUtil;

public class RegistroEntradaSaidaDAO extends GenericDAO<RegistroEntradaSaida>{
	
	public RegistroEntradaSaida buscarPlaca(String placa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(placa));
			@SuppressWarnings("unchecked")
			RegistroEntradaSaida resultado = (RegistroEntradaSaida) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
