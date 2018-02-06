package io.branch.dividesectionlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.branch.dividesectionlist.Utils.MyItemDecoration;
import io.branch.dividesectionlist.Utils.MySpanSizeLookup;
import io.branch.dividesectionlist.Utils.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener{

    private RecyclerView myRecyclerView;
    private RecyclerViewAdapter myRecyclerViewAdapter;

    EditText nameField;
    Button btnAdd;
    private GridLayoutManager gridLayoutManagerVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = findViewById(R.id.myrecyclerview);

        gridLayoutManagerVertical =
                new GridLayoutManager(this,
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL,
                        false);
        //set SpanSizeLookup()
        gridLayoutManagerVertical.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 3 == 0 ? 2 : 1);
            }
        });

        myRecyclerViewAdapter = new RecyclerViewAdapter(this);
        myRecyclerViewAdapter.setOnItemClickListener(this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(gridLayoutManagerVertical);

        //Add MyItemDecoration
        myRecyclerView.addItemDecoration(new MyItemDecoration(this));
        //pre-load dummy items
        myRecyclerViewAdapter.add(0, "SpanSizeLookup");
        myRecyclerViewAdapter.add(0, "ItemDecoration");
        myRecyclerViewAdapter.add(0, "GridLayoutManager");
        myRecyclerViewAdapter.add(0, "LinearLayoutManager");
        myRecyclerViewAdapter.add(0, "RecyclerViewAdapter");
        myRecyclerViewAdapter.add(0, "RecyclerView example");
        myRecyclerViewAdapter.add(0, "RecyclerView");
        myRecyclerViewAdapter.add(0, "android-er.blogspot.com");
        myRecyclerViewAdapter.add(0, "android-er");
        myRecyclerViewAdapter.add(0, "android");

    }

    @Override
    public void onItemClick(RecyclerViewAdapter.ItemHolder item, int position) {
        Toast.makeText(this,
                "Remove " + position + " : " + item.getItemName(),
                Toast.LENGTH_SHORT).show();
        myRecyclerViewAdapter.remove(position);
    }
}