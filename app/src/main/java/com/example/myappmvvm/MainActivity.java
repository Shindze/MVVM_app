package com.example.myappmvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myappmvvm.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    UsersList usersList;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        Button addNewUserButton = findViewById(R.id.addButton);

        Button addNewUser = findViewById(R.id.addButton);
        addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity., AddNewUserActivity.class));
            }
        });

        initRecyclerView();

        loadUserList();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewByid(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        UsersList usersList = new UsersList(this);
        recyclerView.setAdapter(usersList);
    }

    private void loadUserList(){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<User> userList = db.userDao().getAllUser();
        usersList.setUserList(userList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == 100){
            loadUserList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            if (grantResult[0] == PackageManager.PERMISSION_GRANTED){
                showContacts();
            } else{
                Toast.makeText(this, "Дайте разрешение)", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showContacts(){
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else{
            List<String> contacts = getContactNames();
            ListView recyclerView = findViewById(R.id.recyclerView);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
            recyclerView.setAdapter(adapter);
        }
    }

    private List<String> getContactNames(){
        List<String> contacts = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContractsContract.Contracts.CONTENT_URI, null, null, null, null);


        // firstName объекта user присваивается значение полученного имени
        if (cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContractsContract.Contract.DISPLAY_NAME));
                contracts.add(name);
                AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
                User user = new User();
                user.firstName = name;
                db.userDao().insertUser(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contacts;

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        userListAdapter = new UsersListAdapter(this);
        recyclerView.setAdapter(userListAdapter);
    }

}