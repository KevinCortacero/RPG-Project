package com.example.taptap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {

	public enum Etat{
		SLEEP,
		AWAKE
	}
	
	public Etat etat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		this.etat = Etat.AWAKE;
		Dessin dessin = (Dessin)findViewById(R.id.Dessin2);
		dessin.setEtat(this.etat);
		dessin.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {		
				TextView score = (TextView)Game.this.findViewById(R.id.textView3);
				if (Game.this.etat == Etat.AWAKE){
					score.setText(""+(Integer.parseInt(score.getText().toString())+1));
					double chance = Math.random();
					if (chance <= 0.08){
						Game.this.etat = Etat.SLEEP;
						Toast.makeText(Game.this, "ZZZZzzzzz", Toast.LENGTH_SHORT).show();
						v.invalidate();
					}
				}
				else if (Game.this.etat == Etat.SLEEP){
					score.setText(""+(Integer.parseInt(score.getText().toString())-1));
				}
				score.invalidate();
			}
		});
	}
}
