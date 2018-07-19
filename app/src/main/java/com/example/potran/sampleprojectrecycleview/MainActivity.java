package com.example.potran.sampleprojectrecycleview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Placeholder;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv;

//    private List<Movie> moviesList = new ArrayList<>();
//    private RecyclerView recyclerView;
//    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("messagesRR");
//
////        tv = (TextView)findViewById(R.id.textView);
//
//        ref.setValue("What's up homie");
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
//                Log.d("Firebase value: ", value);
//                tv.setText(value);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(MainActivity.this, "Failed to read values", Toast.LENGTH_SHORT).show();
//                Log.w("Tazzzzz: ", "Failed to read");
//            }
//        });

    }
    public static class PlaceholderFragment extends Fragment{

        private List<Movie> moviesList = new ArrayList<>();
        private RecyclerView recyclerView;
        private MoviesAdapter mAdapter;

        public PlaceholderFragment(){

        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
            

        }
    }
}
