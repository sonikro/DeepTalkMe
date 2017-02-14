package com.sonikro.deeptalkme.inputValidator;

/**
 * Created by Jonathan Nagayoshi on 30/05/2016.
 */
public class MessageValidator extends InputValidator {
    @Override
    public void isValid(String input) throws InputException {
        if(input.isEmpty())
        {
            throw new InputException("Message can not be empty");
        }
        if(input.length() > 1000)
        {
            throw new InputException("Message can not contain more than 1000 characters");
        }
    }
}
