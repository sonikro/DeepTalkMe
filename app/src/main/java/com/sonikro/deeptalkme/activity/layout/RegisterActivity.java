package com.sonikro.deeptalkme.activity.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.inputValidator.InputException;
import com.sonikro.deeptalkme.inputValidator.ConfirmPasswordValidator;
import com.sonikro.deeptalkme.inputValidator.InputValidator;

public class RegisterActivity extends SuperActivity {
    public static final int CLOSE_ACTIVITY = 10;
    public Button myRegisterButton;
    public EditText txtEmail, txtPassword, txtConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeComponents();
        initializeController();
        setListeners();
    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();
        setSnackbarView(findViewById(R.id.register_root));
        myRegisterButton = (Button) findViewById(R.id.register_register);
        txtEmail = (EditText) findViewById(R.id.register_email);
        txtPassword = (EditText) findViewById(R.id.register_password);
        txtConfirmPassword = (EditText) findViewById(R.id.register_confirm_password);
        getSupportActionBar().setTitle(R.string.title_activity_register);
    }

    @Override
    protected void setListeners() {
        myRegisterButton.setOnClickListener(getController());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == CLOSE_ACTIVITY)
        {
            finish();
        }
    }

    public boolean validateInput()
    {
        InputValidator validator;
        ConfirmPasswordValidator confirmPasswordValidator;
        boolean result = false;
        try
        {
            validator = InputValidator.factory(InputValidator.EMAIL);
            validator.validateTextView(txtEmail);

            validator = InputValidator.factory(InputValidator.PASSWORD);
            validator.validateTextView(txtPassword);

            confirmPasswordValidator = (ConfirmPasswordValidator) InputValidator.factory(InputValidator.CONFIRM_PASSWORD);
            confirmPasswordValidator.setFirstPassword(txtPassword.getText().toString());
            confirmPasswordValidator.validateTextView(txtConfirmPassword);

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
