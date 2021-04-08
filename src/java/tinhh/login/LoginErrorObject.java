/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.login;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class LoginErrorObject implements Serializable{
    private String usernameError,passwordError;

    public LoginErrorObject() {
    }

    public LoginErrorObject(String usernameError, String passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
}
