/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinhh.register;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class RegisterErrorObject implements Serializable{
    private String emailError,passwordError,rePasswordError,phoneNumberError;

    public RegisterErrorObject(String emailError, String passwordError, String rePasswordError, String phoneNumberError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.rePasswordError = rePasswordError;
        this.phoneNumberError = phoneNumberError;
    }

    public RegisterErrorObject() {
    }
    

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRePasswordError() {
        return rePasswordError;
    }

    public void setRePasswordError(String rePasswordError) {
        this.rePasswordError = rePasswordError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }
    
    
    
}
