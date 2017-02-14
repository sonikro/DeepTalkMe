package com.sonikro.deeptalkme.inputValidator;

/**
 * Created by Jonathan Nagayoshi on 18/05/2016.
 */
public class PasswordValidator extends InputValidator {
    @Override
    public void isValid(String input) throws InputException {
        if(! (input.length() > 4)){
            throw new InputException("Password is too short. Minimum 4 digits");
        }
    }
}
