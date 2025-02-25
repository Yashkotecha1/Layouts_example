package com.example.layouts_example;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.Random;

public class LinearActivity extends AppCompatActivity {

    Button btn_login;
    TextView txt_username;

    EditText edt_username;
    String otp = "";
    private final String channelId = "i.apps.notifications"; // Unique channel ID for notifications
    private final String description = "Test notification";  // Description for the notification channel
    private final int notificationId = 1234; // Unique identifier for the notification

    private static final String KEY_TEXT_REPLY = "key_text_reply";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_linear);
        FindViewByID();
        Body();

    }

    private void Body()
    {
        createNotificationChannel();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LinearActivity.this, OtpLoginActivity.class);
                Random random = new Random();

                // Generate a 4-digit OTP

                for (int i = 0; i < 4; i++) {
                    otp += random.nextInt(10); // Generate a random number between 0 and 9
                }

                // Output the generated OTP
                System.out.println("Your OTP is: " + otp);
                intent.putExtra("otp",otp);



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ActivityCompat.checkSelfPermission(LinearActivity.this, Manifest.permission.POST_NOTIFICATIONS)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(
                                LinearActivity.this,
                                new String[]{Manifest.permission.POST_NOTIFICATIONS},
                                101
                        );
                        return;
                    }
                }
                sendNotification(); //

                startActivity(intent);
            }
        });

    }

    private void FindViewByID()
    {
        btn_login = findViewById(R.id.btn_login);
        txt_username = findViewById(R.id.txt_username);
        edt_username = findViewById(R.id.edt_username);
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(
                channelId,
                description,
                NotificationManager.IMPORTANCE_HIGH
        );
        notificationChannel.enableLights(true); // Turn on notification light
        notificationChannel.setLightColor(Color.GREEN);
        notificationChannel.enableVibration(true); // Allow vibration for notifications

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * Build and send a notification with a custom layout and action.
     */
    @SuppressLint("MissingPermission")
    private void sendNotification() {
        // Intent that triggers when the notification is tapped
        Intent intent = new Intent(LinearActivity.this, ProductActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Custom layout for the notification content
        @SuppressLint("RemoteViewLayout")
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.activity_after_notification);

        String username = edt_username.getText().toString();
        System.out.println("---------- msg is -------------"+username);
        contentView.setTextViewText(R.id.textView, "Otp : "+ otp); // Set dynamic text


        otp = " ";

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_message_24) // Notification icon
                .setContent(contentView) // Custom notification content
                .setContentTitle("Hello") // Title displayed in the notification
                .setContentText(otp) // Text displayed in the notification
                .setContentIntent(pendingIntent) // Pending intent triggered when tapped
                .setAutoCancel(true) // Dismiss notification when tapped
                .setPriority(NotificationCompat.PRIORITY_HIGH); // Notification priority for better visibility

        // Display the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, builder.build());
    }


}