package com.hiory.samnote;

import java.util.List;

import com.hiory.samnote.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AtyNotelist extends Activity implements OnItemClickListener{
	private NotelistAdapter adapter;
	private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_notelist);
        lv=(ListView) findViewById(R.id.note_list);
       
        init();
//        DBNotes db1=new DBNotes(this);
//        db1.DeleteAll();
//        System.out.println("删除完成");
        
        DBNotes db=new DBNotes(this);
       List<Notes> notes= db.getNotes();
       for(int i=0;i<notes.size();i++){
    	   Notes note=notes.get(i);
    	   System.out.println(note.getTitle()+note.getContent()+note.getDate()+note.getId());
       }
       adapter=new NotelistAdapter(AtyNotelist.this,notes);
       lv.setAdapter(adapter);
       adapter.notifyDataSetChanged();
       lv.setOnItemClickListener(this);
      
    }
	
	private void init() {
		
		
		new TitleBuilder(this)
		.setTitleText("记事本")
		.setRightText("添加")
		.setRightOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				DBNotes db=new DBNotes(AtyNotelist.this);
				int x=db.getMaxId();
				if (x>=0) {
					Intent intent =new Intent(AtyNotelist.this,AtyDetail.class);
					intent.putExtra("tag", 0);
					intent.putExtra("noteid", x+1);
					startActivity(intent);
					AtyNotelist.this.finish();
				}
			}
		});
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Notes note=adapter.getItem(position);
		Intent intent=new Intent(AtyNotelist.this,AtyDetail.class);
		intent.putExtra("tag", 1);
		intent.putExtra("id", note.getId());
		intent.putExtra("title", note.getTitle());
		intent.putExtra("date", note.getDate());
		intent.putExtra("content", note.getContent());
		startActivity(intent);
		AtyNotelist.this.finish();
		
	}
}
