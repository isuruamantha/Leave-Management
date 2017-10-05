package rapticon.tk.leavemanagement.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import rapticon.tk.leavemanagement.MainActivity;
import rapticon.tk.leavemanagement.R;
import rapticon.tk.leavemanagement.adapter.LazyAdapter;
import rapticon.tk.leavemanagement.helper.AsyncTaskHelper;
import rapticon.tk.leavemanagement.helper.SharedPrefHelper;
import rapticon.tk.leavemanagement.model.BaseElement;
import rapticon.tk.leavemanagement.service.LeaveService;
import rapticon.tk.leavemanagement.util.AdapterElement;


public class HrViewRequestedLeave extends Activity {

    private Activity mActivity;

    private LazyAdapter mAdapter;
    private ListView mlistView;

    private AsyncTaskHelper asyncTaskHelper;
    private ArrayList<BaseElement> leaveList;
    private Button logoutButton;

    private TextView noRequestsTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_applied_leaves);

        defineLayout();
        initiliazeData();
        setListneres();
    }

    /**
     * To define all the layouts
     */
    public void defineLayout() {
        mActivity = this;

        mlistView = (ListView) findViewById(R.id.listView);
        logoutButton = (Button) findViewById(R.id.logout);
        noRequestsTextView = (TextView) findViewById(R.id.noRequest);

    }

    /**
     * To initiliaze Data
     */
    public void initiliazeData() {

        getAllLeaves();
    }

    /**
     * To set the listneres
     */
    public void setListneres() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getAllLeaves() {

        asyncTaskHelper = new AsyncTaskHelper(mActivity);
        asyncTaskHelper.setOnBackgroundListener(new AsyncTaskHelper.OnBackgroundTaskListener() {
            @Override
            public void onBackground(String... strings) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", SharedPrefHelper.getUser().getNic());

                leaveList = LeaveService.getAllLeaves(jsonObject, mActivity);

            }

            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute() {
                if (leaveList.size() == 0) {
                    leaveList = new ArrayList<BaseElement>();
                    noRequestsTextView.setVisibility(View.VISIBLE);
                }

                mAdapter = new LazyAdapter(leaveList, AdapterElement.ALL_LEAVE.getElement(), mActivity);
                mlistView.setAdapter(mAdapter);
            }
        });
        asyncTaskHelper.execute();
    }

}
