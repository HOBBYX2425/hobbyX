package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hobbyx.Activityes.Login;
import com.example.hobbyx.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
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
