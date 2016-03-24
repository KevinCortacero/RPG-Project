package com.example.taptap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class SelectionApparence extends Activity {

	public static int color;
	public static Form form;
	private String pseudo;
	private int score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_selection_apparence);
		this.pseudo = this.getIntent().getExtras().getString("Pseudo");
		((TextView)this.findViewById(R.id.textView1)).setText(this.pseudo);

		RadioGroup radioGroup = (RadioGroup)this.findViewById(R.id.radioGroup1);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				System.out.println(checkedId);
				RadioButton bouton = (RadioButton)SelectionApparence.this.findViewById(checkedId);
				String textBouton = (String)bouton.getText();
				if ( textBouton.equals("Carré")){
					SelectionApparence.form = Form.CARRE;
				}else{ 
					SelectionApparence.form = Form.ROND;
				}
				View v = SelectionApparence.this.findViewById(R.id.dessin1);
				v.invalidate();
			}
		});

		Spinner liste = (Spinner) findViewById(R.id.spinner1);
		String[] couleurs = { "Bleu", "Rouge", "Vert", "Jaune"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, couleurs);
		liste.setAdapter(adapter);

		liste.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				String couleur = ((CheckedTextView)view).getText().toString();
				if (couleur.equals("Bleu")){
					SelectionApparence.color = Color.BLUE;
				}
				else if (couleur.equals("Rouge")){
					SelectionApparence.color = Color.RED;
				}
				else if (couleur.equals("Vert")){
					SelectionApparence.color = Color.GREEN;
				}
				else if (couleur.equals("Jaune")){
					SelectionApparence.color = Color.YELLOW;
				}
				View v = SelectionApparence.this.findViewById(R.id.dessin1);
				v.invalidate();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				SelectionApparence.color = Color.BLUE;
			}
		});
		this.mettreAJourScore();
		this.activerBoutonJouer();
	}

	private void mettreAJourScore() {
		String select[] = {"score"};
		BDD bdd = new BDD(this);
		SQLiteDatabase dataR = bdd.getReadableDatabase();
		Cursor curseur = dataR.query("Joueurs", select, "pseudo=\"" + this.pseudo + "\"", null, null, null, null);
		if (curseur.moveToFirst()){
			this.score = curseur.getInt(curseur.getColumnIndexOrThrow("score"));
			TextView totalScore = (TextView) findViewById(R.id.textView5);
			totalScore.setText("Score total : " + this.score);
		}
		dataR.close();
		bdd.close();
	}
	
	private void activerBoutonJouer(){
		Button valider = (Button) findViewById(R.id.boutonJouer);
		valider.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SelectionApparence.this, Game.class);
				i.putExtra("Pseudo", SelectionApparence.this.pseudo);
				i.putExtra("Score", SelectionApparence.this.score);
				SelectionApparence.this.startActivityForResult(i, 1);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		TextView lastScore = (TextView) findViewById(R.id.textView4);
		lastScore.setText("Dernier score : " + resultCode);
		this.mettreAJourScore();

	}
}
