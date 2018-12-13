package com.example.tam_o.assign;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static java.lang.String.valueOf;

public class DBHelper extends SQLiteOpenHelper{
    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;
    public DBHelper(Context context) {
        super(context, UserIni.DATABASE_NAME, null, UserIni.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = String.format("CREATE TABLE %s "+
                "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                UserIni.TABLE,
                UserIni.Column.ID,
                UserIni.Column.FIRST_NAME,
                UserIni.Column.LAST_NAME,
                UserIni.Column.EMAIL,
                UserIni.Column.PRIVILEGE,
                UserIni.Column.PASSWORD);
        Log.i("test Db", CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = "DROP TABLE IF EXISTS " + UserIni.TABLE;

        db.execSQL(DROP_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    //crud op
    public void insertUser(UserIni user){
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserIni.Column.FIRST_NAME, user.getFirstName());
        values.put(UserIni.Column.LAST_NAME, user.getLastName());
        values.put(UserIni.Column.EMAIL, user.getEmail());
        values.put(UserIni.Column.PRIVILEGE, user.getPrivilege());
        values.put(UserIni.Column.PASSWORD, user.getPassword());

        long id = sqLiteDatabase.insert(UserIni.TABLE, null, values);
        Log.i("id", valueOf(id));

        sqLiteDatabase.close();
    }

    public UserIni getByUser(String email) {
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(UserIni.TABLE,
                null,
                UserIni.Column.EMAIL+" = ? ",
                new String[] {email},
                null,
                null,
                null,
                null
                );
        if(cursor != null) cursor.moveToFirst();

        UserIni user = new UserIni();
        user.setId((int)cursor.getLong(0));
        user.setFirstName(cursor.getString(1));
        user.setLastName(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setPrivilege(cursor.getString(4));
        user.setPassword(cursor.getString(5));
        return user;
    }
    public UserIni getById(int id) {
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(UserIni.TABLE,
                null,
                UserIni.Column.ID+" = ? ",
                new String[] {valueOf(id) },
                null,
                null,
                null,
                null
        );
        if(cursor != null) cursor.moveToFirst();

        UserIni user = new UserIni();

        user.setId((int)cursor.getLong(0));
        user.setFirstName(cursor.getString(1));
        user.setLastName(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setPrivilege(cursor.getString(4));
        user.setPassword(cursor.getString(5));
        Log.i("custor", cursor.getString(0));

        return user;
    }

    public void updateUserById(UserIni user){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.i("test data", user.getFirstName()+user.getLastName());
        values.put(UserIni.Column.ID, user.getId());
        values.put(UserIni.Column.FIRST_NAME, user.getFirstName());
        values.put(UserIni.Column.LAST_NAME, user.getLastName());
        sqLiteDatabase.update(UserIni.TABLE,
                values,
                UserIni.Column.ID+ " =? ",
                new String[] { valueOf(user.getId())}
                );
        sqLiteDatabase.close();
    }

    public void deleteUserById(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(UserIni.TABLE,UserIni.Column.ID+" = "+valueOf(id), null);
        sqLiteDatabase.close();
    }

}
