package com.example.taptap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Authentification extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentification);
		((Button)findViewById(R.id.button1)).setOnClickListener(new ConnexionListener(this));;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.authentification, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class ConnexionListener implements OnClickListener {

		private Activity activite;
		
		public ConnexionListener(Activity activite) {
			this.activite = activite;
		}
		
		@Override
		public void onClick(View arg0) {
			EditText et1 = ((EditText)this.activite.findViewById(R.id.editText1));
			String pseudo = et1.getText().toString();
			EditText et2 = ((EditText)this.activite.findViewById(R.id.editText2));
			String mdp = et2.getText().toString();
			
			if (pseudo.equals("") && mdp.equals("")){
				Toast.makeText(Authentification.this,"pseudo et mot de passe manquants", Toast.LENGTH_SHORT).show();
			}
			else if (pseudo.equals("")){
				Toast.makeText(Authentification.this,"pseudo manquant", Toast.LENGTH_SHORT).show();
			}else if (mdp.equals("")){
				Toast.makeText(Authentification.this,"mot de passe manquant", Toast.LENGTH_SHORT).show();
			}
			else{
				if (!(pseudo.equals("Twarz") || pseudo.equals("Koreuc"))  || !mdp.equals("123")){
					Toast.makeText(Authentification.this,"pseudo ou mot de passe incorrect", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(Authentification.this,"Connexion", Toast.LENGTH_LONG).show();
					Intent i = new Intent(Authentification.this, Deuxieme_activite.class);
					i.putExtra("Pseudo", pseudo);
					Authentification.this.startActivity(i);
					Authentification.this.finish();
				}
			}
			
		}
	}
}
