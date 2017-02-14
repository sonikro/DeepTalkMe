package com.sonikro.deeptalkme.inputValidator;

/**
 * Created by Jonathan Nagayoshi on 20/06/2016.
 */
public class ConfirmPasswordValidator extends InputValidator {
    private String firstPassword;
    @Override
    public void isValid(String input) throws InputException {
        if(!input.equals(getFirstPassword()))
        {
            throw new InputException("Passwords don`t match");
        }
    }

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }
}
