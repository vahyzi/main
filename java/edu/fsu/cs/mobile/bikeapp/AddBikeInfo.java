package edu.fsu.cs.mobile.bikeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

public class AddBikeInfo extends AppCompatActivity {

    private EditText make;
    private EditText model;
    private Spinner type;
    private EditText color;
    private Spinner wheel_size;
    private EditText tire_width;
    private Spinner valve_spinner;

    private Button Submit;
    private Button Reset;

    boolean formerror = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike_info);

        make = findViewById(R.id.make);
        model = findViewById(R.id.model);
        type = findViewById(R.id.type_spinner);
        color = findViewById(R.id.color);
        wheel_size = findViewById(R.id.size_spinner);
        tire_width = findViewById(R.id.tire_width);
        valve_spinner = findViewById(R.id.valve_spinner);
        Submit = findViewById(R.id.submit);
        Reset = findViewById(R.id.reset);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });


    }

    void checkData(){

        if(make.getText().toString() == ""){
            make.setError("Missing make");
            formerror = true;
        }

        if(model.getText().toString() == ""){
            model.setError("Missing model");
            formerror = true;
        }

        if(color.getText().toString() == ""){
            color.setError("Missing color");
            formerror = true;
        }

        if(tire_width.getText().toString() == ""){
            tire_width.setError("Missing tire width");
            formerror = true;
        }

        else
        {
            Intent myIntent = new Intent(AddBikeInfo.this, MainActivity.class);

            String Make = make.getText().toString();
            String Model = model.getText().toString();
            String Type = type.getSelectedItem().toString();
            String Color = color.getText().toString();
            String Wheel = wheel_size.getSelectedItem().toString();
            String Tire = tire_width.getText().toString();
            String Valve = valve_spinner.getSelectedItem().toString();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference riderRef = db.collection("riders");

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String email = user.getEmail();
            // OnLocationListener -
            DocumentReference docRef = riderRef.document(email);
            Bike bike = new Bike(Make, Model, Type, Color, Wheel, Tire, Valve);
            Rider rider = Rider.generateRider(user, new GeoPoint(1, 1), bike);
            docRef.set(rider);

            startActivity(myIntent);
        }



    }
}
