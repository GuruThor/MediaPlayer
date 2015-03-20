package com.guruthor.mediaplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	MediaPlayer mp;
	ImageButton bplay, bpause, bstop, bprev, bnext;
	int mspause, clickindex = 0;
	int[] tracks = {  
			R.raw.bailamos,
			R.raw.heartattack,
			R.raw.hero,
			R.raw.somebody,
			R.raw.tonight,
			R.raw.takin,
			R.raw.finallyfound,
			R.raw.dancer,
			R.raw.like,
			R.raw.push,
			R.raw.turn
	};
	public int currentTrack = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bplay = (ImageButton) findViewById(R.id.ibplay);
		bpause = (ImageButton) findViewById(R.id.ibpause);
		bstop = (ImageButton) findViewById(R.id.ibstop);
		bprev = (ImageButton) findViewById(R.id.ibprev);
		bnext = (ImageButton) findViewById(R.id.ibnext);
		
		bplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clickindex++;
				if(clickindex==1){
					mp = MediaPlayer.create(MainActivity.this, tracks[currentTrack]);
					mp.seekTo(mspause);
					mp.start();
				}
				else{
					clickindex = 1;
					mp.stop();
					mp = MediaPlayer.create(MainActivity.this, tracks[currentTrack]);
					mp.seekTo(mspause);
					mp.start();
				}
					
			}
		});

		bpause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mspause = mp.getCurrentPosition();
				mp.pause();
				clickindex = 0;

			}
		});
		bstop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mspause = 0;
				mp.stop();
				clickindex = 0;

			}
		});

		bprev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.stop();
				if(currentTrack!=0){
					currentTrack = (currentTrack - 1) % tracks.length;
				}
				else{
					currentTrack = 0;
				}
				mp = MediaPlayer.create(MainActivity.this, tracks[currentTrack]);
				mp.start();

			}
		});

		bnext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.stop();
				currentTrack = (currentTrack + 1) % tracks.length;
				mp = MediaPlayer.create(MainActivity.this, tracks[currentTrack]);
				mp.start();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
