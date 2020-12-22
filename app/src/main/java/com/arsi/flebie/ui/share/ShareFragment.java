package com.arsi.flebie.ui.share;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.arsi.flebie.LoginActivity;
import com.arsi.flebie.R;

public class ShareFragment extends Fragment implements View.OnClickListener {

    private Button logoutBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        logoutBtn = root.findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == logoutBtn) {
            SharedPreferences settings = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            Intent loginscreen = new Intent(getContext(), LoginActivity.class);
            loginscreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginscreen);
            getActivity().finish();
        }
    }
}