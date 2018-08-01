package br.com.estacionamento.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.estacionamento.dao.ClienteDAO;
import br.com.estacionamento.domain.Cliente;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ClienteAction implements Serializable {

	private List<Cliente> clientes = new ArrayList<>();

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	private Cliente cliente = new Cliente();

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();
			Messages.addFlashGlobalError("Lista Carregada");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			cliente = new Cliente();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Clientes");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			cliente = new Cliente();
			clientes = clienteDAO.listar();

			Messages.addFlashGlobalInfo("Cliente salvo com sucesso");

		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar os Clientes");
			erro.printStackTrace();
		}
	}
/*
	public void excluir() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);

			clientes = clienteDAO.listar();

			Messages.addFlashGlobalInfo("Cliente excluido  com sucesso");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir os Clientes");
			erro.printStackTrace();
		}
	}*/
	public void excluir(ActionEvent evento) {
		try {	
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.excluir(cliente);
		
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir os Clientes");
			erro.printStackTrace();
		}
	}
	
	public void editar(Cliente cliente) {
	try {	
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.editar(cliente);
	}catch (Exception erro) {
		Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar os Clientes");
		erro.printStackTrace();
	}
	}
}