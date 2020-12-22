package com.arsi.flebie.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.arsi.flebie.Api;
import com.arsi.flebie.R;
import com.arsi.flebie.pojo.VisitListRequest;
import com.arsi.flebie.pojo.VisitMasterDto;
import com.arsi.flebie.pojo.VisitOrder;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeFragment extends Fragment  {
    ViewPager viewPager;
    TabLayout tabs;
    List<VisitOrder> assignedList, completedList;
    String providerId = "1";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        providerId = prefs.getString("providerDetailId", "7");
        // Setting ViewPager for each Tabs
         viewPager = (ViewPager) root.findViewById(R.id.view_pager);
        tabs = (TabLayout) root.findViewById(R.id.tabs);
        getVisitOrder();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void getVisitOrder(){

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String todayDate = formatter.format(date);

        Calendar calendar = Calendar.getInstance();
        // get a date to represent "today"
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        String tomorrowDate = formatter.format(tomorrow);
        Log.d("-------", todayDate+"------"+tomorrowDate);


        Api.baseTwo().getVisits(providerId,
//                new VisitListRequest("2020-10-21","2020-10-22"),
                new VisitListRequest(todayDate,tomorrowDate),
                new Callback<List<VisitOrder>>() {

                    @Override
                    public void success(List<VisitOrder> visitOrders, Response response) {
                        assignedList = new ArrayList<VisitOrder>();
                        completedList = new ArrayList<VisitOrder>();
                        for(VisitOrder vo : visitOrders) {
                            if(vo.getVisitStatus().equals("ASSIGNED")) {
                                assignedList.add(vo);
                            } else {
                                completedList.add(vo);
                            }
                        }

                        createData(viewPager);
                        tabs.setupWithViewPager(viewPager);
                        progressDialog.dismiss();
//                        Toast.makeText(getContext(), "--"+visitOrders.size(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("-----", error.getMessage());
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void createData(ViewPager viewPager) {

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager());
        adapter.addFragment(new PlaceholderFragment(assignedList), "Assigned");
        adapter.addFragment(new PlaceholderFragment(completedList), "Completed");
        viewPager.setAdapter(adapter);

    }

}