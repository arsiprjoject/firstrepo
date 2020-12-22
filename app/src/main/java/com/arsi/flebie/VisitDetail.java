package com.arsi.flebie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arsi.flebie.dialog.CollectionBottom;
import com.arsi.flebie.pojo.OrderItemDtos;
import com.arsi.flebie.pojo.OrderPatientDetailsDtos;
import com.arsi.flebie.pojo.PatientDetail;
import com.arsi.flebie.pojo.VisitListRequest;
import com.arsi.flebie.pojo.VisitOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class VisitDetail extends Fragment implements View.OnClickListener {

    Button continueBtn;
    ImageView visitBackArrow, backArrow;
    TestListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
//    HashMap<String, List<String>> listDataChild;
    TextView  patientName, patientId, patientAddress, comment, nextBtn, labName, collectionTxt;
    String lat ,lng, phoneNumber;
    List<OrderPatientDetailsDtos> patientList;
    Float itemPrice = 0f;
    int count =0;
    ListView listView;
    Main2Activity activity;
    TextView orderTitle, callTxt, directionTxt;

    public VisitDetail() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_visit_detail_new, container, false);
        getVisitDetails();
        activity = (Main2Activity) getActivity();
//        expListView =( ExpandableListView) root.findViewById(R.id.expandableListView);
        patientName = (TextView) root.findViewById(R.id.patientName);
        patientId = (TextView) root.findViewById(R.id.patientId);
        comment = (TextView) root.findViewById(R.id.comment);

        patientAddress = root.findViewById(R.id.patientAddress);
        patientAddress.setOnClickListener(this);

