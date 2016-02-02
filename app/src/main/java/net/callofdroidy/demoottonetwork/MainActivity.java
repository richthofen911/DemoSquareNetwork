package net.callofdroidy.demoottonetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String API_URL = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create an instance of GitHub API interface
        GitHub gitHub = retrofit.create(GitHub.class);
        // create a call instance for looking up Retrofit Contributors
        Call<List<Contributor>> call = gitHub.contributors("square", "retrofit");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response) {
                for(Contributor contributor: response.body()){
                    Log.e("contributor", contributor.login + " :: " + contributor.contributions);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
