package com.example.autclub;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.util.ArrayList;


public class Server {


    ProgressDialog progressDialog; //is responsible to show the login bar when the server request is being executed
    //creating constants
    static final int CONNECTION_TIME = 1000 * 15;//time in millisecond its basically a timeout
   // static final String SERVER_ADDRESS = "https://softwareteamproject.000webhostapp.com/";//https://www.000webhost.com/members/website/titled-swallow/database
    static final String SERVER_ADDRESS = "https://softwareteamproject.000webhostapp.com/";//https://www.000webhost.com/members/website/titled-swallow/database


    public Server(Context context) {
        progressDialog = new ProgressDialog(context);// to instantiate theh progress dialog
        //setting attributes of progressdialog
        progressDialog.setCancelable(false);//it will make sure that the user wont be able to cancel a progressdialog
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");


    }

    public void savingUserDetailsInServer(user user, getReturnedUser returnedUser) {
        progressDialog.show();

        new savingUserDetailsAsyncTask(user, returnedUser).execute();// to start async task

    }

    public void getUserDetailsInServer(user user, getReturnedUser returnedUser) {
        progressDialog.show();
        new getUserDetailsInServerAsyncTask(user, returnedUser).execute();


    }

    // in android background task is called async task
    public class savingUserDetailsAsyncTask extends AsyncTask<Void, Void, Void> {
        user user;
        getReturnedUser returnedUser;

        // constructor of async task
        public savingUserDetailsAsyncTask(user user, getReturnedUser returnedUser) {

            this.user = user;
            this.returnedUser = returnedUser;

        }

        @Override
        protected Void doInBackground(Void... params) {
            //  ArrayList
            ArrayList<NameValuePair> datatosave = new ArrayList<>();// NameValuePair contains the key and the data store in that key
            datatosave.add(new BasicNameValuePair("name", user.name));

            datatosave.add(new BasicNameValuePair("username", user.username));
            datatosave.add(new BasicNameValuePair("password", user.password));
            datatosave.add(new BasicNameValuePair("email", user.email));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIME);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIME);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Signup.php");


            try {
                post.setEntity(new UrlEncodedFormEntity(datatosave));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        // when async task is finished
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            returnedUser.done(null);


        }
    }

    // it will return a user
    public class getUserDetailsInServerAsyncTask extends AsyncTask<Void, Void, user> {
        user user;
        getReturnedUser returnedUser;

        // constructor of async task
        public getUserDetailsInServerAsyncTask(user user, getReturnedUser returnedUser) {

            this.user = user;
            this.returnedUser = returnedUser;

        }

        @Override
        protected user doInBackground(Void... params) {
            ArrayList<NameValuePair> datatosave = new ArrayList<>();// NameValuePair contains the key and the data store in that key

            datatosave.add(new BasicNameValuePair("username", user.username));
            datatosave.add(new BasicNameValuePair("password", user.password));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIME);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIME);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "UserDetails.php");

            user u = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(datatosave));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity httpEntity = httpResponse.getEntity();
                String result = EntityUtils.toString(httpEntity);
                JSONObject jsonObject = new JSONObject(result); // to read attributes in jason
//                if (jsonObject.length() == 0)// if there is no user
//                {
//                    return null;
//
//                } else {
//
//                    String name = jsonObject.getString("name");
//                    String email = jsonObject.getString("email");
//                    u = new user(name, user.username, user.password, email);
//
//                }
                    if(jsonObject.length()!=0){


                        Log.v("happened","2");
                        String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    u = new user(name, user.username, user.password, email);

                    }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return u;
        }

        @Override
        protected void onPostExecute(com.example.autclub.user u) {

            progressDialog.dismiss();
            returnedUser.done(u);
            super.onPostExecute(u);
        }
    }

}
