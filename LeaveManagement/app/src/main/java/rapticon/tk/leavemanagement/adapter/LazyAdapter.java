package rapticon.tk.leavemanagement.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import rapticon.tk.leavemanagement.R;
import rapticon.tk.leavemanagement.helper.AsyncTaskHelper;
import rapticon.tk.leavemanagement.model.BaseElement;
import rapticon.tk.leavemanagement.model.Leave;
import rapticon.tk.leavemanagement.service.LeaveService;
import rapticon.tk.leavemanagement.util.AdapterElement;
import rapticon.tk.leavemanagement.view.HrViewRequestedLeave;

import static rapticon.tk.leavemanagement.R.id.employeeId;
import static rapticon.tk.leavemanagement.R.id.fromDate;
import static rapticon.tk.leavemanagement.R.id.reason;
import static rapticon.tk.leavemanagement.R.id.toDate;

public class LazyAdapter extends BaseAdapter {

    private ArrayList<BaseElement> items;
    private int type;
    private LayoutInflater inflater;
    private Activity mActivity;
    AsyncTaskHelper asyncTaskHelper;
    Boolean isLeaveUpdated;

    public LazyAdapter(ArrayList<BaseElement> items, int type, Activity activity) {
        super();
        this.items = items;
        this.type = type;
        this.mActivity = activity;

        this.inflater = (LayoutInflater) this.mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = null;

        if (type == AdapterElement.LEAVE.getElement()) {
            view = inflater.inflate(R.layout.common_leave_item, parent, false);
            TextView reasonTextView = (TextView) view.findViewById(reason);
            TextView fromDateTextView = (TextView) view.findViewById(fromDate);
            TextView toDateTextView = (TextView) view.findViewById(toDate);
            TextView statusTextView = (TextView) view.findViewById(R.id.status);

            Leave leave = (Leave) items.get(position);
            reasonTextView.setText("Reason: " + leave.getReason());
            fromDateTextView.setText("From: " + leave.getStartDate());
            toDateTextView.setText("To: " + leave.getEndDate());
            statusTextView.setText("Status: " + leave.getStatus());
        } else if (type == AdapterElement.ALL_LEAVE.getElement()) {
            view = inflater.inflate(R.layout.hr_common_leave_item, parent, false);
            TextView employeeIdTextView = (TextView) view.findViewById(employeeId);
            TextView reasonTextView = (TextView) view.findViewById(reason);
            TextView fromDateTextView = (TextView) view.findViewById(fromDate);
            TextView toDateTextView = (TextView) view.findViewById(toDate);
            TextView statusTextView = (TextView) view.findViewById(R.id.status);
            Button acceptButton = (Button) view.findViewById(R.id.accpet);
            Button rejectButton = (Button) view.findViewById(R.id.reject);

            final Leave leave = (Leave) items.get(position);
            employeeIdTextView.setText("Employee ID: " + leave.getEmpId());
            reasonTextView.setText("Reason: " + leave.getReason());
            fromDateTextView.setText("From: " + leave.getStartDate());
            toDateTextView.setText("To: " + leave.getEndDate());
            statusTextView.setText("Status: " + leave.getStatus());

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateLeave(leave, "Accepted");
                    notifyDataSetChanged();
                }
            });

            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateLeave(leave, "Rejected");
                    notifyDataSetChanged();
                }
            });
        }

        return view;
    }

    public void updateLeave(final Leave leave, final String status) {

        asyncTaskHelper = new AsyncTaskHelper(mActivity);
        asyncTaskHelper.setOnBackgroundListener(new AsyncTaskHelper.OnBackgroundTaskListener() {
            @Override
            public void onBackground(String... strings) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("leave_id", leave.getLeaveId());
                jsonObject.put("emp_id", leave.getEmpId());
                jsonObject.put("from_date", leave.getStartDate());
                jsonObject.put("to_date", leave.getEndDate());
                jsonObject.put("reason", leave.getReason());
                jsonObject.put("status", status);

                isLeaveUpdated = LeaveService.UpdateLeave(jsonObject, mActivity);

            }

            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute() {
                if (isLeaveUpdated) {
                    Toast.makeText(mActivity, "Leave is updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mActivity, "Leave updating failed!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(mActivity, HrViewRequestedLeave.class);
                mActivity.startActivity(intent);


            }
        });
        asyncTaskHelper.execute();
        notifyDataSetChanged();
    }
}
