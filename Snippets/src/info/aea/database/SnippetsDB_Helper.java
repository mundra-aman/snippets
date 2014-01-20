package info.aea.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class SnippetsDB_Helper extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "snippets";
	private static final int DATABASE_VERSION = 1;
	
	public SnippetsDB_Helper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		// you can use an alternate constructor to specify a database location 
		// (such as a folder on the sd card)
		// you must ensure that this folder is available and you have permission
		// to write to it
		//super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
		
	}

	
	
	//---------------------CRUD Operations---------------------//
	
	
		// get all programs query
		public List<SourceCode_Table> getall(String lang) {
			List<SourceCode_Table> codelist = new ArrayList<SourceCode_Table>();
			String language = lang;
			String selectQuery = "SELECT * FROM SourceCodes where codelang='" + language + "'" ;    // Select All Query
			SQLiteDatabase db = getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to Vector
			if (cursor.moveToFirst()) {
				do {
					SourceCode_Table a = new SourceCode_Table();

					a.setCodeID(cursor.getString(0));
					a.setCodeLang(cursor.getString(1));
					a.setCodeTitle(cursor.getString(2));
					a.setCodeSource(cursor.getString(3));
					a.setCodeOutput(cursor.getString(4));

					codelist.add(a);
				} while (cursor.moveToNext());
			}
			return codelist;
		}
		

		
		//get a single row on condition - language
			public String getrow_lang(String id, String lang) {
				String str="";
				String selectQuery = "SELECT  * FROM SourceCodes where codeid='" +id+ "' and codelang='" +lang+ "'" ;    // Select All Query
				SQLiteDatabase db = getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				if (cursor.moveToFirst()) {
					str = cursor.getString(3);
					System.out.println("str");
				}
				
				return str;
			}
			
			
			
			//get a single row on condition - for algorithms
			public String getrow_algo(String id) {
				String str="";
				String selectQuery = "SELECT  * FROM Algorithms where algoid='" +id+ "'" ;    // Select All Query
				SQLiteDatabase db = getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);
				
				if (cursor.moveToFirst()) {
					str = cursor.getString(3);
					System.out.println("str");
				}
				
				return str;
			}

			
			
			// get all Algorithms query
			public List<Algorithms_Table> getallAlgos() {
				List<Algorithms_Table> Algolist = new ArrayList<Algorithms_Table>();
				String selectQuery = "SELECT * FROM Algorithms";    // Select All Query
				SQLiteDatabase db = getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to Vector
				if (cursor.moveToFirst()) {
					do {
						Algorithms_Table a = new Algorithms_Table();

						a.setAlgoID(cursor.getString(0));
						a.setAlgoTitle(cursor.getString(1));
						//a.setAlgoTitle(cursor.getString(2));
						
						Algolist.add(a);
					} while (cursor.moveToNext());
				}
				return Algolist;
			}
			
			

			// query to save code with a savebit
			public void  update_save2(String codeid, String bit){
				System.out.println("executing update command.......");
				// Define the updated row content.
				ContentValues updatedValues = new ContentValues();
				// Assign values for each row.
				String savebit = bit;
				updatedValues.put("SaveBit", savebit);
				String where="CodeID = ?";
				SQLiteDatabase db = getWritableDatabase();		
				System.out.println("codeid arg value ......."+codeid);
				db.update("SourceCodes", updatedValues, where, new String[]{codeid});
				System.out.print("confirm sucessfull+++++++++++++++");				
			}
			
			
			public void insertcode(String Algoid, String AlgoLang, String AlgoSource, String AlgoTitle, String bit){
				ContentValues newValues = new ContentValues();
				// Assign values for each row.
				System.out.println(AlgoSource + AlgoTitle + AlgoLang + Algoid);
				newValues.put("codeid", Algoid);
				newValues.put("codelang",AlgoLang);
				newValues.put("codesource", AlgoSource);
				newValues.put("codetitle", AlgoTitle);
				newValues.put("savebit", bit);

				// Insert the row into your table
				SQLiteDatabase db = getWritableDatabase();
				db.insert("SourceCodes", null, newValues);
				System.out.println(AlgoSource + AlgoTitle + AlgoLang + Algoid);
			}
			
			
			
			// get all_saved code query
			public List<SourceCode_Table> get_saved() {
				List<SourceCode_Table> codelist = new ArrayList<SourceCode_Table>();
				String selectQuery = "SELECT  * FROM SourceCodes where savebit='1'" ;    // Select All Query
				SQLiteDatabase db = getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to Vector
				if (cursor.moveToFirst()) {
					do {
						SourceCode_Table a = new SourceCode_Table();

						a.setCodeID(cursor.getString(0));
						a.setCodeLang(cursor.getString(1));
						a.setCodeTitle(cursor.getString(2));
						a.setCodeSource(cursor.getString(3));
						a.setCodeOutput(cursor.getString(4));

						codelist.add(a);
					} while (cursor.moveToNext());
				}
				return codelist;
			}	
}
