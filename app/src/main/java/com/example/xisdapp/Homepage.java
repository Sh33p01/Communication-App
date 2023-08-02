package com.example.xisdapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Homepage extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<messages> list;

    DatabaseReference db;
    FirebaseAuth auth;
    FirebaseUser user;
    private ProgressBar progressBar;
    TextInputLayout message;
    FloatingActionButton send;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recyclerView = findViewById(R.id.recyclerView);
        auth = FirebaseAuth.getInstance();
        message =  findViewById(R.id.messageBox);
        send = findViewById(R.id.senbtn);
        progressBar =  findViewById(R.id.progressB);


        list = new ArrayList<>();

        user = auth.getCurrentUser();
        String uid = user.getUid();
        String uEmail = user.getEmail();
        String timeStamp = new SimpleDateFormat("dd-MM-yy HH:mm a").format(Calendar.getInstance().getTime());
        db = FirebaseDatabase.getInstance().getReference();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Msg = message.getEditText().getText().toString();

                if(Msg.isEmpty())
                {
                    Toast.makeText(Homepage.this, "Message cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.child("Messages").push().setValue(new messages(uEmail,Msg,timeStamp)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()) {
                                message.getEditText().setText("");
                                Toast.makeText(Homepage.this, "Message was send", Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                Toast.makeText(Homepage.this, "Message was not sendddd", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }

            }
        });

        adapter = new RecyclerViewAdapter(this, list);
        LinearLayoutManager l = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        pullMessages();

    }

    private void pullMessages()
    {
        db.child("Messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                for (DataSnapshot snap: snapshot.getChildren()) {

                    messages ms =  snap.getValue(messages.class);
                    list.add(ms);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}