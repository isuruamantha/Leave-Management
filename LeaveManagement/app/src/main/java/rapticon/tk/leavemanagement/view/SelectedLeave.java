package rapticon.tk.leavemanagement.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import rapticon.tk.leavemanagement.R;
import rapticon.tk.leavemanagement.helper.AsyncTaskHelper;
import rapticon.tk.leavemanagement.model.Leave;
import rapticon.tk.leavemanagement.service.LeaveService;


public class SelectedLeave extends Activity {

    private Activity mActivity;

    private TextView reasonTextView;
    private TextView fromDateTextView;
    private TextView toDateTextView;
    private TextView statusTextView;

    private Leave leaveObject;

    private Button deleteButton;
    private Button editButton;
    private Button backButton;
    private AsyncTaskHelper asyncTaskHelper;

    private boolean isLeaveDeleted;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_leave);

        defineLayout();
        initiliazeData();
        setListneres();
    }

    /**
     * To define all the layouts
     */
    public void defineLayout() {
        mActivity = this;

        reasonTextView = (TextView) findViewById(R.id.reason);
        fromDateTextView = (TextView) findViewById(R.id.fromDate);
        toDateTextView = (TextView) findViewById(R.id.toDate);
        statusTextView = (TextView) findViewById(R.id.status);

        deleteButton = (Button) findViewById(R.id.delete);
        editButton = (Button) findViewById(R.id.edit);
        backButton = (Button) findViewById(R.id.back);


    }

    /**
     * To initiliaze Data
     */
    public void initiliazeData() {

        leaveObject = (Leave) getIntent().getSerializableExtra("leaveObject");
        reasonTextView.setText("Reason: " + leaveObject.getReason());
        fromDateTextView.setText("From: " + leaveObject.getStartDate());
        toDateTextView.setText("To: " + leaveObject.getEndDate());
        statusTextView.setText("Status: " + leaveObject.getStatus());

    }

    /**
     * To set the listneres
     */
    public void setListneres() {

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLeave();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ViewRequestedLeave.class);
                startActivity(intent);
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ApplyLeave.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("leaveObject_edit", leaveObject);
                startActivity(intent);
                finish();
            }
        });
    }

    public void deleteLeave() {
        asyncTaskHelper = new AsyncTaskHelper(mActivity);
        asyncTaskHelper.setOnBackgroundListener(new AsyncTaskHelper.OnBackgroundTaskListener() {
            @Override
            public void onBackground(String... strings) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("leave_id", leaveObject.getLeaveId());

                isLeaveDeleted = LeaveService.DeleteLeave(jsonObject, mActivity);

            }

            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute() {
                if (isLeaveDeleted) {
                    Toast.makeText(mActivity, "Leave is deleted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mActivity, ViewRequestedLeave.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(mActivity, "Leave deletion failed!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        asyncTaskHelper.execute();
    }

}
