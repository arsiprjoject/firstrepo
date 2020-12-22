package com.arsi.flebie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arsi.flebie.pojo.MyErrorMessage;
import com.arsi.flebie.pojo.Provider;
import com.arsi.flebie.pojo.SignUpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends Activity {

    Button loginBtn;
    SignUpResponse signUpResponsesData;
    EditText userName, password, clientName;
    String userNameStr, passwordStr, clientNameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName =  findViewById(R.id.userName);
        password =  findViewById(R.id.password);
        clientName = findViewById(R.id.clientId);
//
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String token = pref.getString("token", "");
        if (token.equals("")) {
            // no token so stay in login activity
        } else {
            Intent it = new Intent(LoginActivity.this, BaseActivity.class);
            startActivity(it);
            finish();
        }


        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameStr = userName.getText().toString();
                passwordStr = password.getText().toString();
                clientNameStr = clientName.getText().toString();

                if (validate()) {
                    makeLoginRequest();
                } else {
                    Toast.makeText(LoginActivity.this, "Please check the Credentials", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean validate() {

        if(userNameStr == null || userNameStr.length() <=0) {
            return false;
        }

        if(passwordStr == null || passwordStr.length() <=0) {
            return false;
        }
        return true;
    }
    private void makeLoginRequest() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("userNameStr",userNameStr);
        editor.putString("passwordStr",passwordStr);
        editor.putString("clientId",clientNameStr);
        editor.commit();
        Api.login(clientNameStr).login("flebie-frontend",
                userNameStr,
                passwordStr,
                "password", new Callback<SignUpResponse>() {
                    @Override
                    public void success(SignUpResponse signUpResponse, Response response) {
                        progressDialog.dismiss();
                        signUpResponsesData = signUpResponse;

                        getProviderDetail(signUpResponsesData.getAccess_token());

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("token", "Bearer "+signUpResponsesData.getAccess_token()); // Storing string
                        editor.putString("justToken", signUpResponsesData.getAccess_token()); // Storing string
                        editor.commit();


                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
//                        Gson gson = new Gson();
//                        MyErrorMessage message=gson.fromJson(error.getResponse().getReason(), MyErrorMessage.class);
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss(); //dismiss progress dialog

                    }
                });
    }

    private void getProviderDetail(String token){
//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show(); // show progress dialog
        Api.baseOne().getProvider(new Callback<Provider>() {
            @Override
            public void success(Provider provider, Response response) {
//                progressDialog.dismiss();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("providerDetailId", provider.getId()+""); // Storing string
                editor.commit();

                MainActivity.providerName = provider.getName();
                MainActivity.providerEmail = provider.getEmailAddress();
                Intent it = new Intent(LoginActivity.this, BaseActivity.class);
                startActivity(it);
                finish();
//                Toast.makeText(LoginActivity.this, "Provider Id "+provider.getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
//                Gson gson = new Gson();
//                MyErrorMessage message=gson.fromJson(error.getBody().toString(), MyErrorMessage.class);
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                progressDialog.dismiss(); //dismiss progress dialog
            }
        });
    }
}
