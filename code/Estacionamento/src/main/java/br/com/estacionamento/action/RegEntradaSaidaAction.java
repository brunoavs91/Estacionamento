package br.com.estacionamento.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	
	@PostConstruct
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

	public void excluir(RegistroEntradaSaida registroEntradaSaida) {
		try {
			RegistroEntradaSaidaDAO regEntradaSaidaDAO = new RegistroEntradaSaidaDAO();
			regEntradaSaidaDAO.excluir(registroEntradaSaida);
			listaEntradaSaida = regEntradaSaidaDAO.listar();
			Messages.addFlashGlobalInfo("Registro excluido  com sucesso");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar os Registro");
			erro.printStackTrace();
		}
	}

	public void editar(RegistroEntradaSaida regEntradaSaida) {
		try {
			RegistroEntradaSaidaDAO regEntradaSaidaDAO = new RegistroEntradaSaidaDAO();
			regEntradaSaidaDAO.editar(regEntradaSaida);
			regEntradaSaidaDAO.merge(regEntradaSaida);
			Messages.addGlobalInfo("Registro editado");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Erro ao editar");
			erro.printStackTrace();
		}
	}

	public void calcular() {
		try {
			RegistroEntradaSaidaDAO regEntradaSaidaDAO = new RegistroEntradaSaidaDAO();
			regEntradaSaidaDAO.buscarPlaca(regEntradaSaida.getPlaca());
			regEntradaSaida.setDataSaida(new Date());
			Long calculo=regEntradaSaida.getDataSaida().getTime() - regEntradaSaida.getDataEntrada().getTime();
			
			if(calculo >= 0 && calculo <=15.00)
				regEntradaSaida.setValorPago(new BigDecimal(2.00));
			if(calculo > 15.00 && calculo <=30.00)
				regEntradaSaida.setValorPago(new BigDecimal(4.00));
			if(calculo > 30.00 && calculo <= 45.00)
				regEntradaSaida.setValorPago(new BigDecimal(6.00));
			if(calculo > 45.00 && calculo <= 60.00)
				regEntradaSaida.setValorPago(new BigDecimal(8.00));
			else if (calculo > 60.00)
				regEntradaSaida.setValorPago(new BigDecimal(15.00));
			
			regEntradaSaidaDAO.merge(regEntradaSaida);
			
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Erro ao calcular");
			erro.printStackTrace();
		}
		
	}

	public List<TipoVeiculo> getTiposVeiculos() {
		return Arrays.asList(TipoVeiculo.values());
	}

}
