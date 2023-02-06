package com.example.assingment_github_api;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.assingment_github_api.API.GitHubApi;
import com.example.assingment_github_api.Adapter.RepositoryAdapter;
import com.example.assingment_github_api.Model.Repository;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView text;
    RepositoryAdapter adapter;
    ArrayList<Repository> repositoryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text1);
        String username = getIntent().getStringExtra("username");
        text.setText("Welcome " + username + "!");


        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RepositoryAdapter(repositoryArrayList);
        recyclerView.setAdapter(adapter);

        getRepositories(username);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    private void getRepositories(String user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubApi service = retrofit.create(GitHubApi.class);

        Call<ArrayList<Repository>> call = service.getRepositories(user);
        call.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Repository> repositories = response.body();
                    adapter.setRepositories(repositories);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve repositories", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to retrieve repositories", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




