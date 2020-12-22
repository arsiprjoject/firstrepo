package com.arsi.flebie.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.arsi.flebie.CollectAmount;
import com.arsi.flebie.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CashBottom extends BottomSheetDialogFragment implements View.OnClickListener {

    EditText collectAmout;
    ImageView editBtn, saveBtn;
    String amount;
    CollectAmount parentFragment;

    public CashBottom(String amount, Fragment parentFragment) {
        this.amount = amount;
        this.parentFragment = (CollectAmount)parentFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cash_bottom, container, false);

        collectAmout = (EditText) v.findViewById(R.id.collectAmout);
        collectAmout.requestFocus();
        collectAmout.setText(amount);


//        editBtn = v.findViewById(R.id.editBtn);
//        editBtn.setOnClickListener(this);
        saveBtn = v.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);


//        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        return v;
    }

    @Override
    public void onClick(View v) {

        if (v == editBtn) {
            collectAmout.setEnabled(true);
        }

        if (v == saveBtn) {
            this.dismiss();
            parentFragment.enableMarkAsDone(1);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public void onResume() {
        super.onResume();
        collectAmout.setEnabled(false);
    }
}
