package com.example.myassignment_gzh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by g on 2020/3/25.
 */
public class MyListAdapter extends BaseAdapter{
    List<ItemBean> mList;
    private Context mContext;
    private LayoutInflater myLayoutInflater;
    public MyListAdapter(Context context, List<ItemBean> list){
        mList = list;
        myLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override

    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    static class ViewHolder{
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        ItemBean item = mList.get(i);
        if(view==null){
            view=myLayoutInflater.inflate(R.layout.activity_items,null);

            holder= new ViewHolder();
            holder.imageView = (ImageView)view.findViewById(R.id.picture);
            holder.title = (TextView)view.findViewById(R.id.tv1);
            holder.content = (TextView)view.findViewById(R.id.tv2);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(item.getItemTitle());
        holder.content.setText(item.getItemContent());
        holder.imageView.setImageResource(item.getItemImageResourceId());
        //改成利用Picasso框架加载图片
        //Picasso.with(mContext).load(item.getItemImageResourceId()).into(holder.imageView);
        view.setBackgroundResource(item.getBackgroundColor());
        return view;
    }
}
