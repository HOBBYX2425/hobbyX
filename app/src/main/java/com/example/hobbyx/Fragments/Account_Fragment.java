package com.example.hobbyx.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hobbyx.Activityes.Login;
import com.example.hobbyx.Activityes.Settings;
import com.example.hobbyx.Activityes.account;
import com.example.hobbyx.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import okhttp3.internal.cache.DiskLruCache;

public class Account_Fragment extends Fragment {

    private Button logout;
    private TextView nameTv;
    private ImageView profImage;
    FirebaseUser user;
    FirebaseAuth auth;
    public static String nameText;
    public static FirebaseAuth mAuth;
    public DocumentReference ref;

    public Account_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }


    void init(View view) {
        logout = view.findViewById(R.id.logout);
        nameTv = view.findViewById(R.id.name);
        mAuth = FirebaseAuth.getInstance();
        profImage = view.findViewById(R.id.profile_image);


        profImage.setOnClickListener(v -> {
            // Анимация перемещения кнопки в центр
            ObjectAnimator moveX = ObjectAnimator.ofFloat(profImage, "translationX", 500 - profImage.getX());
            ObjectAnimator moveY = ObjectAnimator.ofFloat(profImage, "translationY", 1300 - profImage.getY());
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(moveX, moveY);
            animatorSet.setDuration(200); // Задайте длительность анимации

            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    // Создаем BottomSheetDialog
                    Dialog  dialogSheet = new Dialog(getActivity());
                    dialogSheet.setContentView(R.layout.fragment_acc_dialog);
                    dialogSheet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    dialogSheet.getWindow().setBackgroundDrawableResource(R.drawable.bt_navigation_bg);

                    LinearLayout settings = dialogSheet.findViewById(R.id.settings);
                    LinearLayout logOut = dialogSheet.findViewById(R.id.logout);

                    settings.setOnClickListener(view1 -> {
                        Intent intent = new Intent(getActivity(), Settings.class);
                        startActivity(intent);
                    });

                    logOut.setOnClickListener(view12 -> {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                    });

                    // Размещаем диалог по центру экрана
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom( dialogSheet.getWindow().getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    layoutParams.x = (int) (500 - profImage.getX());
                    layoutParams.y = (int) (1300 - profImage.getY());
                    dialogSheet.getWindow().setAttributes(layoutParams);

                    // Закрытие диалога и возврат ImageView на место
                    dialogSheet.setOnDismissListener(dialog -> {
                        // Анимация возвращения в исходное положение
                        ObjectAnimator moveBackX = ObjectAnimator.ofFloat(profImage, "translationX", 500 - profImage.getX());
                        ObjectAnimator moveBackY = ObjectAnimator.ofFloat(profImage, "translationY", 1300 - profImage.getY());
                        AnimatorSet reverseAnimatorSet = new AnimatorSet();
                        reverseAnimatorSet.playTogether(moveBackX, moveBackY);
                        reverseAnimatorSet.setDuration(200); // Длительность обратной анимации
                        reverseAnimatorSet.start();
                    });

                    dialogSheet.show();
                }
            });
            animatorSet.start();
        });


        user = FirebaseAuth.getInstance().getCurrentUser();

        ref = FirebaseFirestore.getInstance().collection("users").document(user.getUid());

        ref.addSnapshotListener((value, error) -> {

            if(error != null){
             return;
            }
            if(value != null && value.exists()){
                String userName = value.getString("userName");
                if(userName != null){
                    nameTv.setText(userName);
                }
            }


        });


    }
}
