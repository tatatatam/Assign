package com.example.tam_o.assign;

import android.provider.BaseColumns;

public class UserIni {
    public static final String DATABASE_NAME = "user_ini.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "user";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String EMAIL = "email";
        public static final String PRIVILEGE = "privilege";
        public static final String PASSWORD = "password";

    }

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String privilege;
    private String password;
    public UserIni(){

    }

    public UserIni(int id, String firstName, String lastName, String email,String privilege, String password ){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.privilege = privilege;
        this.password = password;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrivilege(String privilege) {
       this.privilege = privilege;
    }

    public String getPrivilege(){
       return privilege;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }


}


