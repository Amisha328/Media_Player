package com.amisha.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
    // Handler allows you to process Runnable objects associated with a thread's MessageQueue .
    // Each Handler instance is associated with a single thread and that thread's message queue.
    // Each time we create a new Handler it is bound to a Looper .
    Handler handler;
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
        //handler is used to schedule the task i.e. to schedule message and runnable to be executed at some point in the future
        handler=new Handler();
        mp=MediaPlayer.create(this,R.raw.music);// static method of MediaPlayer class. It returns the instance of MediaPlayer class
        finalTime=mp.getDuration();
        seekBar.setMax((int)finalTime);
        t2.setText(String.format("%d : %d", TimeUnit.MILLISECONDS.toMinutes((long)finalTime),TimeUnit.MILLISECONDS.toSeconds((long)finalTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)finalTime))));

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                currentTime=mp.getCurrentPosition();
                t1.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes((long)currentTime),TimeUnit.MILLISECONDS.toSeconds((long)currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)currentTime))));
                seekBar.setProgress((int) currentTime);
                handler.postDelayed(updateSong,100);
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
    Runnable updateSong=new Runnable() {
        @Override
        public void run() {
            currentTime=mp.getCurrentPosition();
            t1.setText(String.format("%d:%d",TimeUnit.MILLISECONDS.toMinutes((long)currentTime),TimeUnit.MILLISECONDS.toSeconds((long)currentTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)currentTime))));
            seekBar.setProgress((int)currentTime);
            handler.postDelayed(this,100);
        }
    };

}
