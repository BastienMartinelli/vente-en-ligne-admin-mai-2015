package com.dta.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.dta.entities.Article;
import com.dta.entities.Utilisateur;
import com.dta.metier.SearchArticleEJB;
import com.dta.metier.SearchProduitEJB;
import com.dta.metier.SearchUtilisateurEJB;

@ManagedBean(name="research")
@ViewScoped
public class ResearchController {
    

    private boolean searchAllArticles;
    private boolean searchAllUtilisateurs;
    
	//user fields
	private String userName;
	private String userFirstName;
	private String userMail;
	private String userLogin;
	private String userType;

	//article fields
	private String articleName;
	private String articleProduct;
	private String articleId;
	private String articlePrice;
	private String articleStock;
	private String articleCatalogue;

	@EJB
	private SearchArticleEJB searchArticle;

	@EJB
	private SearchProduitEJB searchProduit;

	@EJB
	private SearchUtilisateurEJB searchUtilisateur;

	// research results
	private List<Article> products;
	private List<Utilisateur> users;

	public ResearchController(){
		this.userName = "";
		this.userFirstName = "";
		this.userMail = "";
		this.userLogin = "";
		this.userType = "";
		this.articleName = "";
		this.articleProduct = "";
		this.articleId = "";
		this.articlePrice = "";
		this.articleStock = "";
		this.articleCatalogue = "";
		this.products = new ArrayList<Article>();
		this.users = new ArrayList<Utilisateur>();
	}

	/*
	 *  Methods research ARTICLE
	 */



	public void logout() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("authentification.xhtml");
	}

	
	public void reDoLastSearchArticles() {
		if(searchAllArticles) {
			submitResearchAllArticle();
		} else {
			submitResearchArticle();
		}
	}
	
	public void submitResearchArticle() {
		searchAllArticles = false;
		//priority to research by id
		if(!"".equals(this.articleId)){
			int searchArticleId = Integer.parseInt(this.articleId);
			products = searchArticle.findById(searchArticleId);

		}else{
			// create a model article based on the search fields
			Article modelArticle = new Article();
			modelArticle.setNom( ("".equals(this.articleName)) ? null : this.articleName);
			modelArticle.setPrix( ("".equals(this.articlePrice)) ? -1 : Float.parseFloat(this.articlePrice));
			modelArticle.setStock( ("".equals(this.articleStock)) ? -1 : Integer.parseInt(this.articleStock));

			products = searchArticle.findDetail(modelArticle, this.articleProduct, this.articleCatalogue);
		}
		updateArticlesList();
	}

	public void submitResearchAllArticle(){
		searchAllArticles = true;
		products = searchArticle.findAll();
		updateArticlesList();
	}
	
	private void updateArticlesList() {
		RequestContext.getCurrentInstance().update("resultForm:results");
	}


	/*
	 * Methods research USER
	 */	

	public void reDoLastSearchUsers() {
		if(searchAllUtilisateurs) {
			submitResearchAllUser();
		} else {
			submitResearchUser();
		}
	}	
	
	public void submitResearchUser(){
		searchAllUtilisateurs = false;
		Utilisateur modelUtilisateur = new Utilisateur();
		modelUtilisateur.setLogin("".equals(this.userLogin) ? null : this.userLogin);
		modelUtilisateur.setNom("".equals(this.userName) ? null : this.userName);
		modelUtilisateur.setPrenom("".equals(this.userFirstName) ? null : this.userFirstName);
		modelUtilisateur.setEmail("".equals(this.userMail) ? null : this.userMail);
		modelUtilisateur.setTypeUtil(("".equals(this.userType) || this.userType == null) ? null : this.userType.substring(0, 1));

		users = searchUtilisateur.findDetail(modelUtilisateur);
		updateUsersList();
	}

	public void submitResearchAllUser(){
		searchAllUtilisateurs = true;
		users = searchUtilisateur.findAll();
		updateUsersList();
	}

	private void updateUsersList() {
		RequestContext.getCurrentInstance().update("searchUserForm:results");
	}

	@Override
	public String toString() {
		return "ResearchController [userName=" + userName + ", userFirstName="
				+ userFirstName + ", userMail=" + userMail + ", userLogin="
				+ userLogin + ", userType=" + userType + ", articleName="
				+ articleName + ", articleProduct=" + articleProduct
				+ ", articleId=" + articleId + ", articlePrice=" + articlePrice
				+ ", articleStock=" + articleStock + ", searchArticle="
				+ searchArticle + ", searchProduit=" + searchProduit
				+ ", products=" + products + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleProduct() {
		return articleProduct;
	}

	public void setArticleProduct(String articleProduct) {
		this.articleProduct = articleProduct;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticlePrice() {
		return articlePrice;
	}

	public void setArticlePrice(String articlePrice) {
		this.articlePrice = articlePrice;
	}

	public String getArticleStock() {
		return articleStock;
	}

	public void setArticleStock(String articleStock) {
		this.articleStock = articleStock;
	}

	public String getArticleCatalogue() {
		return articleCatalogue;
	}

	public void setArticleCatalogue(String articleCatalogue) {
		this.articleCatalogue = articleCatalogue;
	}

	public SearchArticleEJB getSearchArticle() {
		return searchArticle;
	}

	public void setSearchArticle(SearchArticleEJB searchArticle) {
		this.searchArticle = searchArticle;
	}

	public SearchProduitEJB getSearchProduit() {
		return searchProduit;
	}

	public void setSearchProduit(SearchProduitEJB searchProduit) {
		this.searchProduit = searchProduit;
	}

	public SearchUtilisateurEJB getSearchUtilisateur() {
		return searchUtilisateur;
	}

	public void setSearchUtilisateur(SearchUtilisateurEJB searchUtilisateur) {
		this.searchUtilisateur = searchUtilisateur;
	}

	public List<Article> getProducts() {
		return products;
	}

	public void setProducts(List<Article> products) {
		this.products = products;
	}

	public List<Utilisateur> getUsers() {
		for(Utilisateur u :users){
			u.setTypeUtil(Util.getFullUserType(u.getTypeUtil()));
		}
		return users;
	}

	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}

}
