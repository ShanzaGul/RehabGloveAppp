package com.example.rehab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewAccActivity extends AppCompatActivity {

    TextInputEditText email, password,phone;
    ImageButton Go;
    String myEmail, myPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newacc);
        mAuth = FirebaseAuth.getInstance();


       /* //if getCurrentUser does not returns null
        if(mAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
*/
        email= (TextInputEditText) findViewById(R.id.eee);
        password= (TextInputEditText) findViewById(R.id.ppp);

        myEmail=email.getText().toString().trim();
        myPass =password.getText().toString().trim();
        if(TextUtils.isEmpty(myEmail)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(myPass)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }





        Go = (ImageButton) findViewById(R.id.signup);
        Go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                myEmail=email.getText().toString();
                myPass =password.getText().toString();
                CreateNewUser();
            }
        });

    }

 private void CreateNewUser()

    {

        mAuth.createUserWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(NewAccActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                           Intent homeIntent = new Intent(NewAccActivity.this , HomeActivity.class);
                            startActivity(homeIntent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(NewAccActivity.this, "Unsuccessful Registration", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
    }
