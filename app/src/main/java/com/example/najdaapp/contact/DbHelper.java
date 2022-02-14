package com.example.najdaapp.contact;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactdata";

    // Country table name
    private static final String TABLE_NAME= "contacts";

    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String NAME = "Name";
    private static final String H = "Phone";
    private static final String PH = "PhoneNo";
    private static final String RELATION = "Relation";
    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the table for the first time
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "+ PH + " TEXT, "+ NAME + " TEXT, "
                 + RELATION + " TEXT )";
//        Log.d("h i", CREATE_CONTACT_TABLE);
        Log.d("hi", "3333333333333333333333333333333333333");

        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
//        onCreate(sqLiteDatabase);
//    }
    public boolean checkContact(String name, String phone) {



        return true;

    }

//    @Override
//    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        super.onDowngrade(db, oldVersion, newVersion);
//    }

    public  ContactModel getContact(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {
                        NAME,PH,RELATION  }, PH + "=?",
                new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


        ContactModel contact = new ContactModel(cursor.getString(1),
                cursor.getString(0), cursor.getString(2));
        // return country
        return contact;
    }

    public void deleteContact(ContactModel c){


            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, PH + " = ?",
                    new String[] { String.valueOf(c.getPhoneNo()) });
            db.close();

    }

    // method to add the contact
    public void addContact(ContactModel contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.PH, contact.getPhoneNo());
        values.put(DbHelper.NAME, contact.getName());
        values.put(DbHelper.RELATION, contact.getRelation());
        long newRowId = db.insert (DbHelper.TABLE_NAME, null, values);

        Log.e("hi", "####################################");

//        Toast.makeText(get, "", Toast.LENGTH_SHORT).show();
    }

    // method to retrieve all the contacts in List
    public List<ContactModel> getAllContacts(){
        List<ContactModel> list=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery(query,null);
        if(c.moveToFirst()) {
            do {

                list.add(new ContactModel(c.getString(1),c.getString(2),c.getString(3)));

            } while (c.moveToNext());
        }
        return list;
    }

    // get the count of data, this will allow user
    // to not add more that five contacts in database
    public int count(){
        int count=0;
        String query="SELECT COUNT(*) FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db.rawQuery(query,null);
        if(c.getCount()>0){
            c.moveToFirst();
            count=c.getInt(0);
        }
        c.close();
        return count;
    }

    public void updateContact(ContactModel contact,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, contact.getName());
        values.put(PH, contact.getPhoneNo());
        values.put(RELATION, contact.getRelation());


        // updating row
         db.update(TABLE_NAME, values, PH + " = ?",
                new String[] { phone});
    }
}

