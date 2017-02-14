package com.sonikro.deeptalkme.activity.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.inputValidator.InputException;
import com.sonikro.deeptalkme.inputValidator.InputValidator;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends SuperActivity {


    public AutoCompleteTextView myEmail;
    public EditText myPassword;
    public Button myLoginButton;
    public Button mySignUpButton;
    public Button myRequestNewPasswordButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
        initializeController();
        setListeners();

    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();
        myEmail = (AutoCompleteTextView) findViewById(R.id.header_email);
        myPassword = (EditText) findViewById(R.id.password);
        myLoginButton = (Button) findViewById(R.id.email_sign_in_button);
        mySignUpButton = (Button) findViewById(R.id.sign_up);
        myToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        myRequestNewPasswordButton = (Button) findViewById(R.id.login_request_new_password);
        setSnackbarView(findViewById(R.id.login_root));
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    protected void setListeners() {
        myLoginButton.setOnClickListener(getController());
        mySignUpButton.setOnClickListener(getController());
        myRequestNewPasswordButton.setOnClickListener(getController());
    }

    public boolean validateFields(){
        InputValidator validator;
        boolean result = false;
        try
        {
            validator = InputValidator.factory(InputValidator.EMAIL);
            validator.validateTextView(myEmail);

            validator = InputValidator.factory(InputValidator.PASSWORD);
            validator.validateTextView(myPassword);

            return true;
        }
        catch(InputException exception)
        {
            exception.displayInputError();
            return false;
        }
        catch(Exception exception)
        {
            return false;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RegisterActivity.CLOSE_ACTIVITY)
        {
            finish();
        }
    }

    public boolean validateRequestPasswordFields() {
        InputValidator validator;
        boolean result = false;
        try
        {
            validator = InputValidator.factory(InputValidator.EMAIL);
            validator.validateTextView(myEmail);

            return true;
        }
        catch(InputException exception)
        {
            exception.displayInputError();
            return false;
        }
        catch(Exception exception)
        {
            return false;
        }
    }
}

