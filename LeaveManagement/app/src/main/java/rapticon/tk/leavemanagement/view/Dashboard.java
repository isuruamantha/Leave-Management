package rapticon.tk.leavemanagement.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import rapticon.tk.leavemanagement.R;
import rapticon.tk.leavemanagement.helper.SharedPrefHelper;
import rapticon.tk.leavemanagement.model.BaseElement;


public class Dashboard extends Activity {

    private Activity mActivity;

    private Button applyButton;
    private Button viewRequestButton;
    private ArrayList<BaseElement> leaveList;

    private TextView remainigTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        defineLayout();
        initiliazeData();
        setListneres();
    }

    /**
     * To define all the layouts
     */
    public void defineLayout() {
        mActivity = this;

        applyButton = (Button) findViewById(R.id.applyLeave);
        viewRequestButton = (Button) findViewById(R.id.requestedLeaves);
        remainigTextView = (TextView) findViewById(R.id.remaining);
    }

    /**
     * To initiliaze Data
     */
    public void initiliazeData() {
        leaveList = SharedPrefHelper.getLeaveList();

        remainigTextView.setText(String.valueOf(leaveList.size()));

    }

    /**
     * To set the listneres
     */
    public void setListneres() {

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ApplyLeave.class);
                intent.putExtra("isEdit", false);
                startActivity(intent);
                finish();
            }
        });

        viewRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ViewRequestedLeave.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
