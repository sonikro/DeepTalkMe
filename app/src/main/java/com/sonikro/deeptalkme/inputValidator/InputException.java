package com.sonikro.deeptalkme.inputValidator;

import android.widget.TextView;

/**
 * Created by Jonathan Nagayoshi on 18/05/2016.
 */
public class InputException extends Exception {
    TextView myTextView;

    public InputException(String message)
    {
        super(message);
    }

    public InputException(TextView textView, String errorMessage)
    {
        super(errorMessage);
        myTextView = textView;
    }

    public void displayInputError()
    {
        myTextView.setError(getMessage());
        myTextView.requestFocus();
    }
}
