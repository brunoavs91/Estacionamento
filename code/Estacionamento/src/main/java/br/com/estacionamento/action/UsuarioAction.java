package br.com.estacionamento.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.estacionamento.dao.UsuarioDAO;
import br.com.estacionamento.dao.VagaDAO;
import br.com.estacionamento.domain.RegistroEntradaSaida;
import br.com.estacionamento.domain.Usuario;
import br.com.estacionamento.enumeration.TamanhoVaga;
import br.com.estacionamento.enumeration.TipoUsuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioAction implements Serializable {

	private Usuario usuario = new Usuario();

	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Usuarios");
			erro.printStackTrace();
		}
	}

	public void limpar() {
			this.setUsuario(new Usuario());
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuario = new Usuario();
			usuarios = usuarioDAO.listar();
			Messages.addFlashGlobalInfo("Usuario salvo com sucesso");

		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o Usuario");
			erro.printStackTrace();
		}
	}

	public void excluir() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			usuarioDAO.listar();

			Messages.addFlashGlobalInfo("Usuario excluido  com sucesso");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir os Usuario");
			erro.printStackTrace();
		}
	}

	public void editar(RegistroEntradaSaida regEntradaSaida) {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.editar(usuario);;
			
			Messages.addGlobalInfo("Usuario editada");
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Erro ao editar");
			erro.printStackTrace();
		}
	}
	public List<TipoUsuario> getTipoUsuarios() {
		return Arrays.asList(TipoUsuario.values());
	}
	}
