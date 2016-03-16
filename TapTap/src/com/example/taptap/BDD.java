package com.example.taptap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDD extends SQLiteOpenHelper{

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "taptap.db";
	
	private static final String DATABASE_CREATE = "CREATE TABLE Joueurs(_id INTEGER AUTOINCREMENT,pseudo TEXT,mdp TEXT,score INTEGER);";
	
	public BDD(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS Joueurs ;");
		this.onCreate(db);
	}
}
