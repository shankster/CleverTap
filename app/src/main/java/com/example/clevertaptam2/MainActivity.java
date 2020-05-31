package com.example.clevertaptam2;
import com.clevertap.android.sdk.CleverTapAPI;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        final EditText editText=findViewById(R.id.editText);
        setSupportActionBar(toolbar);
        // CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());

        Button button = (Button) findViewById(R.id.createProfile);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
//                clevertapDefaultInstance.pushEvent("Product viewed");
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

//Update pre-defined profile properties
                String emailAddress=editText.getText().toString();
                profileUpdate.put("Name", "Sample User");
                profileUpdate.put("Email", emailAddress);
//Update custom profile properties
                profileUpdate.put("Plan Type", "Silver");
                profileUpdate.put("Favorite Food", "Crepes");
                clevertapDefaultInstance.pushProfile(profileUpdate);
                Snackbar.make(v, "Profile Pushed ", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        Button button2 = (Button) findViewById(R.id.pushEvent);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
//                clevertapDefaultInstance.pushEvent("Product viewed");
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

//Update pre-defined profile properties
                String emailAddress=editText.getText().toString();

                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
                prodViewedAction.put("Product Image", "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg");
                prodViewedAction.put("Product Name", "CleverTap");
                prodViewedAction.put("Email Id", emailAddress);
//                prodViewedAction.put("Date", new java.util.Date());
                System.out.println(prodViewedAction.get("Email Id"));
                clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);
                Snackbar.make(v, "Event Pushed ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
