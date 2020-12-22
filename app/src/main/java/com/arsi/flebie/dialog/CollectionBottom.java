package com.arsi.flebie.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arsi.flebie.Api;
import com.arsi.flebie.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CollectionBottom extends BottomSheetDialogFragment implements View.OnClickListener {

    ListView collectionList;
    List<String> collectionArray;
    public static String collectionString = "";
    ImageView closeCollection;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.collection_bottom, container, false);

        collectionList = v.findViewById(R.id.collectionList);
        collectionArray = new ArrayList<String>();

        closeCollection = v.findViewById(R.id.closeCollection);
        closeCollection.setOnClickListener(this);

        if(collectionString != "") {
            collectionArray = Arrays.asList(collectionString.split(","));
        } else {
            collectionArray.add("ADHAR CARD");
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.collection_item, collectionArray);
        collectionList.setAdapter(adapter);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == closeCollection) {
            dismiss();
        }
    }
}
