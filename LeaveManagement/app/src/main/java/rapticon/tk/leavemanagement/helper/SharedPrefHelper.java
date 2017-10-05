package rapticon.tk.leavemanagement.helper;


import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import rapticon.tk.leavemanagement.model.BaseElement;
import rapticon.tk.leavemanagement.model.Leave;
import rapticon.tk.leavemanagement.model.User;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * *   *  Created by Isuru Amantha
 */

public class SharedPrefHelper {
    private static Gson gson;
    private static SharedPreferences sharedPreferences;

    public SharedPrefHelper(Activity mActivity) {
        sharedPreferences = getDefaultSharedPreferences(mActivity.getApplicationContext());
    }

    /**
     * To set saved leave list
     *
     * @param items
     */
    public static void setLeaveList(ArrayList<BaseElement> items) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        gson = new Gson();
        editor.putString(String.valueOf("leaveList"), gson.toJson(items));
        editor.commit();

    }

    /**
     * To return saved leave list
     *
     * @return cart items array
     */
    public static ArrayList<BaseElement> getLeaveList() {
        ArrayList<BaseElement> list = new ArrayList<>();

        if (!(sharedPreferences == null)) {
            String value = sharedPreferences.getString(String.valueOf("leaveList"), "");
            list = new Gson().fromJson(value, new TypeToken<ArrayList<Leave>>() {
            }.getType());
        }

        return list;
    }


    public static void setuser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gson = new Gson();
        editor.putString(String.valueOf("user"), gson.toJson(user));
        editor.commit();
    }

    public static User getUser() {

        User user = null;

        if (!(sharedPreferences == null)) {

            String value = sharedPreferences.getString(String.valueOf("user"), "");
            user = new Gson().fromJson(value, User.class);
        }

        return user;
    }
}
