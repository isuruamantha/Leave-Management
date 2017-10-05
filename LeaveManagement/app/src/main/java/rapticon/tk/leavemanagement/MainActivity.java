package rapticon.tk.leavemanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import rapticon.tk.leavemanagement.helper.AsyncTaskHelper;
import rapticon.tk.leavemanagement.helper.DataHelper;
import rapticon.tk.leavemanagement.helper.SharedPrefHelper;
import rapticon.tk.leavemanagement.model.User;
import rapticon.tk.leavemanagement.service.LoginService;
import rapticon.tk.leavemanagement.view.Dashboard;
import rapticon.tk.leavemanagement.view.HrViewRequestedLeave;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Activity mActivity;

    private AsyncTaskHelper asyncTaskHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineLayout();
        initiliazeData();
        setListneres();
    }

    /**
     * To define all the layouts
     */
    public void defineLayout() {
        mActivity = this;
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
    }

    /**
     * To initiliaze Data
     */
    public void initiliazeData() {

        /*Dummy data*/
//        usernameEditText.setText("952822160v"); //Normal
//        passwordEditText.setText("12345");

//        usernameEditText.setText("941353140V");  //HR
//        passwordEditText.setText("12345");

        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(mActivity);
        DataHelper.insertDummyData();

    }

    /**
     * To set the listneres
     */
    public void setListneres() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEditText.getText().toString().equals("")) {
                    usernameEditText.setError("Username is required!");
                } else if (passwordEditText.getText().toString().equals("")) {
                    passwordEditText.setError("Password is required!");
                } else {

                    loginBackgroundService(usernameEditText.getText().toString(), passwordEditText.getText().toString());

                }
            }
        });
    }

    public void loginBackgroundService(final String userName, final String password) {
        asyncTaskHelper = new AsyncTaskHelper(mActivity);
        asyncTaskHelper.setOnBackgroundListener(new AsyncTaskHelper.OnBackgroundTaskListener() {
            @Override
            public void onBackground(String... strings) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", userName);
                jsonObject.put("password", password);

                user = LoginService.checkCredentials(jsonObject, mActivity);
            }

            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute() {

                if (user == null) {
                    Toast.makeText(mActivity, "Incorrect credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPrefHelper.setuser(user);

                    if (user.getHr()) {
                        Intent intent = new Intent(mActivity, HrViewRequestedLeave.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(mActivity, Dashboard.class);
                        startActivity(intent);
                        finish();
                    }

                }


            }
        });
        asyncTaskHelper.execute();
    }

}
