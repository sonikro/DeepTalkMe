package com.sonikro.deeptalkme.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.sonikro.deeptalkme.DAO.DiscussionException;
import com.sonikro.deeptalkme.model.Discussion;
import com.sonikro.deeptalkme.model.chat.MessageList;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class MessageReaderService extends Service {
    public static final String ACTION_READ_MESSAGES = "READ_MESSAGES";
    public static final String SERIALIZED_DISCUSSION = "SERIALIZED_DISCUSSION";
    public static final String SERIALIZED_MESSAGES = "SERIALIZED_MESSAGES";
    public static final String SERIALIZED_EXCEPTION = "SERIALIZED_EXCEPTION";
    public static final int INTERVAL_BETWEEN_CHECKS = 2000;
    private Discussion mDiscussion;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null)
        {
            stopSelf();
        }
        else
        {
            Bundle data = intent.getExtras();
            mDiscussion = (Discussion) data.getSerializable(SERIALIZED_DISCUSSION);
            MessageReaderThread thread = new MessageReaderThread(mDiscussion);
            thread.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }


    public class MessageReaderThread extends Thread
    {
        private Discussion mDiscussion;

        public MessageReaderThread(Discussion discussion)
        {
            this.mDiscussion = discussion;
        }

        @Override
        public void run() {
            while(true)
            {
                try {
                    Thread.sleep(INTERVAL_BETWEEN_CHECKS);

                    MessageList messageList = mDiscussion.getNewMessagesList();
                    Intent returnIntent = new Intent();
                    Bundle intentData = new Bundle();
                    intentData.putSerializable(SERIALIZED_MESSAGES, messageList);
                    returnIntent.putExtras(intentData);
                    returnIntent.setAction(ACTION_READ_MESSAGES);
                    sendBroadcast(returnIntent);
                }
                catch(UnknownHostException ex)
                {
                    //Ignore, probably internet connection problem
                }
                catch(SocketTimeoutException ex)
                {
                    //Ignore, probably internet connection problem
                }
                catch(InterruptedException ex)
                {
                    ex.printStackTrace(System.out);
                    System.out.println("The MessageReaderService was interrupted");
                    this.interrupt();
                    stopSelf();
                    break;
                }catch(DiscussionException dex)
                {
                    //Return discussion exception - The discussion was ended
                    Intent returnIntent = new Intent();
                    Bundle intentData = new Bundle();
                    intentData.putSerializable(SERIALIZED_EXCEPTION,dex);
                    returnIntent.putExtras(intentData);
                    returnIntent.setAction(ACTION_READ_MESSAGES);
                    sendBroadcast(returnIntent);
                    stopSelf();
                    break;
                }
                catch(Exception ex)
                {
                    ex.printStackTrace(System.out);
                    //stopSelf();
                }
            }
        }
    }
}
