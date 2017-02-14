package com.sonikro.deeptalkme.model;

import com.sonikro.deeptalkme.DAO.UserDAO;
import com.sonikro.deeptalkme.model.authentication.UserAuthenticator;

import java.io.Serializable;

/**
 * Created by Jonathan Nagayoshi on 20/05/2016.
 */
public class User implements Serializable{
    private Integer cod_user;
    private String email;
    private String password;
    private String name;
    private Token token;
    private Subject[] favoriteSubjects;
    private Language[] favoriteLanguages;

    public User()
    {

    }
    public User(User copyFromUser)
    {
        setCod_user(copyFromUser.getCod_user());
        setName(copyFromUser.getName());
        setFavoriteLanguages(copyFromUser.getFavoriteLanguages());
        setFavoriteSubjects(copyFromUser.getFavoriteSubjects());
        setEmail(copyFromUser.getEmail());
        setPassword(copyFromUser.getPassword());
        setToken(copyFromUser.getToken());
    }

    public void authenticate(String authenticationMethod) throws Exception {
        UserAuthenticator authenticator = UserAuthenticator.factory(authenticationMethod);
        User authenticatedUser = authenticator.authenticate(this);
        this.setName(authenticatedUser.getName());
        this.setToken(authenticatedUser.getToken());
        this.setEmail(authenticatedUser.getEmail());
        this.setFavoriteLanguages(authenticatedUser.getFavoriteLanguages());
        this.setFavoriteSubjects(authenticatedUser.getFavoriteSubjects());
        this.setCod_user(authenticatedUser.getCod_user());
    }

    public void updateUser(String new_password) throws Exception {
        UserDAO.getInstance().updateUserSettings(this, new_password);
    }
    public void validate() throws Exception{
        if(getFavoriteLanguages() == null || getFavoriteLanguages().length < 1)
        {
            throw new Exception("User must select at least one language");
        }

        if(getFavoriteSubjects() == null || getFavoriteSubjects().length < 1 )
        {
            throw new Exception("User must select at least one subject");
        }
    }
    public Integer getCod_user() {
        return cod_user;
    }

    public void setCod_user(Integer cod_user) {
        this.cod_user = cod_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void register() throws Exception{
        UserDAO.getInstance().registerUser(this);
    }

    public Subject[] getFavoriteSubjects() {
        return favoriteSubjects;
    }

    public void setFavoriteSubjects(Subject[] favoriteSubjects) {
        this.favoriteSubjects = favoriteSubjects;
    }

    public Language[] getFavoriteLanguages() {
        return favoriteLanguages;
    }

    public void setFavoriteLanguages(Language[] favoriteLanguages) {
        this.favoriteLanguages = favoriteLanguages;
    }

    public void requestNewPassword() throws Exception {
        UserDAO.getInstance().requestNewPassword(this);
    }

    public User[] getFriends() throws Exception
    {
        return UserDAO.getInstance().getFriends(this);
    }

    public String getInitials() {
        String[] names = getName().split(" ");
        StringBuilder sb = new StringBuilder();
        try {
            for ( String singleName : names )
            {
                sb.append(singleName.charAt(0));
            }
        }catch(StringIndexOutOfBoundsException ex)
        {

        }

        return sb.toString().toUpperCase();
    }
}
