package com.example.my1stapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button signUpbtn;
    private Button signInbtn;
    private EditText email;
    private EditText password;
    private Spinner city;
    private EditText phone;
    private FirebaseAuth firebaseAuth;
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayAdapter<City> cityArrayAdapter = new ArrayAdapter<City>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, cities);


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        signUpbtn = (Button) findViewById(R.id.signUp);
        signInbtn = (Button) findViewById(R.id.signIn);
        city = (Spinner) findViewById(R.id.city);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.password);
        password = (EditText) findViewById(R.id.password);
        createLists();
        cityArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        city.setAdapter(cityArrayAdapter);
        city.setOnItemSelectedListener(city_listener);

        signUpbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                final String emailID = email.getText().toString();
                final String paswd = password.getText().toString();
                final String phoneNum = phone.getText().toString();
                if (emailID.isEmpty()) {
                    email.setError("Provide your Email first!");
                    email.requestFocus();
                } else if (paswd.isEmpty()) {
                    password.setError("Set your password");
                    password.requestFocus();
                } else if (emailID.isEmpty() && paswd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(Task task) {
                            Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            generateUser(emailID, paswd);
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, UserActivity.class));
                            }
                        }

                        public void generateUser(String username, String password) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference users = database.getReference("users");
                            User user = new User(username, password);
                            users.push().setValue(user);
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(I);
            }
        });
    }

    private AdapterView.OnItemSelectedListener city_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private void createLists() {
        cities.add(new City(0, "Choose a City"));
        cities.add(new City(1, "City1"));
        cities.add(new City(2, "City2"));
        cities.add(new City(3, "City3"));
        cities.add(new City(4, "City4"));
        cities.add(new City(5, "City5"));
        cities.add(new City(6, "City6"));
        cities.add(new City(7, "City7"));
        cities.add(new City(8, "City8"));
    }

    private class City implements Comparable<City> {

        private int cityID;
        private String cityName;

        public City(int cityID, String cityName) {
            this.cityID = cityID;
            this.cityName = cityName;
        }

        public int getCityID() {
            return cityID;
        }

        public String getCityName() {
            return cityName;
        }

        @Override
        public String toString() {
            return cityName;
        }

        @Override
        public int compareTo(City another) {
            return this.cityID - another.getCityID();//ascending order
//            return another.getCityID() - this.cityID;//descending order
        }
    }
}
