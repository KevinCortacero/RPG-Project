package com.example.taptap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Authentification extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentification);
		((Button)findViewById(R.id.boutonConnexion)).setOnClickListener(new GestionnaireAuthentification(this));
	}
}

