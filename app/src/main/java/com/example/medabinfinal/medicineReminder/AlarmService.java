package com.example.medabinfinal.medicineReminder;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.medabinfinal.R;
import com.example.medabinfinal.medicineReminder.Database.AlarmDatabase;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.medabinfinal.medicineReminder.App.CHANNEL_1_ID;


public class AlarmService extends Service {


    private NotificationManagerCompat notificationManager;
    private Integer[] alarmHour1,alarmHour2,alarmHour3;
    private Integer[] alarmMinute1,alarmMinute2,alarmMinute3;
    List<Integer> alarm_hour1,alarm_minute1,alarm_hour2,alarm_minute2,alarm_hour3,alarm_minute3;
    List<String> alarmActive;
    public static Ringtone ringtone;
    private Timer timer = new Timer();
    AlarmDatabase db;
    int size;
    public static int index;
    alarmReceiveActivity alarmReceiveActivity;


    private static final String CHANNEL_ID = "My Notification";




    public AlarmService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //alarmHour = intent.getIntExtra("alarmHour",0);
        //alarmMinute = intent.getIntExtra("alarmMinute",0);

        db = new AlarmDatabase(this);

        notificationManager = NotificationManagerCompat.from(this);
        size = db.getTime1Hour().size();
        final int[] alarmHour1 = new int[size];
        final int[] alarmMinute1 = new int[size];

        final int[] alarmHour2 = new int[size];
        final int[] alarmMinute2 = new int[size];

        final int[] alarmHour3 = new int[size];
        final int[] alarmMinute3 = new int[size];
        final boolean[] isAlarm = new boolean[size];

        alarm_hour1 = db.getTime1Hour();
        alarm_minute1 = db.getTime1Minutes();
        alarm_hour2 = db.getTime2Hour();
        alarm_minute2= db.getTime2Minute();
        alarm_hour3 = db.getTime3Hour();
        alarm_minute3 = db.getTime3Minute();
        alarmActive = db.getAlarmIsOn();

        for(int i=0;i<size;i++)
        {
            alarmHour1[i]=alarm_hour1.get(i);
            alarmMinute1[i]=alarm_minute1.get(i);
            alarmHour2[i]=alarm_hour2.get(i);
            alarmMinute2[i]=alarm_minute2.get(i);
            alarmHour3[i]=alarm_hour3.get(i);
            alarmMinute3[i]=alarm_minute3.get(i);
            if(alarmActive.get(i).matches("Yes"))
            {
                isAlarm[i] = true;
            }
            else
            {
                isAlarm[i] = false;
            }
        }





        ringtone = RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                System.out.println("Time now :"+ Calendar.getInstance().getTime().getMinutes());


                for(int i =0;i<size;i++)
                {

                    System.out.println("database time i ->"+i+"minutes 1->"+alarmMinute1[i]+"minutes 2->"+alarmMinute2[i]+" mintes 3->"+alarmMinute3[i]+" is alarm "+isAlarm[i]);

                    if((Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == alarmHour1[i] &&
                            Calendar.getInstance().getTime().getMinutes() == alarmMinute1[i] &&isAlarm[i])||
                            (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == alarmHour2[i] &&
                                    Calendar.getInstance().getTime().getMinutes() == alarmMinute2[i] && isAlarm[i])||
                            (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == alarmHour3[i] &&
                                    Calendar.getInstance().getTime().getMinutes() == alarmMinute3[i]) && isAlarm[i]){


                        ringtone.play();
                        sendOnChannel1(db.getOneMedicneName(i+1),i+1);
//                        alarmReceiveActivity.recieveData(i+1);




                    }
                    else {
                        ringtone.stop();
                        continue;
                    }
                }



            }
        },0,5000);


        return super.onStartCommand(intent, flags, startId);
    }


    public void stopForegroundservice()
    {
        stopForeground(true);
        stopSelf();
    }

    public void onDestroy(){
        ringtone.stop();
        timer.cancel();


        super.onDestroy();
    }

    public void sendOnChannel1(String alarmMessage,int id)
    {

        System.out.println("id"+id);

        String idTest = String.valueOf(id);


        System.out.println("it test "+idTest);

//        String titleText = title.getText().toString();
//        String messageText = message.getText().toString();

        Intent intent = new Intent(this, alarmReceiveActivity.class);
        intent.putExtra("medicineName",alarmMessage);
        intent.putExtra("Id",id);
       PendingIntent contentIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

//       Intent brodcastIntent = new Intent(this,AlarmService.class);
//       brodcastIntent.putExtra("medicineName",alarmMessage);
//       brodcastIntent.putExtra("Id",idCheck);

//        PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,brodcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.heart_beat)
                .setContentTitle("Time for medicine")
                .setContentText(alarmMessage)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setColor(Color.RED)
                .build();
        notificationManager.notify(1,notification);
    }

}
