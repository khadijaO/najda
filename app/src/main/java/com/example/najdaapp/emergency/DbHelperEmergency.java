package com.example.najdaapp.emergency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.najdaapp.contact.ContactModel;
import com.example.najdaapp.contact.DbHelper;

public class DbHelperEmergency  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactdata";

    // Country table name
    private static final String TABLE_EMERGENCY_NAME= "emergency";

    // Country Table Columns names
    private static final String KEY_ID_E = "id";
    private static final String NAME_E = "Name";
    private static final String PH_E = "PhoneNo";
    private static final String SQL_DELETE_TABLE_E =
            "DROP TABLE IF EXISTS " + TABLE_EMERGENCY_NAME;


    public DbHelperEmergency(@Nullable Context context){

            super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_EMERGENCY_TABLE = "CREATE TABLE " + TABLE_EMERGENCY_NAME + "("
                + KEY_ID_E + " INTEGER PRIMARY KEY, "+ PH_E + " TEXT, "+ NAME_E + " TEXT)";
//        Log.d("h i", CREATE_CONTACT_TABLE);

        sqLiteDatabase.execSQL(CREATE_EMERGENCY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
//        onCreate(sqLiteDatabase);

    }
    public EmergencyModel getEmergency(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMERGENCY_NAME, new String[] {
                        NAME_E,PH_E }, null,
                null, null, null, null,"1");
        if (cursor != null)
            cursor.moveToFirst();


        EmergencyModel contact = new EmergencyModel(cursor.getString(1),
                cursor.getString(0));
        // return Emergency number
        return contact;
    }

    public void addEmergency(EmergencyModel contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelperEmergency.PH_E, contact.getPhoneNo());
        values.put(DbHelperEmergency.NAME_E, contact.getName());
        db.delete(TABLE_EMERGENCY_NAME,null,null);
        long newRowId = db.insert (DbHelperEmergency.TABLE_EMERGENCY_NAME, null, values);


//        Toast.makeText(get, "", Toast.LENGTH_SHORT).show();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMERGENCY_NAME,null,null);
        if (db != null && db.isOpen()){
            db.close();}

    }



}
