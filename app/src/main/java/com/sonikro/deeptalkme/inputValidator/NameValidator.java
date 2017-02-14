package com.sonikro.deeptalkme.inputValidator;

/**
 * Created by Jonathan Nagayoshi on 21/06/2016.
 */
public class NameValidator extends InputValidator {
    @Override
    public void isValid(String input) throws InputException {
        if(input.length() < 4)
        {
            throw new InputException("Name can't be shorter than 4 characters");
        }
    }
}
