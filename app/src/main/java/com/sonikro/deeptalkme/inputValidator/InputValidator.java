package com.sonikro.deeptalkme.inputValidator;

import android.widget.TextView;

/**
 * Created by Jonathan Nagayoshi on 18/05/2016.
 */
public abstract class InputValidator {
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String MESSAGE = "MESSAGE";
    public static final String CONFIRM_PASSWORD = "CONFIRM_PASSWORD";
    public static final String NAME = "NAME";
    protected String myInput;
    protected InputValidator myValidator;

    public static InputValidator factory(String inputType) throws Exception {
        switch(inputType)
        {
            case EMAIL:
                return new EmailValidator();
            case PASSWORD:
                return new PasswordValidator();
            case MESSAGE:
                return new MessageValidator();
            case CONFIRM_PASSWORD:
                return new ConfirmPasswordValidator();
            case NAME:
                return new NameValidator();
            default:
                throw new Exception("Invalid InputType");
        }
    }

    public abstract void isValid(String input) throws InputException;

    public void validateTextView(TextView textView) throws InputException
    {
        try {
            isValid(textView.getText().toString());
        }catch(InputException ex)
        {
            throw new InputException(textView, ex.getMessage());
        }

    }
}
