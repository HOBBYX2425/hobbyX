package com.example.hobbyx.Activityes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hobbyx.R;
import com.example.hobbyx.utils.FirbaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.messaging.FirebaseMessaging;

import com.example.hobbyx.Fragments.Account_Fragment;
import com.example.hobbyx.Fragments.Community_Fragment;
import com.example.hobbyx.Fragments.Hobby_Fragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Account_Fragment account_fragment;
    Community_Fragment communityFragment;
    Hobby_Fragment hobbyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        account_fragment = new Account_Fragment();
        communityFragment = new Community_Fragment();
        hobbyFragment = new Hobby_Fragment();
        bottomNavigationView = findViewById(R.id.bottom_navigation);



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.hobby) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, hobbyFragment).commit();

                }
                if (item.getItemId() == R.id.community) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, communityFragment).commit();

                }

                if (item.getItemId() == R.id.account) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, account_fragment).commit();
                }

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.hobby);
        getFCMToken();





    }
    private void getFCMToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()){
                    String token = task.getResult();
                    Log.i("fcmToken",token);

                    FirbaseUtil.currentUsersDetails().update("fcmToken",token);

                }else{
                    //         Toast.makeText(MainActivity.this, "token", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void  onBackPressed(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Выход с приложения");
        alertDialog.setMessage("Вы хотите выйти из приложения?");
        alertDialog.setPositiveButton("Да",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }




}
