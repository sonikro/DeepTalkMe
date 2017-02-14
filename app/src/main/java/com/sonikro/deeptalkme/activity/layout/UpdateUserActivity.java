package com.sonikro.deeptalkme.activity.layout;

import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.widget.Button;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.inputValidator.InputException;
import com.sonikro.deeptalkme.inputValidator.ConfirmPasswordValidator;
import com.sonikro.deeptalkme.inputValidator.InputValidator;

public class UpdateUserActivity extends DrawerActivity {
    public TextInputEditText txtName, txtCurrentPassword, txtNewPassword, txtConfirmNewPassword, txtEmail;
    public Button btnSubjects, btnLanguages, btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initializeComponents();
        initializeController();
        setListeners();
    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();

        txtName = (TextInputEditText) findViewById(R.id.update_username);
        txtCurrentPassword = (TextInputEditText) findViewById(R.id.update_original_password);
        txtNewPassword = (TextInputEditText) findViewById(R.id.update_password);
        txtConfirmNewPassword = (TextInputEditText) findViewById(R.id.update_confirm_password);
        txtEmail = (TextInputEditText) findViewById(R.id.update_email);
        btnSubjects = (Button) findViewById(R.id.update_subjects);
        btnLanguages = (Button) findViewById(R.id.update_languages);
        btnSave = (Button) findViewById(R.id.update_save);
        setSnackbarView(findViewById(R.id.update_user_root));

        txtName.setText(getMyApplication().getUser().getName());
        txtEmail.setText(getMyApplication().getUser().getEmail());
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        btnSubjects.setOnClickListener(getController());
        btnLanguages.setOnClickListener(getController());
        btnSave.setOnClickListener(getController());
    }

    public boolean validateInput()
    {
        try {
            InputValidator validator;
            validator = InputValidator.factory(InputValidator.NAME);
            validator.validateTextView(txtName);

            if(!txtCurrentPassword.getText().toString().isEmpty()) {
                validator = InputValidator.factory(InputValidator.PASSWORD);
                validator.validateTextView(txtCurrentPassword);
                validator.validateTextView(txtConfirmNewPassword);
                validator.validateTextView(txtNewPassword);
                ConfirmPasswordValidator passwordValidator = (ConfirmPasswordValidator)
                        InputValidator.factory(InputValidator.CONFIRM_PASSWORD);

                passwordValidator.setFirstPassword(txtNewPassword.getText().toString());
                passwordValidator.validateTextView(txtConfirmNewPassword);
            }

            if(txtCurrentPassword.getText().toString().isEmpty()
                    && ( ! txtNewPassword.getText().toString().isEmpty() ||
                    ! txtConfirmNewPassword.getText().toString().isEmpty()))
            {
                validator = InputValidator.factory(InputValidator.PASSWORD);
                validator.validateTextView(txtCurrentPassword);
            }


            return true;

        }catch(InputException exception)
        {
            exception.displayInputError();
            return false;
        }catch(Exception ex)
        {
            return false;
        }
    }

}
