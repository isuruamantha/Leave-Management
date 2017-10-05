package rapticon.tk.leavemanagement.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import rapticon.tk.leavemanagement.R;
import rapticon.tk.leavemanagement.helper.AsyncTaskHelper;
import rapticon.tk.leavemanagement.helper.SharedPrefHelper;
import rapticon.tk.leavemanagement.model.Leave;
import rapticon.tk.leavemanagement.model.User;
import rapticon.tk.leavemanagement.service.LeaveService;

import static rapticon.tk.leavemanagement.R.id.reason;


public class ApplyLeave extends Activity {

    private Calendar myCalendar;
    private EditText starDateEditText;
    private EditText endDateEditText;
    private EditText reasonEditText;
    private DatePickerDialog.OnDateSetListener startDate;
    private DatePickerDialog.OnDateSetListener endDate;
    private Activity mActivity;

    private Button cancelButton;
    private Button applyButton;
    private AsyncTaskHelper asyncTaskHelper;
    private User user;

    Boolean isLeaveCreted = true;
    Boolean isLeaveUpdated = true;
    boolean isEdit = false;
    Leave leave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_leave);

        defineLayout();
        initiliazeData();
        setListneres();
    }

    /**
     * To define all the layouts
     */
    public void defineLayout() {
        mActivity = this;

        starDateEditText = (EditText) findViewById(R.id.startDate);
        endDateEditText = (EditText) findViewById(R.id.endDate);
        reasonEditText = (EditText) findViewById(reason);
        applyButton = (Button) findViewById(R.id.apply);
        cancelButton = (Button) findViewById(R.id.cancelButton);
    }

    /**
     * To initiliaze Data
     */
    public void initiliazeData() {
        myCalendar = Calendar.getInstance();

        if (getIntent().getExtras().getBoolean("isEdit")) {
            applyButton.setText("Edit");
            leave = (Leave) getIntent().getSerializableExtra("leaveObject_edit");
            starDateEditText.setText(leave.getStartDate());
            endDateEditText.setText(leave.getEndDate());
            reasonEditText.setText(leave.getReason());
        }
    }

    /**
     * To set the listneres
     */
    public void setListneres() {
        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(starDateEditText);
            }

        };

        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(endDateEditText);
            }

        };

        starDateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, startDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            }
        });

        endDateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, endDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (reasonEditText.getText().toString().equals("")) {
                    reasonEditText.setError("Reason is required!");
                } else {
                    if (applyButton.getText().toString().toLowerCase().equals("apply")) {
                        applyLeave(starDateEditText.getText().toString(), endDateEditText.getText().toString(),
                                reasonEditText.getText().toString());
                    } else {
                        updateLeave(starDateEditText.getText().toString(), endDateEditText.getText().toString(),
                                reasonEditText.getText().toString());
                    }
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /*Update the label*/
    private void updateLabel(EditText date) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(sdf.format(myCalendar.getTime()));
    }

    public void applyLeave(final String fromDate, final String toDate, final String reason) {

        user = SharedPrefHelper.getUser();
        asyncTaskHelper = new AsyncTaskHelper(mActivity);
        asyncTaskHelper.setOnBackgroundListener(new AsyncTaskHelper.OnBackgroundTaskListener() {
            @Override
            public void onBackground(String... strings) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("emp_id", user.getNic());
                jsonObject.put("from_date", fromDate);
                jsonObject.put("to_date", toDate);
                jsonObject.put("reason", reason);
                jsonObject.put("status", "Pending");

                isLeaveCreted = LeaveService.ApplyLeave(jsonObject, mActivity);

            }

            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute() {
                if (isLeaveCreted) {
                    Toast.makeText(mActivity, "Leave is created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mActivity, ViewRequestedLeave.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(mActivity, "Leave creation failed!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        asyncTaskHelper.execute();
    }

    public void updateLeave(final String fromDate, final String toDate, final String reason) {

        user = SharedPrefHelper.getUser();
        asyncTaskHelper = new AsyncTaskHelper(mActivity);
        asyncTaskHelper.setOnBackgroundListener(new AsyncTaskHelper.OnBackgroundTaskListener() {
            @Override
            public void onBackground(String... strings) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("leave_id", leave.getLeaveId());
                jsonObject.put("emp_id", user.getNic());
                jsonObject.put("from_date", fromDate);
                jsonObject.put("to_date", toDate);
                jsonObject.put("reason", reason);
                jsonObject.put("status", leave.getStatus());

                isLeaveUpdated = LeaveService.UpdateLeave(jsonObject, mActivity);

            }

            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute() {
                if (isLeaveUpdated) {
                    Toast.makeText(mActivity, "Leave is updated!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mActivity, ViewRequestedLeave.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(mActivity, "Leave updating failed!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        asyncTaskHelper.execute();
    }
}
