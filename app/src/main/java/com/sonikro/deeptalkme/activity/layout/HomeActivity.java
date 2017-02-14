package com.sonikro.deeptalkme.activity.layout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.control.HomeControl;

public class HomeActivity extends DrawerActivity {
    public Button btnSearchDiscussion, btnSelectLanguage, btnSelectSubjects;
    HomeControl myHomeControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeComponents();
        initializeController();
        myHomeControl = (HomeControl) getController();
        setListeners();
        validateUser();
        //myHomeControl.endPossibleOpenDiscussion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        validateUser();
    }


    protected void validateUser()
    {
        if(!myHomeControl.isUserReady())
        {
            Toast.makeText(this,"You need to complete setting up your data",Toast.LENGTH_LONG).show();
            myHomeControl.callUpdateUserActivity();
        }
    }
    @Override
    protected void initializeComponents(){
        super.initializeComponents();
        btnSearchDiscussion = (Button) findViewById(R.id.home_btn_search_discussion);
        btnSelectLanguage = (Button) findViewById(R.id.home_btn_select_language);
        btnSelectSubjects = (Button) findViewById(R.id.home_btn_select_subjects);
        setSnackbarView(findViewById(R.id.home_root));
    }

    @Override
    protected void setListeners()
    {
        super.setListeners();
        btnSearchDiscussion.setOnClickListener(getController());
        btnSelectLanguage.setOnClickListener(getController());
        btnSelectSubjects.setOnClickListener(getController());
    }

}
