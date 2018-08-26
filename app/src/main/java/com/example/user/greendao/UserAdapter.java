package com.example.user.greendao;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.greendao.db.User;

import java.util.List;



public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.UserHolder>

{

    private List<User> mUser;
    private LayoutInflater mInflater;
    private AppCompatActivity mAppCompatActivity;

    public UserAdapter(List<User> users, LayoutInflater inflater, AppCompatActivity activity) {
        mUser = users;
        mInflater = inflater;
        mAppCompatActivity = activity;
    }
//
    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User user = mUser.get(position);
        holder.bindUser(user);

    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }
//
    public void setUser(List<User> user) {
        mUser = user;
        notifyDataSetChanged();
    }
    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mlogin;
        private TextView mpassword;
        private User user;


        public UserHolder(View itemView) {
            super(itemView);

            mlogin = (TextView) itemView.findViewById(R.id.login);
            mpassword = (TextView) itemView.findViewById(R.id.password);
            itemView.setOnClickListener(this);

        }
//
        public void bindUser(User user) {
            this.user = user;
            mlogin.setText(user.getLogin());
            mpassword.setText(user.getPassword());
        }

        @Override
        public void onClick(View view) {
            User user = mUser.get(getLayoutPosition());
            Long userId = user.getId();
           ((MainActivity)mAppCompatActivity).getUserDao().deleteByKey(userId);
           ((MainActivity)mAppCompatActivity).updateUser();
        }

    }
}