package com.example.taptap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BDD extends SQLiteOpenHelper{

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "TapTap.db";
	
	public final String DATABASE_CREATE = "CREATE TABLE Joueurs (pseudo TEXT PRIMARY KEY,mdp TEXT,score INTEGER);";
	public final String DATABASE_DELETE = "DROP TABLE IF EXISTS Joueurs ;";
	
	private Context context;
	
	public BDD(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(DATABASE_CREATE);
		Toast.makeText(this.context, "Initialisation de la base de donn�e", Toast.LENGTH_LONG).show();
		db.insert("Joueurs", null, this.creerJoueur("Twarz", "123"));
		db.insert("Joueurs", null, this.creerJoueur("Koreuc", "abc"));
		db.insert("Joueurs", null, this.creerJoueur("Info", "$info123"));
	}
	private ContentValues creerJoueur(String pseudo, String mdp) {
		ContentValues joueur = new ContentValues();
		joueur.put("pseudo", pseudo);
		joueur.put("mdp", mdp);
		joueur.put("score", 0);
		return joueur;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL(DATABASE_DELETE);
		this.onCreate(db);
	}
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		super.onDowngrade(db, oldVersion, newVersion);
	}
}
