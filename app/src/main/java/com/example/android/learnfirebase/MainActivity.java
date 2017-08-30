package com.example.android.learnfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private Button firebaseButton;
    private DatabaseReference mDatabaseReference;

    private EditText mNameField;
    private EditText mEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseButton = (Button) findViewById(R.id.firebase_button);
        mNameField = (EditText)findViewById(R.id.name_field);
        mEmailId = (EditText)findViewById(R.id.email_id);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("User_01");

        firebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create child in root object
                String name = mNameField.getText().toString().trim();
                String email = mEmailId.getText().toString().trim();
                // assign some value to child


                //way 1
                //
                // mDatabaseReference.push().child("Name").setValue(name);

                /*
                Way 2
                Using hash map
                 */

                HashMap<String, String> userMap = new HashMap<String, String>();
                userMap.put("Name", name);
                userMap.put("Email", email);

                mDatabaseReference.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Is successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Is unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}
