package com.example.hobbyx.Activityes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hobbyx.R;
import com.example.hobbyx.model.UserModel;
import com.example.hobbyx.utils.FirbaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class LoginUsernameActivity extends AppCompatActivity {
    EditText usernameInput;
    Button finish_btn;
    ProgressBar  progressBar;
    UserModel userModel;
    String phoneNumber;
    ////
    List<String> folowers,folowing;
    String imageURL;
    String status;
    FirebaseUser user;

    ////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_username);
        usernameInput = findViewById(R.id.login_username);
        finish_btn = findViewById(R.id.login_finish_btn);
        progressBar = findViewById(R.id.login_progress_bar_3);
        phoneNumber = getIntent().getExtras().getString("phone");

        getUserName();
        finish_btn.setOnClickListener(v -> {
            setUserName();
        });


    }

    void setUserName(){
        setInProgress(true);
        String username = usernameInput.getText().toString();
        if(username.isEmpty() || username.length() < 3){
            usernameInput.setError("Username length should be  at least 3 chars");
            return;
        }
        if(userModel != null){
            userModel.setUserName(username);
        }else{
            userModel = new UserModel(phoneNumber,username, Timestamp.now(),FirbaseUtil.currentUsersId(),
                    ////////////////////////////
                   imageURL, false
                       );
        }
        FirbaseUtil.currentUsersDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginUsernameActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    startActivity(intent);

                }
            }
        });
    }
    void getUserName(){
        setInProgress(true);
        FirbaseUtil.currentUsersDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    userModel = task.getResult().toObject(UserModel.class);
                    if(userModel != null){
                        usernameInput.setText(userModel.getUserName());
                    }
                }

            }
        });
    }

    void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            finish_btn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            finish_btn.setVisibility(View.VISIBLE);
        }
    }



}