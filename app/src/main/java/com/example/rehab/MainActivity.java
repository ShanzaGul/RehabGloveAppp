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

public class MainActivity extends AppCompatActivity {

    private TextInputEditText email;
    private TextInputEditText pass;
    private ImageButton login;
    private Button forgotpass;
    private Button newacc;

    private FirebaseAuth firebaseAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
      /*  if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

*/





        email = (TextInputEditText) findViewById(R.id.email);
        pass = (TextInputEditText) findViewById(R.id.password);
        login = (ImageButton) findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                login();
            }
        });


        newacc = (Button) findViewById(R.id.newacc);
        newacc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                makeNewacc();
            }
        });

    }

    public void makeNewacc() {
        // Do something in response to button
          Intent intent = new Intent(this , NewAccActivity.class);
          startActivity(intent);

    }

    public void login(){

        String emaill = email.getText().toString().trim();
        String passs = pass.getText().toString().trim();
        if(TextUtils.isEmpty(emaill)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(passs)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(emaill,passs).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(MainActivity.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(MainActivity.this, "Login unsuccessful , Try again", Toast.LENGTH_SHORT).show();

                }




            }
        });



    }


}
