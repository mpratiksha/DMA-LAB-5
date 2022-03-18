package com.example.lifecycleandstatechallenge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final int ITEM_REQUEST = 1;
    private final TextView[] item = new TextView[10];
    private ArrayList<String> itemsList = new ArrayList<>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        item[0] = findViewById(R.id.itema);
        item[1] = findViewById(R.id.itemb);
        item[2] = findViewById(R.id.itemc);
        item[3] = findViewById(R.id.itemd);
        item[4] = findViewById(R.id.iteme);
        item[5] = findViewById(R.id.itemf);
        item[6] = findViewById(R.id.itemg);
        item[7] = findViewById(R.id.itemh);
        item[8] = findViewById(R.id.itemi);
        item[9] = findViewById(R.id.itemj);

        if (savedInstanceState != null) {
            itemsList = savedInstanceState.getStringArrayList("ItemsListStringArray");
            int i;
            if (itemsList != null && itemsList.size() > 0) {
                for (i = 0; i < itemsList.size(); i++) {
                    if (itemsList.size() > 10) {
                        Toast.makeText(MainActivity.this, "Cannot add more items to the list", Toast.LENGTH_LONG).show();
                        break;
                    }
                    item[i].setVisibility(View.VISIBLE);
                    item[i].setText(itemsList.get(i));
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (itemsList.size() != 0) {
            outState.putStringArrayList("ItemsListStringArray", itemsList);
        }
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, Secondactivity.class);
        startActivityForResult(intent, ITEM_REQUEST);
    }

    // method for when SecondActivity returns intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                String itemString = data.getStringExtra(Secondactivity.EXTRA_ITEMS);
                itemsList.add(itemString);
                int i;
                for (i = 0; i < itemsList.size(); i++) {
                    if (itemsList.size() > 10) {
                        Toast.makeText(MainActivity.this, "Cannot add more items to the list", Toast.LENGTH_LONG).show();
                        break;
                    }
                    item[i].setVisibility(View.VISIBLE);
                    item[i].setText(itemsList.get(i));
                }

            }
        }
    }
}