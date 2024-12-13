package com.jah.sonido_animales_video;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.MaterialToolbar;

public class ActivityJugar extends AppCompatActivity {

    MaterialToolbar mtbMenu;
    VideoView vvVideo;
    ImageView img1, img2, img3;
    MediaController mediaController;
    String url;
    Drawable[] arrimagenes;
    ImageView[] arrImageViews;
    SoundPool soundPool;
    int[] sonidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jugar);
        initComponents();
        setSupportActionBar(mtbMenu);
        img1.setOnClickListener(view -> {
            reproducirSonido(sonidos[0]);
        });
        img2.setOnClickListener(view -> {
            reproducirSonido(sonidos[1]);
        });
        img3.setOnClickListener(view -> {
            reproducirSonido(sonidos[2]);
        });
    }

    private void reproducirSonido(int sonido) {
        soundPool.play(sonido, 1, 1, 0, 0, 1);
    }

    private void asignarElementos(int tipo) {
        if(tipo == 1){
            url = "android.resource://" + getPackageName() + "/" + R.raw.domesticos;
            img1.setImageResource(R.drawable.cerdo);
            img2.setImageResource(R.drawable.gato);
            img3.setImageResource(R.drawable.perro);
            sonidos = new int[]{
                    soundPool.load(this, R.raw.cerdo, 1),
                    soundPool.load(this, R.raw.gato, 1),
                    soundPool.load(this, R.raw.perro, 1)};
        }else if(tipo == 2){
            url = "android.resource://" + getPackageName() + "/" + R.raw.salvaje;
            img1.setImageResource(R.drawable.lobo);
            img2.setImageResource(R.drawable.oso);
            img3.setImageResource(R.drawable.zorro);
            sonidos = new int[]{
                    soundPool.load(this, R.raw.leon, 1),
                    soundPool.load(this, R.raw.elefante, 1),
                    soundPool.load(this, R.raw.hipopotamo, 1)};
        }else {
            url = "android.resource://" + getPackageName() + "/" + R.raw.cielo;
            img1.setImageResource(R.drawable.halcon);
            img2.setImageResource(R.drawable.aguila);
            img3.setImageResource(R.drawable.loro);
            sonidos = new int[]{
                    soundPool.load(this, R.raw.buho, 1),
                    soundPool.load(this, R.raw.aguila, 1),
                    soundPool.load(this, R.raw.rinoceronte, 1)};
        }
        arrimagenes = new Drawable[]{img1.getDrawable(), img2.getDrawable(), img3.getDrawable()};
        for(int i = 0;i < 3;i++){
            llamarAGlide(arrimagenes[i], arrImageViews[i]);
        }
        vvVideo.setVideoURI(Uri.parse(url));
        vvVideo.setMediaController(mediaController);
        vvVideo.start();
    }

    private void llamarAGlide(Drawable arrimagenes, ImageView arrImageView) {
        Glide.with(this)
                .load(arrimagenes)
                .into(arrImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_domesticos){
            asignarElementos(1);
        }else if(item.getItemId() == R.id.item_salvajes){
            asignarElementos(2);
        }else if(item.getItemId() == R.id.item_aves){
            asignarElementos(3);
        }else if(item.getItemId() == R.id.item_salir){
            createDialogSalir();
        }
        return false;
    }

    private void createDialogSalir() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setNegativeButton(R.string.dialog_negative, null)
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> finishAffinity())
                .create()
                .show();
    }

    private void initComponents() {
        mtbMenu = findViewById(R.id.mtbMenu);
        vvVideo = findViewById(R.id.vvVideo);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        arrImageViews = new ImageView[]{img1, img2, img3};
        mediaController = new MediaController(getApplicationContext());
        mediaController.setAnchorView(vvVideo);
        soundPool = new SoundPool.Builder().setMaxStreams(2).build();
        asignarElementos(1);
    }

}