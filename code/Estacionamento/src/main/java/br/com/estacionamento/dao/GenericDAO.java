package br.com.estacionamento.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.estacionamento.util.HibernateUtil;


public class GenericDAO<E> {

	public Class<E> classe;

	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}
	
	// para alterar dados das tabelas
		public void salvar(E entidade) {

			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();// abrindo uma sessao

			Transaction transacao = null;

			try {
				transacao = sessao.beginTransaction();
				sessao.save(entidade);
				transacao.commit();
			} catch (RuntimeException erro) {

				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
			} finally {
				sessao.close(); // fechando sessao
			}
		}
		
		public List<E> listar() {
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				@SuppressWarnings("deprecation")
				Criteria consulta = sessao.createCriteria(classe);
				@SuppressWarnings("unchecked")
				List<E> resultado = consulta.list();
				return resultado;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}

		}
		public E buscar(Long codigo) {
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				@SuppressWarnings("deprecation")
				Criteria consulta = sessao.createCriteria(classe);
				consulta.add(Restrictions.idEq(codigo));
				@SuppressWarnings("unchecked")
				E resultado = (E) consulta.uniqueResult();
				return resultado;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
		}

		public void excluir(E entidade) {

			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();// abrindo uma sessao

			Transaction transacao = null;

			try {
				transacao = sessao.beginTransaction();
				sessao.delete(entidade);
				transacao.commit();
			} catch (RuntimeException erro) {

				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
			} finally {
				sessao.close(); // fechando sessao
			}
		}
		public void editar(E entidade) {

			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();// abrindo uma sessao

			Transaction transacao = null;

			try {
				transacao = sessao.beginTransaction();
				sessao.update(entidade);
				transacao.commit();
			} catch (RuntimeException erro) {

				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
			} finally {
				sessao.close(); // fechando sessao
			}
		}
		public void merge(E entidade) {

			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();// abrindo uma sessao

			Transaction transacao = null;

			try {
				transacao = sessao.beginTransaction();
				sessao.merge(entidade);
				transacao.commit();
			} catch (RuntimeException erro) {

				if (transacao != null) {
					transacao.rollback();
				}
				throw erro;
			} finally {
				sessao.close(); // fechando sessao
			}
		}
}
