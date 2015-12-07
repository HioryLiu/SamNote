package com.hiory.samnote;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AtyDetail extends Activity {

	private EditText titleView;
	private EditText contentView;
	private  Notes note=new Notes();
	int id=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editor);
		titleView=(EditText) findViewById(R.id.title);
		contentView=(EditText) findViewById(R.id.content);
		if (getIntent().getIntExtra("tag", 0)==0) {
			id=getIntent().getIntExtra("noteid", 0);
		}else{
			id=getIntent().getIntExtra("id", 0);
			titleView.setText(getIntent().getStringExtra("title"));
			contentView.setText(getIntent().getStringExtra("content"));
		}
		System.out.println(id);
		System.out.println(id);
		init();
		
	}
	private void init() {

		new TitleBuilder(this)
		.setTitleText("NoteBook")
		.setRightText("Save")
		.setRightOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String title=titleView.getText().toString();
				String content=contentView.getText().toString();
				if(title.equals("") || content.equals("")){
					Toast.makeText(AtyDetail.this, "Title or content is Enpty", Toast.LENGTH_SHORT).show();
				}else {
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date=df.format(new Date());
					note.setId(id);
					note.setTitle(title);
					note.setDate(date);
					note.setContent(content);
					DBNotes db=new DBNotes(AtyDetail.this);
					db.addNote(note);
					System.out.println("±£´æ³É¹¦LL");
					Toast.makeText(AtyDetail.this, "Save Successfully", Toast.LENGTH_SHORT).show();
					AtyDetail.this.finish();
					startActivity(new Intent(AtyDetail.this,AtyNotelist.class));
				}
				
			}
		}).setLeftText("Return").setLeftOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AtyDetail.this.finish();
				startActivity(new Intent(AtyDetail.this,AtyNotelist.class));
			}
		});
	}
}
