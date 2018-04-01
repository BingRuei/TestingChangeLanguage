package com.app.ray.testingchangelanguage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     * 實驗結果：
     * 1. 第一頁因為沒改語系，所以字串設置是跟著系統跑，
     * 如果我們新增的語言包的語言不是系統預設語言，那麼預設字串會為基本沒國籍的那個字串檔
     * 2. 第二頁是改語系頁面，當第二頁設置好組態檔之後，第三頁不用設定，
     * 語系就會照組態檔的語系設置
     * **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //參數1:群組id, 參數2:itemId, 參數3:item順序, 參數4:item名稱
        menu.add(0, 0, 0, "Main2Activity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        //依據itemId來判斷使用者點選哪一個item
        switch (item.getItemId()) {
            case 0:
                intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
