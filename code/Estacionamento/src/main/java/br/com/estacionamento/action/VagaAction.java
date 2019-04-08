package br.com.estacionamento.action;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.estacionamento.dao.RegistroEntradaSaidaDAO;
import br.com.estacionamento.dao.VagaDAO;
import br.com.estacionamento.domain.RegistroEntradaSaida;
import br.com.estacionamento.domain.Vaga;
import br.com.estacionamento.enumeration.TamanhoVaga;
import br.com.estacionamento.enumeration.TipoVeiculo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VagaAction implements Serializable {

	private Vaga vaga;

	private List<Vaga> vagas;

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}
	@PostConstruct
	public void inicializar() {
		try {
			this.vaga=new Vaga();
			VagaDAO vagaDAO = new VagaDAO();
			vagas = vagaDAO.listar();
			
		}catch(RuntimeException erro) {
			erro.printStackTrace();
		}
	}

	public void listar() {
		try {
			VagaDAO vagaDAO = new VagaDAO();
			vagas = vagaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as vagas");
			erro.printStackTrace();
		}
	}

	public void limpar() {
			this.setVaga(new Vaga());
	}

	public void salvar() {
		try {
			VagaDAO vagaDAO = new VagaDAO();
			vagaDAO.merge(vaga);

			vaga = new Vaga();
			vagas = vagaDAO.listar();

			Messages.addFlashGlobalInfo("Vaga salva com sucesso");

		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar a Vaga");
			erro.printStackTrace();
		}
	}

	public void excluir() {
		try {
			VagaDAO vagaDAO = new VagaDAO();
			vagaDAO.excluir(vaga);

			vagas = vagaDAO.listar();

			Messages.addFlashGlobalInfo("Vaga excluida  com sucesso");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir as Vagas");
			erro.printStackTrace();
		}
	}
/*
	public void excluir(ActionEvent evento) {
		try {
			vaga = (Vaga) evento.getComponent().getAttributes().get("vagaSelecionado");

			VagaDAO vagaDAO = new VagaDAO();
			vagaDAO.excluir(vaga);
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a Vaga");
			erro.printStackTrace();
		}
	}*/

	public void editar(RegistroEntradaSaida regEntradaSaida) {
		try {
			VagaDAO vagaDAO = new VagaDAO();
			vagaDAO.editar(vaga);
			Messages.addGlobalInfo("Vaga editada");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Erro ao editar");
			erro.printStackTrace();
		}
	}

	public List<TamanhoVaga> getTamanhoVagas() {
		return Arrays.asList(TamanhoVaga.values());
	}
}
