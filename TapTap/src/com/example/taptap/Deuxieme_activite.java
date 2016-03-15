package com.example.taptap;

import android.app.Activity;
import android.content.Intent;
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

public class Deuxieme_activite extends Activity {

	public static int color;
	public static Form form;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_deuxieme_activite);
		if (this.getIntent().hasExtra("Pseudo"))
			((TextView)this.findViewById(R.id.textView1)).setText(this.getIntent().getExtras().getString("Pseudo"));
		else 
			((TextView)this.findViewById(R.id.textView1)).setText("Pseudo");

		RadioGroup radioGroup = (RadioGroup)this.findViewById(R.id.radioGroup1);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				System.out.println(checkedId);
				RadioButton bouton = (RadioButton)Deuxieme_activite.this.findViewById(checkedId);
				String textBouton = (String)bouton.getText();
				if ( textBouton.equals("Carré")){
					Deuxieme_activite.form = Form.CARRE;
				}else{ 
					Deuxieme_activite.form = Form.ROND;
				}
				View v = Deuxieme_activite.this.findViewById(R.id.dessin1);
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
					Deuxieme_activite.color = Color.BLUE;
				}
				else if (couleur.equals("Rouge")){
					Deuxieme_activite.color = Color.RED;
				}
				else if (couleur.equals("Vert")){
					Deuxieme_activite.color = Color.GREEN;
				}
				else if (couleur.equals("Jaune")){
					Deuxieme_activite.color = Color.YELLOW;
				}
				View v = Deuxieme_activite.this.findViewById(R.id.dessin1);
				v.invalidate();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Deuxieme_activite.color = Color.BLUE;
			}
		});
		
		Button valider = (Button) findViewById(R.id.button1);
		valider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Deuxieme_activite.this, Game.class);
				Deuxieme_activite.this.startActivity(i);
				Deuxieme_activite.this.finish();
			}
		});
	}
}
