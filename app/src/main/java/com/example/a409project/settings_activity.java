package com.example.a409project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class settings_activity extends AppCompatActivity {

    TextView navDashboard, navHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Settings), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Allow call us button work
        Button btnCall = findViewById(R.id.btn_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
            }
        });

        //Allow Website button to work
        Button btnWebsite = findViewById(R.id.btn_website);
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.example.com"; //Test URL you can put anything you want here
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        //Allow Website button to work
        Button btnPrank = findViewById(R.id.btn_prank);
        VideoView videoView = findViewById(R.id.videoView);


        btnPrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPrank.setVisibility(View.GONE); // Hide button
                videoView.setVisibility(View.VISIBLE);

                String path = "android.resource://" + getPackageName() + "/" + R.raw.rickroll;
                Uri uri = Uri.parse(path);
                videoView.setVideoURI(uri);

                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(settings_activity.this, settings_activity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                videoView.start();
            }
        });


        //NavBar handling switch between activity.
        navDashboard = findViewById(R.id.nav_dashboard);

        navDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        navHome = findViewById(R.id.nav_home);

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings_activity.this, activity_home.class);
                startActivity(intent);
            }
        });
    }
}