package cl.tofcompany.drivindesafio.activities;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import cl.tofcompany.drivindesafio.Adapter.MainAdapter;
import cl.tofcompany.drivindesafio.R;
import cl.tofcompany.drivindesafio.response.MainData;
import cl.tofcompany.drivindesafio.Retrofit.MainInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
// Initialize variable
    private  RecyclerView mRecyclerView;
    private SwipeRefreshLayout refreshLayout;
    ArrayList<String> dataArrayList = new ArrayList<String>();
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();

    }

    private void Init() {
        //Assing variable
        mRecyclerView = findViewById(R.id.recycler_view);
        refreshLayout = findViewById(R.id.swipelayout);
        //Initialize adapter
        mainAdapter = new MainAdapter(MainActivity.this,dataArrayList);
        //set layout manager
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //set Adapter
        mRecyclerView.setAdapter(mainAdapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onRefresh() {
                getData();

                refreshLayout.setRefreshing(false);


            }

        });
        getData();

    }

    private void getData() {
        //Initialize progress dialog
        ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.this);
        //set message on dialog
        mProgressDialog.setMessage("Loading...");
        //set non cancelable
        mProgressDialog.setCancelable(false);
        //show dialog
        mProgressDialog.show();
        //Initialize retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //create interface
        MainInterface mainInterface = retrofit.create(MainInterface.class);
        //Initialize call
        Call<MainData> stringCall = mainInterface.getResponse();
        stringCall.enqueue(new Callback<MainData>() {

            @Override
            public void onResponse(@NonNull Call<MainData> call, @NonNull Response<MainData> response) {
                //check condition
                if (response.isSuccessful() && response.body() !=null){
                    //when response is successful and not null
                    //Dismiss dialog
                    mProgressDialog.dismiss();
                    MainData data = response.body();
                    dataArrayList.clear();
                    for (String srt:data.getMessage()) {
                            dataArrayList.add(srt);
                            //Initialize adapter
                            mainAdapter = new MainAdapter(MainActivity.this,dataArrayList);
                            //set adapter
                            mRecyclerView.setAdapter(mainAdapter);

                    }


                }

            }

            @Override
            public void onFailure(@NonNull Call<MainData> call, @NonNull Throwable t) {

            }
        });
    }
}