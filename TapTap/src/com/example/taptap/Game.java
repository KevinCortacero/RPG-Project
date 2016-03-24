package com.example.taptap;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
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
	private int compteur;
	private int compteurPartieActuelle = 0;
	private String pseudo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		this.etat = Etat.AWAKE;
		this.compteur = this.getIntent().getExtras().getInt("Score");
		this.score = (TextView)Game.this.findViewById(R.id.textView2);
		this.score.setText("Score : "+this.compteur);
		this.dessin = (Dessin)findViewById(R.id.Dessin2);
		this.boutonReveil = (Button) findViewById(R.id.buttonReveil);
		this.pseudo = "Twarz";
		this.pseudo = this.getIntent().getExtras().getString("Pseudo");
		((TextView)this.findViewById(R.id.textView1)).setText(this.pseudo);
		this.boutonReveil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Game.this.reveiller();
			}
		});
		this.dessin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (Game.this.etat == Etat.AWAKE){
					Game.this.compteur ++;
					Game.this.compteurPartieActuelle ++;

					double chance = Math.random();
					if (chance <= 0.05){
						Game.this.dormir();
					}
				}
				else{
					Game.this.compteur --;
					Game.this.compteurPartieActuelle --;
					Game.this.score.invalidate();
				}
				Game.this.score.setText("Score : "+Game.this.compteur);
			}
		});

		String select[] = {"pseudo", "score"};
		BDD bdd = new BDD(this);
		SQLiteDatabase dataR = bdd.getReadableDatabase();
		Cursor curseur = dataR.query("Joueurs", select, null, null, null, null, "score desc");
		String list[] = new String[3];
		int compteur = 0;
		if (curseur.moveToFirst()){
			do{
				list[compteur] = curseur.getString(curseur.getColumnIndexOrThrow("pseudo")) 
						+ "    " 
						+ curseur.getString(curseur.getColumnIndexOrThrow("score"));
				compteur ++;
			}while(curseur.moveToNext() && compteur < 3);
		}
		dataR.close();
		bdd.close();
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
			this.compteur -= 10;
			this.compteurPartieActuelle -= 10;
			this.score.setText("Score : "+this.compteur);
		}
	}

	private void sauvegarder(){
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
		if (id == R.id.sauvegarder){
			this.sauvegarder();
			return true;
		}
		if (id == R.id.changer){
			this.sauvegarder();
			this.setResult(Game.this.compteurPartieActuelle);
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
