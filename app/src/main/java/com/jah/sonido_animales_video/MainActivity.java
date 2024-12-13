package com.jah.sonido_animales_video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnJugar).setOnClickListener(view -> jugar());
        mediaPlayer = MediaPlayer.create(this, R.raw.fondo);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    private void jugar() {
        Intent play = new Intent(this, ActivityJugar.class);
        mediaPlayer.stop();
        finish();
        startActivity(play);
    }

}