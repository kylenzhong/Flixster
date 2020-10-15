package activities.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

String title;
String description;
String image;

    public Movie(JSONObject object) throws JSONException {
        this.title = object.getString("original_title");
        this.description = object.getString("overview");
        this.image = object.getString("poster_path");
    }

    public static List<Movie> populateMovies(JSONArray jarray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i<jarray.length(); i++) {
            Movie temp =new Movie(jarray.getJSONObject(i));
            movies.add(temp);
        }
        return movies;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return "https://image.tmdb.org/t/p/w342/" + image;
    }
}
