//hhhhhhh

package com.example.najdaapp.contact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.najdaapp.DBSqLite.DatabaseHelper;
import com.example.najdaapp.R;
import com.example.najdaapp.contact.ContactModel;
import com.example.najdaapp.contact.DbHelper;
import com.example.najdaapp.contactAdapter.CustomAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ContactManager extends AppCompatActivity {
EditText text;
DatabaseHelper d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);
        d=new DatabaseHelper(getApplicationContext());

        ArrayList<ContactModel>l;

        l= (ArrayList<ContactModel>) d.getAllContacts();
        ListView lv=findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), l);
        lv.setAdapter(adapter);


    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String selectedItem = (String) adapterView.getItemAtPosition(i);
            Toast.makeText(getApplicationContext(), selectedItem+"  contact", Toast.LENGTH_SHORT).show();

        }
    });
    }



}
