package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hobbyx.Activityes.Login;
import com.example.hobbyx.R;
import com.google.firebase.auth.FirebaseAuth;

public class Account_Fragment extends Fragment {

    private Button logout;

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
        logout = view.findViewById(R.id.logout); // Initialize the button

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Login.class); // Use getActivity() instead of getApplicationContext()
                startActivity(intent);
                // If you're using a fragment, don't call finish() here; it's for activities only
                // Optionally, you could clear the back stack if needed
                // getActivity().finish();
            }
        });
    }
}
