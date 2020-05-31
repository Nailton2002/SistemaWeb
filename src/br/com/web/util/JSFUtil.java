package br.com.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	// CRIANDO MENSAGEM DE ALERTA COM GROWL
	// CRIANDO CLASSE PARA MENSAGEM COM SUCESSO
	public static void adicionarMensagemSucesso(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

	// CRIANDO CLASSE PARA MENSAGEM DE ERRO
	public static void adicionarMensagemErro(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

}
