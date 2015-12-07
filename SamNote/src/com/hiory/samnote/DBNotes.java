package com.hiory.samnote;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.CommonDataKinds.Note;

public class DBNotes extends SQLiteOpenHelper{

	public DBNotes(Context context) {
		super(context, "notes", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table notes(" +
				"id INTEGER PRIMARY KEY," +
				"title varchar(100) NULL," +
				"date varchar(45) NULL," +
				"content text NULL)");
		db.execSQL("CREATE UNIQUE INDEX unique_index_id ON notes (id)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

	public void addNote(Notes note){
		SQLiteDatabase db=getWritableDatabase();
		String insql="replace into notes(id,title,date,content) values("+note.getId()+",'"+
				note.getTitle()+"','"+note.getDate()+"','"+
				note.getContent()+"')";
		db.execSQL(insql);
		
	}
	
	public List<Notes> getNotes(){
		List<Notes> notes=new ArrayList<Notes>();
		SQLiteDatabase db=getWritableDatabase();
		Cursor cursor=db.query("notes", new String[]{"id,title","date","content"}, null, null, null, null, "date");
		Notes nt;
		while (cursor.moveToNext()) {
			nt=new Notes();
			nt.setId(cursor.getInt(cursor.getColumnIndex("id")));
			nt.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			nt.setDate(cursor.getString(cursor.getColumnIndex("date")));
			nt.setContent(cursor.getString(cursor.getColumnIndex("content")));
			notes.add(nt);
		}
		return notes;
	}
	
	public int getMaxId(){
		int x=0;
		SQLiteDatabase db=getWritableDatabase();
		Cursor cursor=db.query("notes", new String[]{"id"}, null, null, null, null, "id");
		if(cursor.moveToLast()){
			 x=cursor.getInt(cursor.getColumnIndex("id"));
		}
		return x;
	}
	
	public void DeleteAll(){
		SQLiteDatabase db=getWritableDatabase();
		db.execSQL("delete from notes where id >=0");
	}
	
	
}