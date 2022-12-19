package com.example.myappmvvm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myappmvvm.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> getAllUser();

    @Insert
    void insertUser(User... users);

    @Delete
    void delete(User user);

}
