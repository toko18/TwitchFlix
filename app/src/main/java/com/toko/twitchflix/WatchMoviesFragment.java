package com.toko.twitchflix;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WatchMoviesFragment extends Fragment {

    ArrayList<String> movieNames = new ArrayList<>();
    ArrayList<String> movieIDs = new ArrayList<>();
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watch_movies, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        try {
            getMovies();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot connect the server!", e);
        }

        rootView = view.getRootView();

        GridView gridView;
        gridView = (GridView) rootView.findViewById(R.id.movie_gridViewMovies);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(getContext(), VideoPlayer.class);
                i.putExtra("movieID", movieIDs.get(position));
                getContext().startActivity(i);
            }
        });
    }

    public void getMovies() throws Exception {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground (Void ... params) {
                try {
                    String url = "http://" + Server.getIP() + ":" + Server.getPortDB() + "/tyger/allmovies";

                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("GET");

                    BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    String[] divs;

                    while ((line = input.readLine()) != null) {
                        divs = line.split("\\$");
                        movieIDs.add(divs[0]);
                        movieNames.add(divs[1]);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result){
                super.onPostExecute(result);

                GridView gridView;
                GridItemAdapter adapter;

                gridView = (GridView) rootView.findViewById(R.id.movie_gridViewMovies);

                adapter = new GridItemAdapter(getContext(), movieNames, movieIDs);

                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        }.execute();
    }
}
