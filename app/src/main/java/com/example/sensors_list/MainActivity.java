package com.example.sensors_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        createPost();
     /*   Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                String content = "";
                for (Post post:posts){
                    content += "ID"+ post.getId() + "\n";
                    content += "UserID"+ post.getUserId() + "\n";
                    content += "Title"+ post.getTitle()+ "\n";
                    content += "Text"+ post.getText() + "\n";

                }
                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });*/

    }
    private void createPost(){
        Post post = new Post(23,"New Title","New Text Body");
        Call<Post> call = jsonPlaceHolderApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Code"+ response.code() + "\n";
                content += "ID"+ postResponse.getId() + "\n";
                content += "UserID"+ postResponse.getUserId() + "\n";
                content += "Title"+ postResponse.getTitle()+ "\n";
                content += "Text"+ postResponse.getText() + "\n";
                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}

