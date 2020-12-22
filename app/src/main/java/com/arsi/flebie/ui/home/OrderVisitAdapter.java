package com.arsi.flebie.ui.home;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arsi.flebie.BaseActivity;
import com.arsi.flebie.CollectAmount;
import com.arsi.flebie.LoginActivity;
import com.arsi.flebie.Main2Activity;
import com.arsi.flebie.MainActivity;
import com.arsi.flebie.R;
import com.arsi.flebie.VisitDetail;
import com.arsi.flebie.pojo.VisitMasterDto;
import com.arsi.flebie.pojo.VisitOrder;
import com.arsi.flebie.ui.send.SendFragment;

import java.util.List;

public class OrderVisitAdapter  extends RecyclerView.Adapter<OrderVisitAdapter.MyViewHolder> {

    private Context mContext;
    private List<VisitOrder> orderList;
    public int pos;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, count, dateTxt, timeTxt, addressTxt, costTxt, countTxt;
        public ImageView visitCount;
//        public Button callButton;
        public LinearLayout visitItem;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.temptext);
            dateTxt = (TextView) view.findViewById(R.id.dateTxt);
            timeTxt = (TextView) view.findViewById(R.id.timeTxt);
            addressTxt = (TextView) view.findViewById(R.id.addressTxt);
//            costTxt = (TextView) view.findViewById(R.id.costTxt);
            visitItem = (LinearLayout)view.findViewById(R.id.visitItem);
            visitItem.setOnClickListener(this);

            countTxt = (TextView) view.findViewById(R.id.countTxt);
//            countTxt.setOnClickListener(this);
//            visitCount =  (ImageView) view.findViewById(R.id.visitIcon);
//            visitCount.setOnClickListener(this);

//
//             =  (Button) view.findViewById(R.id.callBtn);
//            callButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == visitItem) {

                MainActivity.orderIdentifier = orderList.get( getLayoutPosition()).getVisitMasterDto().getOrderIdentifier();
                MainActivity.orderId = orderList.get( getLayoutPosition()).getVisitMasterDto().getOrderId();
                MainActivity.visitId = orderList.get( getLayoutPosition()).getVisitMasterDto().getOrderVisitId();
                MainActivity.branch = orderList.get(getLayoutPosition()).getBranch();
//                FragmentManager ft = ((AppCompatActivity)mContext).getSupportFragmentManager();
//                ft.beginTransaction().replace(R.id.container, new VisitDetail(), "NewFragmentTag")
//                        .addToBackStack(null)
//                        .commit();
                Intent it = new Intent(mContext, Main2Activity.class);
                mContext.startActivity(it);
            }
//            if(v == callButton) {
//
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:"+orderList.get( getLayoutPosition()).getOrderPhoneNumber()));
//                mContext.startActivity(intent);
////                Toast.makeText(mContext, "###"+orderList.get( getLayoutPosition()).toString(), Toast.LENGTH_SHORT).show();
//            }
        }
    }

    public OrderVisitAdapter(Context mContext, List<VisitOrder> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderVisitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_visit_item_new, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderVisitAdapter.MyViewHolder holder, int position) {
        VisitOrder vo = orderList.get(position);
        holder.title.setText(vo.getVisitMasterDto().getOrderIdentifier());

        String time = vo.getVisitMasterDto().getTimeslot().substring(0,2)+":"+vo.getVisitMasterDto().getTimeslot().substring(2) + "AM";
        String dateTime = vo.getVisitMasterDto().getScheduledDate();
        holder.dateTxt.setText(dateTime);
        holder.timeTxt.setText(time);

        holder.addressTxt.setText(vo.getVisitMasterDto().getAddress());
//        holder.costTxt.setText("\u20B9"+vo.getOrderPrice());
        holder.countTxt.setText(vo.getVisitMasterDto().getNumberOfPatients()+"");
        if (vo.getVisitStatus().equals("ASSIGNED")) {
            holder.visitItem.setClickable(true);
        } else {
            holder.visitItem.setBackgroundColor(Color.rgb(194,194,194));
//            holder.visitItem.setClickable(false);
        }

        pos = position;
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
