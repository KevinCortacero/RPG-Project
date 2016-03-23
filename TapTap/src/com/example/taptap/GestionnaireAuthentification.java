package com.example.taptap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class GestionnaireAuthentification implements OnClickListener {

	private Activity activite;

	public GestionnaireAuthentification(Activity activite) {
		this.activite = activite;
	}

	@Override
	public void onClick(View arg0) {
		String pseudo = this.getValeurChamp(R.id.pseudo);
		String mdp = this.getValeurChamp(R.id.mdp);
		this.tentativeConnexion(pseudo, mdp);
	}
	
	private String getValeurChamp(int id) {
		return ((EditText)this.activite.findViewById(id)).getText().toString();
	}
	
	public void afficher(String message){
		Toast.makeText(this.activite,message, Toast.LENGTH_SHORT).show();
	}
	
	private void tentativeConnexion(String pseudo, String mdp){
		if (!pseudo.equals("") && !mdp.equals("")){
			this.verifierCorrespondance(pseudo, mdp);
		}
		else if (pseudo.equals("") && mdp.equals("")){
			this.afficher("Pseudo et mot de passe manquants !");
		}
		else if (pseudo.equals("")){
			this.afficher("Pseudo manquant !");
		}
		else{
			this.afficher("Mot de passe manquant !");
		}
	}

	private void verifierCorrespondance(String pseudo, String mdp) {
		BDD bdd = new BDD(this.activite);
		SQLiteDatabase dataR = bdd.getReadableDatabase();
		String[] col = {"mdp"};
		String[] select = {};
		Cursor cursor = dataR.query("Joueurs", col, "pseudo=\"" + pseudo + "\"",select,null,null,null);	
		if (cursor.moveToFirst()){
			if (cursor.getString(cursor.getColumnIndexOrThrow("mdp")).equals(mdp)){
				dataR.close();
				this.connexion(pseudo);
			}
			else{
				this.afficher("Mot de passe incorrect !");
			}
		}
		else{
			this.afficher("Pseudo non enregistré !");
		}
		dataR.close();
		bdd.close();
	}

	private void connexion(String pseudo) {
		this.afficher("Connexion...");
		Intent i = new Intent(this.activite, Deuxieme_activite.class);
		i.putExtra("Pseudo", pseudo);
		this.activite.startActivity(i);
		this.activite.finish();
	}
}
