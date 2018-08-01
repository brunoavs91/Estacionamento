package br.com.estacionamento.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.estacionamento.dao.UsuarioDAO;
import br.com.estacionamento.domain.Usuario;

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

	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Usuarios");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			usuario = new Usuario();

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar criar um novo Usuario");
			erro.printStackTrace();
		}
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

	public void excluir(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		Messages.addGlobalInfo("Usuario :" + usuario.getNome());
	}

	public void editar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		Messages.addGlobalInfo("Usuario :" + usuario.getNome());
	}
}