//        visitBackArrow = root.findViewById(R.id.visitBackArrow);
//        visitBackArrow.setOnClickListener(this);

        continueBtn = root.findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(this);

        nextBtn = (TextView)root.findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);
        nextBtn.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.buttonClr));

        labName = (TextView) root.findViewById(R.id.labName);
        listView = (ListView) root.findViewById(R.id.test_list);

        collectionTxt = (TextView) root.findViewById(R.id.collectionTxt);
        collectionTxt.setOnClickListener(this);

        backArrow = (ImageView) activity.toolbar.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(this);

        orderTitle = (TextView) activity.toolbar.findViewById(R.id.orderTitle);

        callTxt = (TextView) root.findViewById(R.id.callBtn);
        callTxt.setOnClickListener(this);

        directionTxt = (TextView) root.findViewById(R.id.directionBtn);
        directionTxt.setOnClickListener(this);
        return root;
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawablesRelative()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == nextBtn) {
            try  {
                if(count < patientList.size()) {
                    setPatientData(patientList.get(count));
                } else {
                    Toast.makeText(getContext(), "No more patient Found", Toast.LENGTH_SHORT).show();
                }
                if (count == patientList.size()) {
                    nextBtn.setClickable(false);
                    nextBtn.setTextColor(getResources().getColor(R.color.grayText));
                    nextBtn.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.grayText));
                }
            } catch (Exception e ) {
                e.printStackTrace();
                Log.e("Count size Exception" , e.getMessage());
            }

        }

        if (v == callTxt) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+phoneNumber));
            getContext().startActivity(intent);
        }
        if (v == collectionTxt) {
            CollectionBottom bottmDiag = new CollectionBottom();
            bottmDiag.show(getActivity().getSupportFragmentManager(), "ModalBottomSheet");
        }
        if (v == continueBtn) {
//            Float itemPrice = 0f;
//            for ( OrderItemDtos orderItem : patientList.get(count).getOrderItemDtos()){
//                itemPrice =itemPrice + Float.parseFloat(orderItem.getItemPrice());
//            }
//            MainActivity.amountTOCollect = itemPrice+"";
            sampleCollectedApi();
        }

        if(v == backArrow ) {
//            if(getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
//                getActivity().getSupportFragmentManager().popBackStackImmediate();
//            }
//            else{
                Intent it = new Intent(getContext(), BaseActivity.class);
                startActivity(it);
                getActivity().finish();
                //super.onBackPressed();
//            }
        }

        if(v== patientAddress) {
            if(patientAddress.getText().toString().toLowerCase() != "na") {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+lat+","+lng));
                startActivity(intent);
            }

        }

        if (v == directionTxt) {
            if(patientAddress.getText().toString().toLowerCase() != "na") {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+lat+","+lng));
                startActivity(intent);
            }
        }
    }

    public void sampleCollectedApi(){

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        Api.baseTwo().sampleCollection(
                MainActivity.orderId,
                MainActivity.visitId,
                MainActivity.patientId,"",
                new Callback() {
                    @Override
                    public void success(Object o, Response response) {
                        progressDialog.dismiss();
//                        Toast.makeText(getContext(), "Sample Collected", Toast.LENGTH_SHORT).show();

                        if(count == patientList.size()) {
                            count = 0;
                            FragmentManager ft = getActivity().getSupportFragmentManager();
                            ft.beginTransaction().replace(R.id.container2, new CollectAmount(), "NewFragmentTag")
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            setPatientData(patientList.get(count));
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("-----", error.getMessage());
                       progressDialog.dismiss();
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void prepareListData(OrderItemDtos[] testArray) {
        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
        List<String> top250 = new ArrayList<String>();
        top250.add("Collection instruction to be displayed");

        for (OrderItemDtos test : testArray) {
            if(test.getEnabled() == "true") {
                listDataHeader.add(test.getItemName());
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.test_item, listDataHeader);
        listView.setAdapter(adapter);

//        listAdapter = new TestListAdapter(getContext(), listDataHeader, listDataChild);
//        expListView.setAdapter(listAdapter);
    }

    private void setPatientData(OrderPatientDetailsDtos patientOrder){

        MainActivity.patientId = patientOrder.getId()+"";

//        for ( OrderItemDtos orderItem : patientOrder.getOrderItemDtos()){
//            itemPrice =itemPrice + Float.parseFloat(orderItem.getItemPrice());
//        }

//        MainActivity.amountTOCollect = itemPrice+"";
        phoneNumber = patientOrder.getPhoneNumber();
        patientName.setText(patientOrder.getName());
        patientId.setText(patientOrder.getId()+"");
        comment.setText(patientOrder.getComments());
        if (patientOrder.getOrderItemDtos().length > 0) {
            if (patientOrder.getPatientServiceInfo() == null) {
                CollectionBottom.collectionString = patientOrder.getOrderItemDtos()[0].getItemDetails();
            } else {
                CollectionBottom.collectionString = patientOrder.getOrderItemDtos()[count].getPatientServiceInfo();
            }

            labName.setText(patientOrder.getOrderItemDtos()[0].getItemProviderName());
        }
        prepareListData(patientOrder.getOrderItemDtos());
        count++;
    }

   public void getVisitDetails(){

       final ProgressDialog progressDialog = new ProgressDialog(getContext());
       progressDialog.setCancelable(false); // set cancelable to false
       progressDialog.setMessage("Please Wait"); // set message
       progressDialog.show(); // show progress dialog

       Api.baseTwo().getVisitList(
               MainActivity.orderId,
               MainActivity.visitId,
               new Callback<PatientDetail>() {
                   @Override
                   public void success(PatientDetail patientItem, Response response) {
                       lat = patientItem.getLattitude()+"";
                       lng = patientItem.getLongitude()+"";
                       patientAddress.setText(patientItem.getAddress());
                       MainActivity.orderStatus = patientItem.getOrderVisitStatus();
                       MainActivity.amountTOCollect = patientItem.getOrderPrice()+"";


                       if (!patientItem.getOrderVisitStatus().equals("ASSIGNED")) {
                           continueBtn.setEnabled(false);
                       }
                       patientList  = new ArrayList<OrderPatientDetailsDtos>();

                       for (OrderPatientDetailsDtos op :  patientItem.getOrderPatientDetailsDtos()) {
                           patientList.add(op);
                       }
                       if(patientList.size()>0) {
                           setPatientData(patientList.get(0));
                       } else {
                           Toast.makeText(getContext(), "No Patient record found", Toast.LENGTH_SHORT).show();
                       }


                       progressDialog.dismiss();
                   }

                   @Override
                   public void failure(RetrofitError error) {
                       Log.e("-----", error.getMessage());

                       progressDialog.dismiss();
                       Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                   }
               });
    }
}
