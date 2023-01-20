package com.example.myappmvvm;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<LauncherActivity.ListItem>;
    private Context context;

    public ListAdapter(List<LauncherActivity.ListItem> listItems, Context context){
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new RecyclerView.ViewHolder();
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        LauncherActivity.ListItem listItem = LauncherActivity.ListItem.get(position);
        holder.tvTitul.setText(listItem).getTitleText();
        holder.tvContent.setText(listItem).getTitleText();
        holder.tvId.setText(String.valueOf(listItem.getClass()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitul;
        public TextView tvContent;
        public TextView tvId;

        public ViewHolder(View itemView){
            super(itemView);

            tvTitul = (TextView) itemView.findViewById(R.id.tvtitul);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
        }
    }
}
