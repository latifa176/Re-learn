package com.example.projec2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("RE-Learn");
    }




    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = " ";
        switch (item.getItemId()) {
            case R.id.action_search: {
                toolbar.setSubtitle("Search");
                msg = " search";
                break;
            }
            case R.id.action_home:
                toolbar.setSubtitle("Home");
                msg = " home page";
                break;
            case R.id.favorit:
                toolbar.setSubtitle("Favorite");
                msg=" favorite";
                break;

            case R.id.AddPost:
                toolbar.setSubtitle("Add Post");
                msg=" Add Post";
                break;


        }
        Toast.makeText(this,msg+"checked", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

}
