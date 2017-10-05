package rapticon.tk.leavemanagement.helper;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionHelper {


    public static String post(JSONObject json, String url, Activity activity) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");


        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json.toString());
        wr.flush();
        wr.close();

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
//                savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
//                savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }
        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else if (responseCode == 417) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();
        }


    }

    public static String post(JSONArray json, String url, Activity activity) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");


        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json.toString());
        wr.flush();
        wr.close();

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
//                savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
//                savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }
        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else if (responseCode == 417) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();
        }


    }

    public static String put(JSONObject json, String url, Activity activity) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setDoOutput(true);
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");


        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json.toString());
        wr.flush();
        wr.close();

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
//                savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
//                savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }
        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        }
        if (responseCode == 417) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();
        }


    }


    public static String get(String url, Activity activity) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
//                savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
//                savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }

        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();

        }


    }


    public static String getGetService(String url, String authenticate, String accessToken, Context context) throws IOException, JSONException {

        if (url.contains(" ")) {
            url = url.replace(" ", "%20");
        }
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("jauthtoken", authenticate);
        con.setRequestProperty("jaccesstoken", accessToken);

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
                //  savePin(context);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
                //  savePin(context);

            } else {  // Throw IOException

                throw new IOException();
            }
        }

        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();

        }


    }

    public static String postWithToken(JSONObject json, String url, String authToken, String accessToken, Context context) throws IOException, NullPointerException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Jauthtoken", authToken);
        con.setRequestProperty("Jaccesstoken", accessToken);


        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json.toString());
        wr.flush();
        wr.close();

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
                //  savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
                //  savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }

        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();
        }


    }

    /*public static String getWithToken(String url, String authTken, String accessToken, Activity activity) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();


        //add request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("jauthtoken", authTken);
        con.setRequestProperty("jaccesstoken", accessToken);

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
                savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
                savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }

        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();

        }


    }*/

   /* public static String getRedirectRequest(String url, Activity activity) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
                savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
                savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }

        if (responseCode == 302) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();

        }


    }*/

    /*public static String postAccessToken(String url, String userName, String authenticateToken, String password, Activity activity) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("jauthtoken", authenticateToken);


        String urlParameters =
                "username=" + URLEncoder.encode(userName, "UTF-8") +
                        "&client_secret=" + URLEncoder.encode(password, "UTF-8") +
                        "&scope=" + URLEncoder.encode("request", "UTF-8") +
                        "&grant_type=" + URLEncoder.encode("authorization_code", "UTF-8") +
                        "&redirect_uri=" + URLEncoder.encode("auth/access", "UTF-8") +
                        "&code=" + URLEncoder.encode(authenticateToken, "UTF-8") +
                        "&client_id=" + URLEncoder.encode("Internet-Banking-Client", "UTF-8") +
                        "&jauthtoken=" + URLEncoder.encode(authenticateToken, "UTF-8");

        // Send post request

        con.setRequestProperty("Content-Length", "" +
                Integer.toString(urlParameters.getBytes().length));
        con.setRequestProperty("Content-Language", "en-US");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
                savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
                savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }

        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else if (responseCode == 417) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("code", "sessionIsAlreadyIn");
            return jsonObject.toString();

        } else if (responseCode == 500) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("code", "Invalid user credentials");
            return jsonObject.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();
        }


    }*/


    /*public static String loginPost(JSONObject json, String url, Activity activity) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();


        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json.toString());
        wr.flush();
        wr.close();

        int responseCode = 0;

        try {

            responseCode = con.getResponseCode();

        } catch (IOException ioEx) {

            if (ioEx.getMessage().contains("Network is unreachable")) {  // Throws NoInternetException

//                throw new NoInternetException("No Internet");

            } else if (ioEx.getMessage().contains("No valid pins found")) { // handle pin validation
                savePin(activity);

            } else if (ioEx.getMessage().contains("Could not validate certificate")) { // Pin Expire
                savePin(activity);

            } else {  // Throw IOException

                throw new IOException();
            }
        }

        if (responseCode == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", responseCode);
            jsonObject.put("message", "error");


            return jsonObject.toString();
        }


    }*/

    /*public static HttpURLConnection getConnection(Activity activity, String urlString) throws IOException, JSONException {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PreferenceKey.PREF.getKey(), activity.MODE_PRIVATE);

        String ping = sharedPreferences.getString(PreferenceKey.PIN.getKey(), "");

        if (ping == null || ping.equals("")) {
            ping = savePin(activity);
        } else {

            ping = DataHelper.decodeNew(ping);

        }

        String[] pins = new String[]{ping};

        URL url = new URL(urlString);

        URL obj = new URL(ServiceURL.PIN.getUrl());
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();// PinningHelper.getPinnedHttpURLConnection(context, pins, url);

        return connection;
    }*/

    /*public static HttpURLConnection getConnection(Context context, String urlString) throws IOException, JSONException {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PreferenceKey.PREF.getKey(), context.MODE_PRIVATE);

        String ping = sharedPreferences.getString(PreferenceKey.PIN.getKey(), "");

        if (ping == null || ping.equals("")) {
            ping = savePin(context);
        } else {

            try {
                ping = DataHelper.decryptPass(ping);
            } catch (GeneralSecurityException e) {

            }
        }

        try {
            ping = DataHelper.decryptPass(ping);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        String[] pins = new String[]{ping};

        URL url = new URL(urlString);
        URL obj = new URL(ServiceURL.PIN.getUrl());
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();// PinningHelper.getPinnedHttpURLConnection(context, pins, url);

        return connection;
    }*/

    /*private static String savePin(Activity activity) throws IOException, JSONException {

        URL obj = new URL(ServiceURL.PIN.getUrl());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();

        if (con.getResponseCode() == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            SharedPreferences sharedPreferences = activity.getSharedPreferences(PreferenceKey.PREF.getKey(), activity.MODE_PRIVATE);

            //  JSONObject jsonObject = new JSONObject(response.toString());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(PreferenceKey.PIN.getKey(), response.toString());
            editor.apply();
            editor.commit();

            try {
                return DataHelper.decryptPass(response.toString());
            } catch (GeneralSecurityException e) {
                return "error";
            }


        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", con.getResponseCode());
            jsonObject.put("message", "error");
            return jsonObject.toString();
        }


    }*/

    /*private static String savePin(Context context) throws IOException, JSONException {

        URL obj = new URL(ServiceURL.PIN.getUrl());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();

        if (con.getResponseCode() == 200) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            SharedPreferences sharedPreferences = context.getSharedPreferences(PreferenceKey.PREF.getKey(), context.MODE_PRIVATE);

            //  JSONObject jsonObject = new JSONObject(response.toString());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(PreferenceKey.PIN.getKey(), response.toString());
            editor.apply();
            editor.commit();

            try {
                return DataHelper.decryptPass(response.toString());
            } catch (GeneralSecurityException e) {
                return "error";
            }


        } else {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("response", con.getResponseCode());
            jsonObject.put("message", "error");
            return jsonObject.toString();
        }


    }*/
}
