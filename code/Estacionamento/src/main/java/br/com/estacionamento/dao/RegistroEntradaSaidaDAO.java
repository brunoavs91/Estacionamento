package br.com.estacionamento.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.estacionamento.domain.RegistroEntradaSaida;
import br.com.estacionamento.util.HibernateUtil;

public class RegistroEntradaSaidaDAO extends GenericDAO<RegistroEntradaSaida>{
	
	@SuppressWarnings("deprecation")
	public RegistroEntradaSaida buscarPlaca(String placa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.like("placa", placa));
			return (RegistroEntradaSaida) consulta.uniqueResult();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
