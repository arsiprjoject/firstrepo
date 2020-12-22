package com.arsi.flebie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.arsi.flebie.ui.tools.ToolsFragment;

public class SettingsActivity  extends AppCompatActivity implements View.OnClickListener {

    Toolbar settingsToolbar;
    ImageView backArrow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        settingsToolbar =  findViewById(R.id.settingToolbar);
        backArrow = findViewById(R.id.settingsBackArrow);
        backArrow.setOnClickListener(this);
        openFragment(new ToolsFragment());
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container3, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        if (v == backArrow) {
            Intent it = new Intent(this, BaseActivity.class);
            startActivity(it);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
