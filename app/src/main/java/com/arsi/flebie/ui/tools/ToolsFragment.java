package com.arsi.flebie.ui.tools;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.arsi.flebie.Api;
import com.arsi.flebie.CollectAmount;
import com.arsi.flebie.MainActivity;
import com.arsi.flebie.R;
import com.arsi.flebie.pojo.Avilability;
import com.arsi.flebie.pojo.AvilabilityRequest;
import com.arsi.flebie.pojo.VisitListRequest;
import com.arsi.flebie.pojo.VisitOrder;
import com.arsi.flebie.ui.home.HomeFragment;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ToolsFragment extends Fragment implements View.OnClickListener {

    private Button offDutyBtn, updateAvilBtn;
    private Button timeSlot1, timeSlot2, timeSlot3, timeSlot4, timeSlot5;
    private TextView dateText;
    private CalendarView monthView;
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
    int mMinute = calendar.get(Calendar.MINUTE);
    boolean clearAvialbility = false;
    String setDate = "", setFromTime = "", setToTime = "";
    String daysList[] = {null, "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static String monthList[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings_new, container, false);

        timeSlot1 = root.findViewById(R.id.timeSlot1);
        timeSlot1.setOnClickListener(this);

        timeSlot2 = root.findViewById(R.id.timeSlot2);
        timeSlot2.setOnClickListener(this);

        timeSlot3 = root.findViewById(R.id.timeSlot3);
        timeSlot3.setOnClickListener(this);

        timeSlot4 = root.findViewById(R.id.timeSlot4);
        timeSlot4.setOnClickListener(this);

        timeSlot5 = root.findViewById(R.id.timeSlot5);
        timeSlot5.setOnClickListener(this);

        offDutyBtn = root.findViewById(R.id.offDutyBtn);
        offDutyBtn.setOnClickListener(this);

        updateAvilBtn = root.findViewById(R.id.updateAviBtn);
        updateAvilBtn.setOnClickListener(this);

        dateText = root.findViewById(R.id.dateText);
        Date currentDate = new Date();
        String dayOfWeek = daysList[currentDate.getDay() + 1];
        String monthName = monthList[currentDate.getMonth()];
        dateText.setText(monthName + " " + dayOfMonth + ", " + dayOfWeek);
        month = month + 1;
        String monthStr = (month) + "";
        String dayStr = dayOfMonth + "";

        if (month < 10) {
            monthStr = "0" + (month);
        }

        if (dayOfMonth < 10) {
            dayStr = "0" + dayOfMonth;
        }
        setDate = year + "-" + monthStr + "-" + dayStr;

        monthView = root.findViewById(R.id.monthView);

        monthView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                String dayOfWeek = daysList[calendar.get(Calendar.DAY_OF_WEEK)];
                String monthName = monthList[calendar.get(Calendar.MONTH)];
                dateText.setText(monthName + " " + dayOfMonth + ", " + dayOfWeek);

                String monthStr = (month+1) + "";
                String dayStr = dayOfMonth + "";

                if (month < 10) {
                    monthStr = "0" + month;
                }

                if (dayOfMonth < 10) {
                    dayStr = "0" + dayOfMonth;
                }
                setDate = year + "-" + monthStr + "-" + dayStr;
                markAvilability();
            }
        });
