package com.example.rehab;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeActivity extends AppCompatActivity {

    Button on;
    Button off;


    RadioGroup radioGrp;
    RadioButton radioBtn;
    int radioId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        on = (Button) findViewById(R.id.button_on);
        off = (Button) findViewById(R.id.button_off);


         radioGrp= (RadioGroup) findViewById(R.id.radioGroup);
         radioId = radioGrp.getCheckedRadioButtonId();

        on.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                int radioId = radioGrp.getCheckedRadioButtonId();
                radioBtn= findViewById(radioId);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myReff = database.getReference("CASE");

                // Check which radio button was clicked
                if(radioId==R.id.radio_High)
                    myReff.setValue(0);
                else if (radioId==R.id.radio_Medium)
                    myReff.setValue(1);
                else
                    myReff.setValue(2);

                DatabaseReference myRef = database.getReference("LED_STATUS");
                myRef.setValue(0);
            }
        });

        off.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STATUS");
                myRef.setValue(1);
            }
        });






    }

    public void checkButton(View view ){
        int radioId = radioGrp.getCheckedRadioButtonId();
        radioBtn= findViewById(radioId);
    }
}
