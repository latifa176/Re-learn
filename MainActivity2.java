package com.example.projec2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import com.example.projec2.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;


public class MainActivity2 extends AppCompatActivity {
    EditText EditTextuniname,EditTextmaterialname,EditTextcoursename,EditTextprice;
EditText IBAN;
    EditText bankname;
    EditText description;
EditText accountowner;
Button buttonDone;
Spinner spinnertype;
Button choosebtn,uploadbtn;
StorageReference db;
//Uri filePath;
//final int PICK_IMAGE_REQUEST=71;

String iban="";

    String accountowner1="";
    String bankname1="";
    String description1="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = FirebaseStorage.getInstance().getReference("posts");
        IBAN = (EditText) findViewById(R.id.IBAN);
        accountowner= (EditText) findViewById(R.id.accountowner);
        bankname = (EditText) findViewById(R.id.bankname);
        description = (EditText) findViewById(R.id.description);
        EditTextmaterialname = (EditText) findViewById(R.id.EditTextmaterialname);
        EditTextcoursename = (EditText) findViewById(R.id.EditTextcoursename);
        EditTextuniname = (EditText) findViewById(R.id.EditTextuniname);
        EditTextprice = (EditText) findViewById(R.id.EditTextprice);

        spinnertype = (Spinner) findViewById(R.id.spinnertype);
        buttonDone = (Button) findViewById(R.id.buttonDone);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPost();

            }
        });
    }







    private void addPost(){
        String materialname = EditTextmaterialname.getText().toString().trim();
        String coursename = EditTextcoursename.getText().toString().trim();
        String uniname = EditTextuniname.getText().toString().trim();
        String materialtype= spinnertype.getSelectedItem().toString().trim();
        String price= EditTextprice.getText().toString().trim();
String iban=IBAN.getText().toString().trim();

        accountowner1=accountowner.getText().toString().trim();
         bankname1=bankname.getText().toString().trim();
        description1=description.getText().toString().trim();

        if(!TextUtils.isEmpty(materialname) &&
                !TextUtils.isEmpty(coursename) && !TextUtils.isEmpty(uniname) &&
        !TextUtils.isEmpty(price) && !TextUtils.isEmpty(materialtype) && !TextUtils.isEmpty(iban) && !TextUtils.isEmpty(bankname1) && !TextUtils.isEmpty(accountowner1) && !TextUtils.isEmpty(description1) )
        {

          String id=   db.child("books").getName();
          com.example.o.Post post = new com.example.o.Post(id,materialname,coursename,uniname,materialtype,price,iban,bankname1,accountowner1,description1);
db.child(id).equals(post);
Toast.makeText(this,"Done", Toast.LENGTH_LONG).show();
        }else{
    Toast.makeText(this,"please make sure all information are entered",Toast.LENGTH_LONG).show();

        }
    }

}
