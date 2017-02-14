package com.sonikro.deeptalkme.services;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.sonikro.deeptalkme.DAO.DiscussionException;
import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.layout.ChatActivity;
import com.sonikro.deeptalkme.activity.layout.DiscussionMessageView;
import com.sonikro.deeptalkme.activity.layout.LoginActivity;
import com.sonikro.deeptalkme.framework.DeepTalkMe;
import com.sonikro.deeptalkme.model.chat.MessageList;
import com.sonikro.deeptalkme.services.MessageReaderService;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class MessageReceiver extends BroadcastReceiver{
    private DiscussionMessageView mDiscussionMessageView;
    private final static int NOTIFICATION_ID = 123;

    public MessageReceiver(DiscussionMessageView messageView)
    {
        this.mDiscussionMessageView = messageView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        MessageList newMessages = (MessageList) data.getSerializable(MessageReaderService.SERIALIZED_MESSAGES);
        DiscussionException exception = (DiscussionException) data.getSerializable(MessageReaderService.SERIALIZED_EXCEPTION);

        if(exception != null)
        {
            mDiscussionMessageView.add(exception.getErrorSystemMessage());
            System.out.println("Discussion ended");

        }
        if(newMessages != null && newMessages.size() > 0)
        {
            mDiscussionMessageView.add(newMessages);
            System.out.println("New Messages Received");
            if(!appIsForeground(context))
            {
                dispatchNotification(context, newMessages);
            }
        }

        if(appIsForeground(context) && ( newMessages == null || newMessages.size() < 1))
        {
            mDiscussionMessageView.refresh();
        }
    }

    protected void dispatchNotification(Context context, MessageList messages)
    {
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context);
        notificationBuilder.setSmallIcon(R.drawable.ic_message_18_black);
        notificationBuilder.setContentTitle("DeepTalkMe Discussion");
        notificationBuilder.setContentText("There are new messages!");
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setVibrate(new long[]{1000, 1000});
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder.setSound(alarmSound);

        Intent notificationIntent = new Intent(context, ChatActivity.class);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    protected boolean appIsForeground(Context context)
    {
        ActivityManager.RunningAppProcessInfo currentApp = getForegroundApp(context);
        if(currentApp != null && currentApp.processName.equals(DeepTalkMe.PROCESS_NAME))
        {
            return true;

        }
        return false;
    }

    protected ActivityManager.RunningAppProcessInfo getForegroundApp(Context context) {
        ActivityManager.RunningAppProcessInfo result = null, info=null;
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List <ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        Iterator <ActivityManager.RunningAppProcessInfo> runningAppProcessInfoIterator = runningAppProcesses.iterator();
        while(runningAppProcessInfoIterator.hasNext())
        {
            info = runningAppProcessInfoIterator.next();
            if(info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
            {
                result = info;
                break;
            }
        }
        return result;
    }
}
