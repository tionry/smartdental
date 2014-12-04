package com.edu.thss.smartdental.adapter;

import java.util.ArrayList;

import com.edu.thss.smartdental.OneEMRActivity;
import com.edu.thss.smartdental.OneImageActivity;
import com.edu.thss.smartdental.R;
import com.edu.thss.smartdental.model.BBSElement;
import com.edu.thss.smartdental.model.ImageElement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


public class BBSListAdapter extends BaseAdapter implements Filterable{
	private class buttonViewHolder{
		//Button read; //�Ķ�
		Button delete; //ɾ��
		//Button hide; //����
		Button collect;//�ղ�
	}
	private ArrayList<BBSElement> list;
	private Context context;
	private BBSFilter filter;
	private buttonViewHolder holder;
	
	public BBSListAdapter(ArrayList<BBSElement> list, Context context){
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.bbs_list_item, null);
		}
		BBSElement post = list.get(position);
		//add!!!!
		TextView title =(TextView)convertView.findViewById(R.id.bbs_list_item_title);
		TextView preview = (TextView)convertView.findViewById(R.id.bbs_list_item_preview);
		TextView time = (TextView)convertView.findViewById(R.id.bbs_list_item_time);
		TextView author = (TextView)convertView.findViewById(R.id.bbs_list_item_author);
		
		title.setText(post.title);
		preview.setText(post.preview);
		time.setText(post.time);
		author.setText(post.author);
		
		holder = new buttonViewHolder();
		holder.delete = (Button)convertView.findViewById(R.id.bbs_list_item_delete);
		if(!post.isDeletable){
			holder.delete.setVisibility(View.INVISIBLE);
		}else{
			holder.delete.setVisibility(View.VISIBLE);
		}
		holder.collect = (Button)convertView.findViewById(R.id.bbs_list_item_collect);
		holder.collect.setText(post.isCollected?"ȡ���ղ�":"�ղ�");
		holder.delete.setOnClickListener(new ButtonListner(position));
		holder.collect.setOnClickListener(new ButtonListner(position));
		return convertView;
	}

	@Override
	public Filter getFilter() {
		if(filter == null){
			filter = new BBSFilter(list);
		}
		return filter;
	}
    public class BBSFilter extends Filter{
    	private ArrayList<BBSElement> original;
    	public BBSFilter(ArrayList<BBSElement> list){
    		this.original = list;
    	}

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			
			FilterResults results = new FilterResults();
			if(constraint == null || constraint.length()==0){ //û�й�������
				results.values = this.original;
				results.count = this.original.size();
				
			}
			else{
				
				ArrayList<BBSElement> mList = new ArrayList<BBSElement>();
				for(BBSElement image: original){
					if(image.title.toUpperCase().contains(constraint.toString().toUpperCase())
					   ||image.preview.toUpperCase().contains(constraint.toString().toUpperCase())
					   ||image.time.contains(constraint)){
						
						mList.add(image);
					}
				}
				results.values = mList;
				results.count = mList.size();
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			list = (ArrayList<BBSElement>)results.values;
			notifyDataSetChanged();
			
		}
    }
    class ButtonListner implements OnClickListener{
		private int itemPosition;
		public ButtonListner(int pos){
			this.itemPosition = pos;
		}

		@Override
		public void onClick(View v) {
			int vid = v.getId();
			if(vid == holder.delete.getId()){
			 //ɾ��
				list.remove(itemPosition);
				notifyDataSetChanged();
			}
		    /*if(vid == holder.hide.getId()){
		    	BBSElement temp = list.get(this.itemPosition);
		    	if(temp.isHidden == false)
		    	temp.isHidden = true;
		    	else temp.isHidden = false;
		    	list.set(this.itemPosition, temp);
		    	notifyDataSetChanged();
		    }*/
		    if(vid == holder.collect.getId()){
		    	
		    	BBSElement temp = list.get(this.itemPosition);
		    	if(temp.isCollected ==false)
		    	temp.isCollected = true;
		    	else temp.isCollected = false;
		    	list.set(this.itemPosition, temp);
		    	notifyDataSetChanged();
		    }
		   /* if(vid == holder.read.getId()){
		    	ImageElement temp = list.get(this.itemPosition);
		    	if(temp.isRead == false)
		    	temp.isRead = true;
		    	list.set(this.itemPosition, temp);
		    	notifyDataSetChanged();
		    	Intent intent = new Intent(context,OneImageActivity.class);
		    	intent.putExtra("imageclass", this.itemPosition);
		    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		    	context.startActivity(intent);
		    }
		    */
			
		}
		
	}
}