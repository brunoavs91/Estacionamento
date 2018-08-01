package br.com.estacionamento.action;


import org.omnifaces.util.Messages;

import br.com.estacionamento.domain.Usuario;

public class login {
	
	private Usuario usuario = new Usuario();
	
	
	public String efetuarLogin(Usuario usuario) {
		
		if(usuario.getEmail().equals(getUsuario()) && usuario.getCpf().equals(getUsuario())) {
			Messages.addFlashGlobalError("Usuario Logado");
			return "template.xhtml";
		}else {
			Messages.addFlashGlobalError("Login incorreto");
		return null;
		}
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
