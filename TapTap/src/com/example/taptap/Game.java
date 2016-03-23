package com.example.taptap;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Game extends Activity {

	public enum Etat{
		SLEEP,
		AWAKE
	}

	private Etat etat;
	private Dessin dessin;
	private Button boutonReveil;
	private TextView score;
	private int compteur = 0;
	private String pseudo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		this.etat = Etat.AWAKE;
		this.dessin = (Dessin)findViewById(R.id.Dessin2);
		this.boutonReveil = (Button) findViewById(R.id.buttonReveil);
		this.score = (TextView)Game.this.findViewById(R.id.textView3);
		this.pseudo = "Twarz";
		if (this.getIntent().hasExtra("Pseudo"))
			this.pseudo = this.getIntent().getExtras().getString("Pseudo");
		else 
			this.pseudo = "Pseudo";
		((TextView)this.findViewById(R.id.textView1)).setText(this.pseudo);
		boutonReveil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Game.this.reveiller();
			}
		});
		this.dessin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (Game.this.etat == Etat.AWAKE){
					Game.this.compteur++;
					
					double chance = Math.random();
					if (chance <= 0.05){
						Game.this.dormir();
					}
				}
				else{
					Game.this.compteur--;
					Game.this.score.invalidate();
				}
				Game.this.score.setText(""+Game.this.compteur);
			}
		});

		String list[] = {"Twarz", "Koreuc", "Info"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		ListView liste = (ListView) findViewById(R.id.listView1);
		liste.setAdapter(adapter);
		
	}
	
	private void dormir(){
		this.etat = Etat.SLEEP;
		this.dessin.setEtat(this.etat);
		Game.this.dessin.invalidate();
	}
	
	private void reveiller(){
		if (this.etat == Etat.SLEEP){
			this.etat = Etat.AWAKE;
			this.dessin.setEtat(this.etat);
			Game.this.dessin.invalidate();
		}
		else{
			this.compteur-=10;
			Game.this.score.setText(""+Game.this.compteur);
		}
	}
	
	private void sauvegarder() {
		BDD bdd = new BDD(this);
		SQLiteDatabase dataW = bdd.getWritableDatabase();
		ContentValues joueur = new ContentValues();
		joueur.put("score", this.compteur);
		dataW.update("Joueurs", joueur, "pseudo=\"" + this.pseudo + "\"", null);
		dataW.close();
		bdd.close();
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
			this.sauvegarder();
			return true;
		}
		if (id == R.id.changer){
			this.sauvegarder();
			this.setResult(Game.this.compteur);
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
