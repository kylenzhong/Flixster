package activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;
import com.example.flix.R;
import com.example.flix.adapters.RVAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import activities.models.Movie;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    RecyclerView rvMain;
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<>();
        rvMain = findViewById(R.id.rvMain);
        final RVAdapter rvAdapter = new RVAdapter(this, movies);
        rvMain.setAdapter(rvAdapter);
        rvMain.setLayoutManager(new LinearLayoutManager(this));


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d("Main Activity", "got information successfully");
                JSONObject jobject = json.jsonObject;
                try {
                    JSONArray jsonArray = jobject.getJSONArray("results");
                    movies.addAll(Movie.populateMovies(jsonArray));
                    rvAdapter.notifyDataSetChanged();
                    //Log.i("Main Activity", "Movie size: " + movies.size());
                } catch (JSONException e) {
                    Log.e("Main Activity","Ran into a json array exception", e);
                }
            }
            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d("Main Activity", "FAILED at api info retrieval");
            }
        });





    }
}