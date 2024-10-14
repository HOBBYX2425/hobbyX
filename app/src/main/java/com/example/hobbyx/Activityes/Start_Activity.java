package com.example.hobbyx.Activityes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hobbyx.R;
import com.example.hobbyx.model.UserModel;
import com.example.hobbyx.utils.FirbaseUtil;
import com.google.firebase.auth.FirebaseAuth;

public class Start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sclscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirbaseUtil.isLoggedIn() &&  getIntent().getExtras() != null && getIntent().getExtras().getString("userId") != null) {
                    //from notification
                    String userId = getIntent().getExtras().getString("userId");
                  //  FirbaseUtil.allUsersCollectionReference().document(userId).get().addOnCompleteListener(task -> {
                  //      if (task.isSuccessful()) {
                  //          UserModel model = task.getResult().toObject(UserModel.class);
                  //          Intent intent = new Intent(Start_Activity.this, Chat.class);
                  //          intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                  //          intent.putExtra("username", model.getUserName());
                  //          intent.putExtra("person2_id", userId);
                  //          intent.putExtra("profilePic", model.getImageURL());
                  //          startActivity(intent);
                  //          finish();
//
                  //      }
                  //  });
                } else {
                    if (FirbaseUtil.isLoggedIn()) {
                        if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                            startActivity(new Intent(Start_Activity.this, MainActivity.class));
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                            startActivity(new Intent(Start_Activity.this, Login.class));
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                        }
                    } else {
                        startActivity(new Intent(Start_Activity.this, Registration.class));
                    }

                    finish();

                }
            }

        },1500);
    }

    }


