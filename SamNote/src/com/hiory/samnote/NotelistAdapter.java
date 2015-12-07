package com.hiory.samnote;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NotelistAdapter extends BaseAdapter {
	private Context context=null;
	private List<Notes> noteslist=new ArrayList<Notes>();
	public NotelistAdapter(Context context,List<Notes> notes){
		System.out.println("初始化成功");
		this.context=context;
		this.noteslist=notes;
	}
	@Override
	public int getCount() {
		System.out.println(noteslist.size());
		return noteslist.size();
	}

	@Override
	public Notes getItem(int position) {
		return noteslist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convetView, ViewGroup parent) {
		System.out.println("getView被执行");
		TextView views=new TextView(context);
		List<TextView> listViews=new ArrayList<TextView>();
		if(convetView==null){
			convetView=LayoutInflater.from(getContext()).inflate(R.layout.item_noteline, null);
			TextView tView=(TextView) convetView.findViewById(R.id.note_title);
			TextView dView=(TextView) convetView.findViewById(R.id.note_date);
			TextView sView=(TextView) convetView.findViewById(R.id.note_startcontent);
			listViews.add(0,tView);
			listViews.add(1,dView);
			listViews.add(2,sView);
			convetView.setTag(new ListCell(listViews));
		}
		ListCell lc=(ListCell) convetView.getTag();
		Notes notes=getItem(position);
		listViews=lc.getListView();
		listViews.get(0).setText(notes.getTitle());
		listViews.get(1).setText(notes.getDate());
		listViews.get(2).setText(notes.getContent());
		
		return convetView;
	}
	
	public void addAll(List<Notes> notes) {
		this.noteslist.addAll(notes);
		notifyDataSetChanged();
		
	}
	public void clear() {
		noteslist.clear();
		notifyDataSetChanged();
	}
	
	
	private static class ListCell{
		private List<TextView> viewList=new ArrayList<TextView>();
		
		private TextView tView=null;
		private TextView txtView=null;
		private TextView loView=null;
		

		public ListCell(List<TextView> viewList){
		  tView=viewList.get(0);
		  txtView= viewList.get(1);
		  loView=viewList.get(2);
		  this.viewList.add(0, tView);
		  this.viewList.add(1,txtView);
		  this.viewList.add(2,loView);
		  
		}
		public List<TextView> getListView(){
			return  viewList;
		}
	}
	
	private Context getContext() {
		return context;
	}

}