//        dateText.setOnClickListener(this);

        markAvilability();
        return root;
    }

    public void resetBtnColor() {
        timeSlot1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.whiteClr));
        timeSlot1.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.buttonClr));

        timeSlot2.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.whiteClr));
        timeSlot2.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.buttonClr));

        timeSlot3.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.whiteClr));
        timeSlot3.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.buttonClr));

        timeSlot4.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.whiteClr));
        timeSlot4.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.buttonClr));

        timeSlot5.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.whiteClr));
        timeSlot5.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.buttonClr));

        offDutyBtn.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.whiteClr));
        offDutyBtn.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.redTxt));


        setFromTime = "";
        setToTime = "";
    }

    @Override
    public void onClick(View v) {
        if (v == timeSlot1) {
            setClickParms(timeSlot1, "0600", "0900");
            clearAvialbility = false;
        }
        if (v == timeSlot2) {
            setClickParms(timeSlot2, "0600", "1100");
            clearAvialbility = false;
        }
        if (v == timeSlot3) {
            setClickParms(timeSlot3, "0600", "1400");
            clearAvialbility = false;
        }
        if (v == timeSlot4) {
            setClickParms(timeSlot4, "0400", "1800");
            clearAvialbility = false;
        }
        if (v == timeSlot5) {
            setClickParms(timeSlot5, "0600", "1800");
            clearAvialbility = false;
        }

        if (v == updateAvilBtn) {

            if (clearAvialbility) {
                clearAvilability();
            } else {
                if (setDate == "" || setFromTime == "" || setToTime == "") {
                    Toast.makeText(getContext(), "Please select the Date and Time", Toast.LENGTH_SHORT).show();
                } else {
                    setAvilability();
                }
            }

        }
        if(v == offDutyBtn) {
            clearAvialbility = true;
            setClickParms(offDutyBtn, "", "");
//
//            FragmentManager ft = getActivity().getSupportFragmentManager();
//            ft.beginTransaction().replace(R.id.container, new HomeFragment(), "NewFragmentTag")
//                    .addToBackStack(null)
//                    .commit();
        }

    }


    private void setClickParms(Button btn, String fromTime, String toTime) {
        resetBtnColor();
        if (btn == offDutyBtn) {
            btn.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.redTxt));
            btn.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.whiteClr));
            setFromTime = fromTime;
            setToTime = toTime;
        } else {
            btn.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.buttonClr));
            btn.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.whiteClr));
            setFromTime = fromTime;
            setToTime = toTime;

        }

    }

    private void clearAvilability() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        SharedPreferences prefs = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String providerId = prefs.getString("providerDetailId", "");
        Api.baseOne().clearAvailablity(providerId, setDate, new Callback<Boolean>() {
            @Override
            public void success(Boolean aBoolean, Response response) {
                if (aBoolean) {
                    Toast.makeText(getContext(),"Cleared Availability" , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(),"Error in clearing Availability" , Toast.LENGTH_LONG).show();
                }
                resetBtnColor();
                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }
    private void markAvilability() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        SharedPreferences prefs = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String providerId = prefs.getString("providerDetailId", "");
        Api.baseOne().getAvailability( providerId, setDate, new Callback<Avilability>() {


            @Override
            public void success(Avilability ar, Response response) {
                Button selectBtn = timeSlot1;
                if (ar != null && ar.getDate() != null && ar.getStartTime() != null && ar.getEndTime() != null) {
                    if (ar.getDate().equals(setDate) && ar.getStartTime().equals("0600") && ar.getEndTime().equals("0900")) {
                        selectBtn = timeSlot1;
                    }
                    if (ar.getDate().equals(setDate) && ar.getStartTime().equals("0600") && ar.getEndTime().equals("1100")) {
                        selectBtn = timeSlot2;
                    }
                    if (ar.getDate().equals(setDate) && ar.getStartTime().equals("0600") && ar.getEndTime().equals("1400")) {
                        selectBtn = timeSlot3;
                    }
                    if (ar.getDate().equals(setDate) && ar.getStartTime().equals("0400") && ar.getEndTime().equals("1800")) {
                        selectBtn = timeSlot4;
                    }
                    if (ar.getDate().equals(setDate) && ar.getStartTime().equals("0600") && ar.getEndTime().equals("1800")) {
                        selectBtn = timeSlot5;
                    }
                    setClickParms(selectBtn, ar.getStartTime(), ar.getEndTime());
                } else {
                    resetBtnColor();
                }
                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void setAvilability() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        SharedPreferences prefs = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String providerId = prefs.getString("providerDetailId", "");

        AvilabilityRequest avr = new AvilabilityRequest(Long.parseLong(providerId), setDate, setFromTime, setToTime);
        Api.baseOne().setAvilability(avr,
                new Callback() {
                    @Override
                    public void success(Object o, Response response) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Updated Avilability", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss(); //dismiss progress dialog

                    }
                });
    }
}