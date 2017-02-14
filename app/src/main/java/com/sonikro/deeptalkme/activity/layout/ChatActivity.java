package com.sonikro.deeptalkme.activity.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.control.ChatControl;
import com.sonikro.deeptalkme.inputValidator.InputException;
import com.sonikro.deeptalkme.inputValidator.InputValidator;
import com.sonikro.deeptalkme.model.chat.ChatMessage;

public class ChatActivity extends SuperActivity {
    public ImageButton btnSend;
    public EditText txtMessage;
    public RecyclerView chatView;
    protected ChatControl myController;
    public DiscussionMessageView mDiscussionMessageView;

    @Override
    protected void onNewIntent(Intent intent) {
        //super.onNewIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return myController.handleOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        myController.destroyServices();
        myController.leaveDiscussion();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initializeComponents();
        initializeController();
        setListeners();
        setupMessageDiscussionView();
        myController.validateDiscussion();
        myController.setupMessageReaderService();
        System.out.println("Chat Activity onCreate");
    }

    protected void setupMessageDiscussionView()
    {
        mDiscussionMessageView = new DiscussionMessageView(chatView, myController.getDiscussion(), getApplicationContext());
    }
    public boolean validateInput()
    {
        try {
            InputValidator validator = InputValidator.factory(InputValidator.MESSAGE);
            validator.validateTextView(txtMessage);
            return true;
        }catch(InputException inputException)
        {
            inputException.displayInputError();
            return false;
        }catch(Exception exception)
        {
            return false;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        myController.validateDiscussion();
        myController.validateServices();
    }



    @Override
    public void onBackPressed(int action) {
        if (action == SuperActivity.EXIT_APPLICATION)
        {
            myController.goHome(true);
        }
    }

    @Override
    public void onBackPressed() {
        myController.goHome(true);
    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();
        btnSend = (ImageButton) findViewById(R.id.chat_btn_send);
        txtMessage = (EditText) findViewById(R.id.chat_message);
        chatView = (RecyclerView) findViewById(R.id.chat_recycler_view);
        setSnackbarView(findViewById(R.id.root_chat));
    }

    @Override
    protected void initializeController() {
        super.initializeController();
        myController = (ChatControl) getController();
    }

    @Override
    protected void setListeners() {
        btnSend.setOnClickListener(getController());
    }

    public void addMessageToList(ChatMessage message)
    {
        mDiscussionMessageView.add(message);
    }

}
