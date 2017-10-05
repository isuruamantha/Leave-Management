package rapticon.tk.leavemanagement.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
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
import rapticon.tk.leavemanagement.model.Leave;
import rapticon.tk.leavemanagement.service.LeaveService;
import rapticon.tk.leavemanagement.util.AdapterElement;


public class ViewRequestedLeave extends Activity {

    private Activity mActivity;

    private LazyAdapter mAdapter;
    private ListView mlistView;

    private AsyncTaskHelper asyncTaskHelper;
    private ArrayList<BaseElement> leaveList = new ArrayList<>();
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

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Leave leave = (Leave) leaveList.get(position);
                Intent intent = new Intent(mActivity, SelectedLeave.class);
                intent.putExtra("leaveObject", leave);
                startActivity(intent);
                finish();
            }
        });

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

                leaveList = LeaveService.getAllLeavesById(jsonObject, mActivity);

            }

            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute() {

                if (leaveList == null) {
                    leaveList = new ArrayList<BaseElement>();
                    noRequestsTextView.setVisibility(View.VISIBLE);
                }
                mAdapter = new LazyAdapter(leaveList, AdapterElement.LEAVE.getElement(), mActivity);
                mlistView.setAdapter(mAdapter);
            }
        });
        asyncTaskHelper.execute();
    }

}
