package com.arsi.flebie;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arsi.flebie.dialog.CashBottom;
import com.arsi.flebie.dialog.PaymentBottom;
import com.arsi.flebie.pojo.PaymentLink;
import com.arsi.flebie.ui.home.HomeFragment;
import com.ncorti.slidetoact.SlideToActView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;


public class CollectAmount extends Fragment implements View.OnClickListener {

    ImageView arrowBackIcon, cachePayment10,cachePayment20,cachePayment30,clicked_image_id ;
    TextView costTxt, orderStatus;
    TextView cachePayment1, cachePayment11,cachePayment2, cachePayment21, cachePayment3, cachePayment31;
    Button sendPayment, pwpBtn;
    SlideToActView markAsDone;
    CardView cpCardView,qrCardView,spCardView;
    File finalFile;
    PaymentBottom payDialog;
    boolean qrPayment = false;

    public CollectAmount() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 1);
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_collect_amount_new, container, false);

        costTxt = root.findViewById(R.id.costTxt);
        costTxt.setText("\u20B9"+MainActivity.amountTOCollect);

        orderStatus = root.findViewById(R.id.orderStatus);
//        orderStatus.setText(""+MainActivity.orderStatus);

        cachePayment10= root.findViewById(R.id.cachePayment10);
        cachePayment1 = root.findViewById(R.id.cachePayment1);

        cachePayment11 = root.findViewById(R.id.cachePayment11);
        cachePayment11.setOnClickListener(this);

        cachePayment20= root.findViewById(R.id.cachePayment20);
        cachePayment2 = root.findViewById(R.id.cachePayment2);

        cachePayment21= root.findViewById(R.id.cachePayment21);
        cachePayment21.setOnClickListener(this);

        cachePayment30= root.findViewById(R.id.cachePayment30);
        cachePayment3 = root.findViewById(R.id.cachePayment3);

        cachePayment31= root.findViewById(R.id.cachePayment31);
        cachePayment31.setOnClickListener(this);


        markAsDone = root.findViewById(R.id.swipeBtn);
        markAsDone.setEnabled(false);
        markAsDone.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {


                Api.baseTwo().cashReceived(MainActivity.orderId, "", new Callback() {
                    @Override
                    public void success(Object o, Response response) {
                        Toast.makeText(getContext(), "Updated", Toast.LENGTH_LONG).show();
                        Intent it = new Intent(getContext(), BaseActivity.class);
                        getContext().startActivity(it);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        pwpBtn = root.findViewById(R.id.pwpBtn);
        pwpBtn.setOnClickListener(this);

        qrCardView = root.findViewById(R.id.qrCardView);
        qrCardView.setOnClickListener(this);

        cpCardView = root.findViewById(R.id.cpCardView);
        cpCardView.setOnClickListener(this);

        spCardView = root.findViewById(R.id.spCardView);
        spCardView.setOnClickListener(this);

        clicked_image_id = (ImageView)root.findViewById(R.id.imgPreview);
        return root;
    }

    public void enableMarkAsDone(int btnNum) {
        markAsDone.setEnabled(true);
        if (btnNum ==1 ) {
            disableCardView2();
            disableCardView3();
            pwpBtn.setEnabled(false);
        }

        if (btnNum == 2) {
            disableCardView1();
            disableCardView3();
            pwpBtn.setEnabled(false);
        }

        if (btnNum == 3) {
            disableCardView1();
            disableCardView2();
            pwpBtn.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {

   // show progress dialog
        if( v == arrowBackIcon) {
            if(getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
            else{
                //super.onBackPressed();
            }
        }

        if (v == cpCardView) {
            CashBottom cb = new CashBottom("\u20B9"+MainActivity.amountTOCollect, this);
            cb.show(getActivity().getSupportFragmentManager(), "CashPayment");
        }
        if (v == qrCardView) {
            qrPayment = true;
            payDialog = new PaymentBottom(true, this);
            payDialog.show(getActivity().getSupportFragmentManager(), "QRCodePayment");
        }

        if (v == spCardView) {
            sendPaymentLink();
            payDialog = new PaymentBottom(false, this);
            payDialog.show(getActivity().getSupportFragmentManager(), "SMSPayment");
        }
        if (v == pwpBtn) {
            pwpBtn.setBackgroundColor(getResources().getColor(R.color.buttonClr));
            pwpBtn.setTextColor(getResources().getColor(R.color.whiteClr));
            pwpBtn.setEnabled(false);

            disableCardView1();
            disableCardView2();
            disableCardView3();

            markAsDone.setEnabled(true);
        }

        if(v == sendPayment) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setCancelable(false); // set cancelable to false
            progressDialog.setMessage("Please Wait"); // set message
            progressDialog.show();

            Api.baseTwo().sendPayment(MainActivity.orderId, new Callback<PaymentLink>() {
                @Override
                public void success(PaymentLink paymentLink, Response response) {
//                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Payment link is sent to your email and Mobile", Toast.LENGTH_LONG).show();
//                    FragmentManager ft = getActivity().getSupportFragmentManager();
//                    ft.beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment(), "NewFragmentTag")
//                            .addToBackStack(null)
//                            .commit();
                }

                @Override
                public void failure(RetrofitError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    private void sendPaymentLink () {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        Api.baseTwo().sendPayment(MainActivity.orderId, new Callback<PaymentLink>() {
            @Override
            public void success(PaymentLink paymentLink, Response response) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Payment link is sent to your email and Mobile", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updatePaymentStatus () {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        Api.baseTwo().cashReceived(MainActivity.orderId, "",new Callback() {
            @Override
            public void success(Object o, Response response) {
                progressDialog.dismiss();
                uploadImage();
            }

            @Override
            public void failure(RetrofitError error) {
                    progressDialog.dismiss();
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void disableCardView1() {
        cpCardView.setEnabled(false);
        cpCardView.setCardBackgroundColor(getResources().getColor(R.color.disableBtn));
        cachePayment1.setTextColor(getResources().getColor(R.color.drakGray));
        cachePayment11.setTextColor(getResources().getColor(R.color.drakGray));
        cachePayment10.setColorFilter(getResources().getColor(R.color.drakGray));
    }

    private void disableCardView2() {
        qrCardView.setEnabled(false);
        qrCardView.setCardBackgroundColor(getResources().getColor(R.color.disableBtn));
        cachePayment2.setTextColor(getResources().getColor(R.color.drakGray));
        cachePayment21.setTextColor(getResources().getColor(R.color.drakGray));
        cachePayment20.setColorFilter(getResources().getColor(R.color.drakGray));
    }

    private void disableCardView3() {
        spCardView.setEnabled(false);
        spCardView.setCardBackgroundColor(getResources().getColor(R.color.disableBtn));
        cachePayment3.setTextColor(getResources().getColor(R.color.drakGray));
        cachePayment31.setTextColor(getResources().getColor(R.color.drakGray));
        cachePayment30.setColorFilter(getResources().getColor(R.color.drakGray));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1337 && data != null && data.getExtras() != null) {

            if (payDialog != null) {
                payDialog.dismiss();
            }
            updatePaymentStatus();
            if (qrPayment) {
                enableMarkAsDone(2);
            } else {
                enableMarkAsDone(3);
            }
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            clicked_image_id.setImageBitmap(photo);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getActivity().getApplicationContext(), photo);


            finalFile = new File(getRealPathFromURI(tempUri));
        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void uploadImage () {

        SharedPreferences prefs = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String token = prefs.getString("token", null);
        String clientId = prefs.getString("clientId", null);

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        TypedFile typedFile = new TypedFile("multipart/form-data", finalFile);
        Api.baseThree().uploadFile(clientId, MainActivity.branch, MainActivity.orderIdentifier, typedFile, new Callback() {
            @Override
            public void success(Object o, Response response) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Uploaded Pic", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
//        Intent it = new Intent(getContext(), BaseActivity.class);
//        getContext().startActivity(it);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getActivity().getContentResolver() != null) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
//                        Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case 2: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
//                        Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Permission Denied to capture Pic", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
