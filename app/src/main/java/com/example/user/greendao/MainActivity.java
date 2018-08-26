package com.example.user.greendao;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.greendao.db.DaoSession;
import com.example.user.greendao.db.User;
import com.example.user.greendao.db.UserDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    private FloatingActionButton mFab;
    private EditText mLoginEditText;
    private EditText mPasswordEditText;
    private RecyclerView mRecyclerView;

    private UserAdapter adapter;
    private List<User> mUser;

     public UserDao getUserDao() {
    return mUserDao;
 }
//
    private UserDao mUserDao;
     private Query<User> userQuery;
//
 @Override
     protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

     DaoSession daoSession = ((UserApp) getApplication()).getDaoSession();
    mUserDao = daoSession.getUserDao();
     userQuery = mUserDao.queryBuilder().build();
    updateUser();
  }

    public void updateUser() {
        mUser = userQuery.list();
        adapter.setUser(mUser);
  }


        private void addUser () {

            String login = mLoginEditText.getText().toString();
            String password = mPasswordEditText.getText().toString();
            mLoginEditText.setText("");
            mPasswordEditText.setText("");
            mLoginEditText.clearFocus();
            mPasswordEditText.clearFocus();

            if (login.trim().equals("") || password.trim().equals("")) {
                Toast.makeText(MainActivity.this, "Имя или логин не заполнены", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.setLogin(login);
            user.setPassword(password);


            mUserDao.insert(user);
            updateUser();
        }
        private void setupViews () {
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mFab = (FloatingActionButton) findViewById(R.id.fab);
            mLoginEditText = (EditText) findViewById(R.id.login);
            mPasswordEditText = (EditText) findViewById(R.id.password);


            mUser = new ArrayList<>();
            adapter = new UserAdapter(mUser, getLayoutInflater(), this);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(adapter);

            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
              addUser();

                }
            });
        }
    }