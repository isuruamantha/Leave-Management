package rapticon.tk.leavemanagement.service;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import rapticon.tk.leavemanagement.helper.ConnectionHelper;
import rapticon.tk.leavemanagement.model.User;
import rapticon.tk.leavemanagement.util.ServiceURL;

public class LoginService {

    public static User checkCredentials(JSONObject jsonObject, Activity activity)
            throws JSONException, IOException {

        User user = new User();

        JSONArray jsonArray = new JSONArray(ConnectionHelper.post(jsonObject, ServiceURL.Login.getUrl(), activity));

        JSONObject jsonObj = jsonArray.getJSONObject(0);

        if (jsonObj.getString("response").equals("success")) {
            user.setNic(jsonObj.getJSONArray("responseObj").getJSONObject(0).getString("nic"));
            user.setFirstName(jsonObj.getJSONArray("responseObj").getJSONObject(0).getString("first_name"));
            user.setLastName(jsonObj.getJSONArray("responseObj").getJSONObject(0).getString("last_name"));
            user.setPhoneNumber(jsonObj.getJSONArray("responseObj").getJSONObject(0).getString("telephone"));
            user.setEmail(jsonObj.getJSONArray("responseObj").getJSONObject(0).getString("email"));
            user.setHr(jsonObj.getJSONArray("responseObj").getJSONObject(0).getBoolean("is_hr"));
            return user;
        } else {
            return null;
        }
    }
}
