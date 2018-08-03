package br.com.estacionamento.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.estacionamento.dao.RegistroEntradaSaidaDAO;
import br.com.estacionamento.domain.RegistroEntradaSaida;
import br.com.estacionamento.enumeration.TipoVeiculo;

@ManagedBean
@ViewScoped
public class RegEntradaSaidaAction implements Serializable {
	private static final long serialVersionUID = -3529722568709950696L;

	private List<RegistroEntradaSaida> listaEntradaSaida = new ArrayList<>();

	private RegistroEntradaSaida regEntradaSaida = new RegistroEntradaSaida();
	 

	public List<RegistroEntradaSaida> getListaEntradaSaida() {
		return listaEntradaSaida;
	}

	public void setListaEntradaSaida(List<RegistroEntradaSaida> listaEntradaSaida) {
		this.listaEntradaSaida = listaEntradaSaida;
	}

	public RegistroEntradaSaida getRegEntradaSaida() {
		return regEntradaSaida;
	}

	public void setRegEntradaSaida(RegistroEntradaSaida regEntradaSaida) {
		this.regEntradaSaida = regEntradaSaida;
	}

	public void listar() {
		try {
			RegistroEntradaSaidaDAO regEntradaSaida = new RegistroEntradaSaidaDAO();
			listaEntradaSaida = regEntradaSaida.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Registros");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			regEntradaSaida = new RegistroEntradaSaida();

			RegistroEntradaSaidaDAO regEntradaSaida = new RegistroEntradaSaidaDAO();
			listaEntradaSaida = regEntradaSaida.listar();
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Clientes");
			erro.printStackTrace();
		}
	}
	

	public void salvar() {
		try {
			RegistroEntradaSaidaDAO regEntradaSaidaDAO = new RegistroEntradaSaidaDAO();
			regEntradaSaida.setDataEntrada(new Date());
			regEntradaSaidaDAO.merge(regEntradaSaida);
			
			regEntradaSaida = new RegistroEntradaSaida();
			listaEntradaSaida = regEntradaSaidaDAO.listar();
			Messages.addFlashGlobalInfo("Registro salvo com sucesso");

		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar os Registro");
			erro.printStackTrace();
		}
	}
	public void excluir(ActionEvent evento) {
		regEntradaSaida = (RegistroEntradaSaida) evento.getComponent().getAttributes().get("registroSelecionado");
		Messages.addGlobalInfo("Registro :" + regEntradaSaida.getCodigo());
	}
	
	public void editar(RegistroEntradaSaida regEntradaSaida) {
		
		RegistroEntradaSaidaDAO regEntradaSaidaDAO = new RegistroEntradaSaidaDAO();
		regEntradaSaidaDAO.editar(regEntradaSaida);
		Messages.addGlobalInfo("Registro :" + regEntradaSaida.getCodigo());
	}
	
	public Long calcular(RegistroEntradaSaida regEntradaSaida){
		
		RegistroEntradaSaidaDAO regEntradaSaidaDAO = new RegistroEntradaSaidaDAO();
		regEntradaSaidaDAO.buscarPlaca(regEntradaSaida.getPlaca());
		regEntradaSaidaDAO.merge(regEntradaSaida);	
		return regEntradaSaida.getDataSaida().getTime() - regEntradaSaida.getDataEntrada().getTime();
		
	}
	
	public List<TipoVeiculo> getTiposVeiculos(){
		return Arrays.asList(TipoVeiculo.values());
	}
	
}
