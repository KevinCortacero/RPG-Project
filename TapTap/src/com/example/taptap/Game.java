package com.example.taptap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity {
	
	
	int compteur = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		Dessin dessin = (Dessin)findViewById(R.id.Dessin2);
		dessin.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {		
				TextView score = (TextView)Game.this.findViewById(R.id.textView3);
				score.setText(""+(Integer.parseInt(score.getText().toString())+1));
				Game.this.compteur++;
				score.invalidate();
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
}
