package it.dindonkey.gameoflife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;

/**
 * Created by simone on 09/01/15.
 */
public class LifChooserActivity extends Activity
{

    private String[] items;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifchooser);

        items= getFileList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        ListView listView = (ListView) findViewById(R.id.lifListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent();
                intent.putExtra("fileName", items[position]);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    private String[] getFileList() {
        try
        {
            return getAssets().list("gol_patterns");
        } catch (IOException e)
        {
            return new String[]{};
        }
    }


}