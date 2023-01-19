package com.example.myappmvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappmvvm.screens.AddRegistration;

import java.util.List;

public class UsersList extends RecyclerView.Adapter<UsersList.MyViewHolder> {

    private Context context;
    private List<User> userList;
    public UsersList(AddRegistration context) { this.context = context; }

    public void setUserList(List<User> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersList.MyViewHolder holder, int position) {

        holder.tvFirstName.setText(this.userList.get(position).firstName);
        holder.tvDescription.setText(this.userList.get(position).description);

    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvFirstName;
        TextView tvDescription;

        public MyViewHolder(View view){
            super(view);
            tvFirstName = view.findViewById(R.id.Name);
            tvDescription = view.findViewById(R.id.Description);

        }
    }

}
