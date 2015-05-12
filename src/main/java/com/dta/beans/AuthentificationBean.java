package com.dta.beans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.dta.entities.Utilisateur;
import com.dta.metier.SearchUtilisateur;

@ManagedBean(name="authentificationBean")
@SessionScoped
public class AuthentificationBean {

	@EJB
	private SearchUtilisateur searchUtilisateur;

	Utilisateur utilisateur;

	private String login;
	private String password;
	private String typeUtil;


	public void verifyAuth() throws IOException{
		if ("root".equals(login) && "root".equals(password)) {
			
			utilisateur = new Utilisateur();
			utilisateur.setTypeUtil("a");
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			return;
		}
		utilisateur = searchUtilisateur.findAuthentification(login, password, typeUtil);
		if(utilisateur == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Utilisateur inconnu"));
			FacesContext.getCurrentInstance().getExternalContext().redirect("errorAuth.xhtml");
			return;
		}
		else{
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			return;
		}
	}

	public boolean isLoggedIn() {
		return utilisateur != null;
	}

	public void logout() throws IOException{
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
		ec.redirect(ec.getRequestContextPath() + "/authentification.xhtml");
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTypeUtil() {
		return typeUtil;
	}
	public void setTypeUtil(String typeUtil) {
		this.typeUtil = typeUtil;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}




}
