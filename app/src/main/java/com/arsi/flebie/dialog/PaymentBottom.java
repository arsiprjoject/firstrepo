package com.arsi.flebie.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.arsi.flebie.Api;
import com.arsi.flebie.BaseActivity;
import com.arsi.flebie.CollectAmount;
import com.arsi.flebie.MainActivity;
import com.arsi.flebie.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PaymentBottom  extends BottomSheetDialogFragment implements View.OnClickListener {
    private static final int CAMERA_PIC_REQUEST = 1337;
    ImageView cameraIcon, qrCodeImg, closePayment;
//    ImageView uploadPic;
    boolean qrPayment;
//    String imgUrl = "http://139.59.11.135:9004/api/client/flebie/branch/Prima Diagnostics/service/DIAGNOSTICS/payment/qrcode";
    CollectAmount parentFragment;
    TextView txtMsg;

    public PaymentBottom(boolean qrPayment, Fragment parentFragment){
        this.qrPayment = qrPayment;
        this.parentFragment = (CollectAmount)parentFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.payment_bottom, container, false);

        cameraIcon = v.findViewById(R.id.cameraIcon);
        cameraIcon.setOnClickListener(this);

        txtMsg = v.findViewById(R.id.txtMsg);

        qrCodeImg = v.findViewById(R.id.qrCodeImg);
        qrCodeImg.setVisibility(View.GONE);

        closePayment = v.findViewById(R.id.closePayment);
        closePayment.setOnClickListener(this);

//        uploadPic = v.findViewById(R.id.uploadPic);
//        uploadPic.setOnClickListener(this);

        if(qrPayment) {
            qrCodeImg.setVisibility(View.VISIBLE);
            txtMsg.setText("Present the below QR Code to the customer for payment. Upload payment Confirmation image");
        }

        SharedPreferences prefs = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String clientId = prefs.getString("clientId", null);
        String imgUrl = "http://139.59.11.135:9004/api/client/"+clientId+"/branch/"+MainActivity.branch+"/service/DIAGNOSTICS/payment/qrcode";
        Picasso.with(getContext()).load(imgUrl).placeholder( R.drawable.progress_animation ).into(qrCodeImg, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                qrCodeImg.setVisibility(View.GONE);
                Toast.makeText(getContext(), "QR code not found", Toast.LENGTH_SHORT).show();
                cameraIcon.setClickable(false);
            }
        });
        return v;
    }


    @Override
    public void onClick(View v) {
        if (v == cameraIcon) {
            final Fragment amountFragment = (CollectAmount)getActivity().getSupportFragmentManager().findFragmentById(R.id.container2);
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            amountFragment.startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
        }

        if(v == closePayment) {
            this.dismiss();
        }

//        if (v == uploadPic) {
//            updatePaymentStatus();
//            if (qrPayment) {
//                parentFragment.enableMarkAsDone(2);
//            } else {
//                parentFragment.enableMarkAsDone(3);
//            }
//
//        }
    }

//    private void updatePaymentStatus () {
//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show();
//
//
//        Api.baseTwo().cashReceived(MainActivity.orderId, "",new Callback() {
//            @Override
//            public void success(Object o, Response response) {
//                progressDialog.dismiss();
//                final CollectAmount amountFragment = (CollectAmount)getActivity().getSupportFragmentManager().findFragmentById(R.id.container2);
//                amountFragment.uploadImage();
//                dismiss();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                progressDialog.dismiss();
//                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

}
