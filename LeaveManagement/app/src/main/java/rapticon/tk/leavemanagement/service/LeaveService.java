package rapticon.tk.leavemanagement.service;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import rapticon.tk.leavemanagement.helper.ConnectionHelper;
import rapticon.tk.leavemanagement.model.BaseElement;
import rapticon.tk.leavemanagement.model.Leave;
import rapticon.tk.leavemanagement.util.ServiceURL;

public class LeaveService {

    public static Boolean ApplyLeave(JSONObject jsonObject, Activity activity)
            throws JSONException, IOException {

        JSONArray jsonArray = new JSONArray(ConnectionHelper.post(jsonObject, ServiceURL.NEW_lEAVE.getUrl(), activity));
        JSONObject jsonObj = jsonArray.getJSONObject(0);

        if (jsonObj.getString("response").equals("success")) {
            return true;
        } else {
            return null;
        }
    }

    public static Boolean UpdateLeave(JSONObject jsonObject, Activity activity)
            throws JSONException, IOException {

        JSONArray jsonArray = new JSONArray(ConnectionHelper.post(jsonObject, ServiceURL.UPDATE_LEAVE.getUrl(), activity));
        JSONObject jsonObj = jsonArray.getJSONObject(0);

        if (jsonObj.getString("response").equals("success")) {
            return true;
        } else {
            return null;
        }
    }

    public static Boolean DeleteLeave(JSONObject jsonObject, Activity activity)
            throws JSONException, IOException {

        JSONArray jsonArray = new JSONArray(ConnectionHelper.post(jsonObject, ServiceURL.DELETE_LEAVE.getUrl(), activity));

        JSONObject jsonObj = jsonArray.getJSONObject(0);

        if (jsonObj.getString("response").equals("success")) {

            return true;
        } else {
            return null;
        }
    }

    public static ArrayList<BaseElement> getAllLeavesById(JSONObject jsonObject, Activity activity)
            throws JSONException, IOException {

        ArrayList<BaseElement> leaveList = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(ConnectionHelper.post(jsonObject, ServiceURL.GET_ALL_LEAVES_BY_ID.getUrl(), activity));

        JSONObject jsonObj = jsonArray.getJSONObject(0);

        if (jsonObj.getString("response").equals("success")) {

            for (int i = 0; i < jsonObj.getJSONArray("responseObj").length(); i++) {

                Leave leave = new Leave();
                leave.setStartDate(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("from_date"));
                leave.setEndDate(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("to_date"));
                leave.setReason(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("reason"));
                leave.setStatus(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("status"));
                leave.setLeaveId(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("leave_id"));
                leave.setEmpId(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("emp_id"));
                leaveList.add(leave);
            }

            return leaveList;
        } else {
            return null;
        }
    }

    public static ArrayList<BaseElement> getAllLeaves(JSONObject jsonObject, Activity activity)
            throws JSONException, IOException {

        ArrayList<BaseElement> leaveList = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(ConnectionHelper.post(jsonObject, ServiceURL.GET_ALL_LEAVES.getUrl(), activity));

        JSONObject jsonObj = jsonArray.getJSONObject(0);

        if (jsonObj.getString("response").equals("success")) {

            for (int i = 0; i < jsonObj.getJSONArray("responseObj").length(); i++) {

                Leave leave = new Leave();
                leave.setStartDate(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("from_date"));
                leave.setEndDate(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("to_date"));
                leave.setReason(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("reason"));
                leave.setStatus(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("status"));
                leave.setLeaveId(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("leave_id"));
                leave.setEmpId(jsonObj.getJSONArray("responseObj").getJSONObject(i).getString("emp_id"));
                if (leave.getStatus().toLowerCase().equals("pending")) {
                    leaveList.add(leave);
                }

            }

            return leaveList;
        } else {
            return null;
        }
    }
}
