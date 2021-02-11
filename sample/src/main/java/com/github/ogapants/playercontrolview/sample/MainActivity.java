package com.github.ogapants.playercontrolview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ogapants.playercontrolview.PlayerControlView;
import com.github.ogapants.playercontrolview.sample.legacy.McExoPlayerActivity;
import com.github.ogapants.playercontrolview.sample.legacy.McMusicActivity;
import com.github.ogapants.playercontrolview.sample.legacy.McVideoViewActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Object> items = Arrays.asList(
            "##  " + PlayerControlView.class.getSimpleName() + " Samples ##",
            new Item(MusicActivity.class, "- MediaPlayer"),
            new Item(VideoViewActivity.class, "- VideoView"),
            new Item(ExoPlayerActivity.class, "- ExoPlayer"),
            new Item(CustomizedActivity.class, "- Customized"),

            "## " + MediaController.class.getSimpleName() + " Samples (legacy) ##",
            new Item(McMusicActivity.class, "- MediaPlayer"),
            new Item(McVideoViewActivity.class, "- VideoView"),
            new Item(McExoPlayerActivity.class, "- ExoPlayer")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = items.get(position);
                if (item instanceof Item) {
                    startActivity(new Intent(MainActivity.this, ((Item) item).activityClass));
                }
            }
        });
    }

    private class Item {
        private Class<? extends Activity> activityClass;
        private String name;

        private Item(Class<? extends Activity> activityClass, String name) {
            this.activityClass = activityClass;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
