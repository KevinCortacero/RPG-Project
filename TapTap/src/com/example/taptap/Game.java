package com.example.taptap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {
	
	int compteur = 0;

	public enum Etat{
		SLEEP,
		AWAKE
	}

	public Etat etat;
	public Dessin dessin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		this.etat = Etat.AWAKE;
		this.dessin = (Dessin)findViewById(R.id.Dessin2);
		this.dessin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TextView score = (TextView)Game.this.findViewById(R.id.textView3);

				if (Game.this.etat == Etat.AWAKE){
					score.setText(""+(Integer.parseInt(score.getText().toString())+1));
					Game.this.compteur++;
					double chance = Math.random();
					if (chance <= 0.05){
						Game.this.dormir();
					}
				}
				else{
					score.setText(""+(Integer.parseInt(score.getText().toString())-1));
					Game.this.compteur--;
					score.invalidate();
				}
			}
		});

		Button boutonRetour = (Button) findViewById(R.id.buttonRetour);
		boutonRetour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Game.this.setResult(Game.this.compteur);
				Toast.makeText(Game.this, "Score : " + compteur, Toast.LENGTH_LONG).show();
				Game.this.finish();
			}
		});
	}
	
	private void dormir(){
		this.etat = Etat.SLEEP;
		this.dessin.setEtat(this.etat);
		Toast.makeText(Game.this, "ZZZZzzzzz", Toast.LENGTH_SHORT).show();
		Game.this.dessin.invalidate();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.sauvegarder) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
