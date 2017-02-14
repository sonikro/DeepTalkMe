package com.sonikro.deeptalkme.inputValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jonathan Nagayoshi on 18/05/2016.
 */
public class EmailValidator extends InputValidator{
    protected Pattern pattern;
    protected Matcher matcher;

    public static String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public void isValid(String input) throws InputException {
        matcher = pattern.matcher(input);
        if(!matcher.matches()) {
            throw new InputException("Invalid E-mail");
        };
    }

}
