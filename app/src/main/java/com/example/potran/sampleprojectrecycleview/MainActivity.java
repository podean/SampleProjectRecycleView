package com.example.potran.sampleprojectrecycleview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }

    public static class PlaceholderFragment extends Fragment {

        private static List<Movie> moviesList = new ArrayList<>();
        RecyclerView recyclerView;
        MoviesAdapter mAdapter;

        FirebaseDatabase database;
        DatabaseReference ref;

        public PlaceholderFragment() {

        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);

            // vertical RecyclerView keep movie_list_row.xml width to 'match_parent'
            // horizontal RecyclerView keep movie_list_row.xml width to 'wrap_content'
            RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(llm);
            recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            mAdapter = new MoviesAdapter(moviesList);
            recyclerView.setAdapter(mAdapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Movie movie = moviesList.get(position);
                    Toast.makeText(getContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

            prepareMovieData();

        }

        private void prepareMovieData() {

            database = FirebaseDatabase.getInstance();
            ref = database.getReference("Movie");


            ref.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Movie movie = ds.getValue(Movie.class);
                        assert movie != null;
                        String title = movie.getTitle();
                        String genre = movie.getGenre();
                        String year = movie.getYear();

                        Toast.makeText(getContext(), " " + movie.toString(), Toast.LENGTH_LONG).show();
                        moviesList.add(new Movie(title, genre, year));
                        mAdapter.notifyDataSetChanged();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Failed to read value
                    Log.w("LOG V", "Failed to read value.", error.toException());
                }
            });

        }
    }
}
