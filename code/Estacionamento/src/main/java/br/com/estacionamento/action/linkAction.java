package br.com.estacionamento.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class linkAction {
	
	public String linkCliente() {
		
		return "ClienteAction.xhtml";
	}
	
	public String linkUsuario() {
		
		return"usuarioAction.xhtml";
	}
}
