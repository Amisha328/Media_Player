package com.amisha.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button play,pause,stop,reset;
    TextView t1,t2;
    MediaPlayer mp;
    SeekBar seekBar;
    double finalTime=0;
    double currentTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.textView2);
        t2=findViewById(R.id.textView3);
        play=findViewById(R.id.button);
        pause=findViewById(R.id.button2);
        stop=findViewById(R.id.button3);
        reset=findViewById(R.id.button4);
        seekBar=findViewById(R.id.seekBar);
        mp=MediaPlayer.create(this,R.raw.music);
        seekBar.setClickable(false);
        finalTime=mp.getDuration();
        t2.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes((long)finalTime),TimeUnit.MILLISECONDS.toSeconds((long)finalTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)finalTime))));

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                currentTime=mp.getCurrentPosition();
                t1.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes((long)currentTime),TimeUnit.MILLISECONDS.toSeconds((long)currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)currentTime))));
                seekBar.setProgress((int) currentTime);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                currentTime=mp.getCurrentPosition();
                seekBar.setProgress((int) currentTime);
                t1.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes((long)currentTime),TimeUnit.MILLISECONDS.toSeconds((long)currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)currentTime))));

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.reset();
                currentTime=mp.getCurrentPosition();
                seekBar.setProgress((int) currentTime);
                t1.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes((long)currentTime),TimeUnit.MILLISECONDS.toSeconds((long)currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)currentTime))));
                mp=MediaPlayer.create(MainActivity.this,R.raw.music);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                currentTime=mp.getCurrentPosition();
                seekBar.setProgress((int) currentTime);
                t1.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes((long)currentTime),TimeUnit.MILLISECONDS.toSeconds((long)currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)currentTime))));
                mp=MediaPlayer.create(MainActivity.this,R.raw.music);
            }
        });
        currentTime=mp.getCurrentPosition();
        t1.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes((long)currentTime),TimeUnit.MILLISECONDS.toSeconds((long)currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)currentTime))));

    }

}
