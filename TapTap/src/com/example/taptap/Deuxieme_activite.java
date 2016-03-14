package com.example.taptap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
		
		ListView liste = (ListView) findViewById(R.id.listView1);
		String[] couleurs = { "Bleu", "Rouge", "Vert", "Jaune"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, couleurs);
		liste.setAdapter(adapter);
		Deuxieme_activite.color = Color.RED;
		Deuxieme_activite.form = Form.CARRE;
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.authentification, menu);
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
	}*/
	
}
