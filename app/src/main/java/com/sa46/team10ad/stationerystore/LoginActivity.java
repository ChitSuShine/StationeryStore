package com.sa46.team10ad.stationerystore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sa46.team10ad.model.AccessToken;
import com.sa46.team10ad.model.Login;
import com.sa46.team10ad.model.User;
import com.sa46.team10ad.service.UserClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {



    Retrofit.Builder builder = new Retrofit.Builder().
            baseUrl("http://lussisadteam10api.azurewebsites.net/api/").
            addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        findViewById(R.id.btn_secret).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSecret();
            }
        });
    }

    private static String token="bearer 1ANzozvaYtXuoXHQTNQuUpmtj1yNOhCTOJj4ZOkSD3icFs0Exakjx3cVMlwIAY3vFFpnqQLMga0-nnxioRf0HnjZfTFm7K5Kv2jtG3iQJMM7-Oa_G2OXhIQTO4MF_r-ZgziH6nqJGbhfyczsmEaAlhz5uedtCbQ-VrCBTiyFJgHFSYHJezgwYVW7PYbR9HWW91Fkjc0n0U5gW_h-hokCyQsHLj1qMexQhxon33FnYe-3KnO6lo3JosOs6DwHQkOSqbBx7Q_pVB2tHQB0j8EI8oLUfTxMSkPGY7_61m0kztGE-7-JJhVZR1DHva8Ya4tDya8oueM-VUEU34qtV8YuYQ";
    private void login()
    {
        Call<AccessToken> call = userClient.getAccessToken("admin","admin","password");
       call.enqueue(new Callback<AccessToken>() {
           @Override
           public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
               if(response.isSuccessful()) {
                   Toast.makeText(LoginActivity.this, response.body().getAccessToken().toString(), Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(LoginActivity.this, call.request().url().toString(), Toast.LENGTH_SHORT).show();
               }
               }

           @Override
           public void onFailure(Call<AccessToken> call, Throwable t) {
               Toast.makeText(LoginActivity.this, "No", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void getSecret()
    {
        Call<User> call = userClient.getSecret(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful())
                {
                    try
                    {
                        Toast.makeText(LoginActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Secret not correct.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "SECRET ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
