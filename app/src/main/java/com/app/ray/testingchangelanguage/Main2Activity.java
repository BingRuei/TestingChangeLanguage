package com.app.ray.testingchangelanguage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private static final int JAPANESE = 0,
            CHINESE_SIMPLE = 1,
            CHINESE_TRANDITIONAL = 2,
            ENGLISH = 3;
    private TextView txtText;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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

        settings = getSharedPreferences("Test", 0);

        txtText = (TextView) this.findViewById(R.id.txt_text);
        registerForContextMenu(txtText);

        init();

    }

    private void init(){
        setLanguage();
        setTheWord();
    }

    private void setLanguage() {
        String sLanguage = settings.getString("Language", "NONE");
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        // 应用用户选择语言
        switch (sLanguage) {
            case "JP":
                config.locale = Locale.JAPAN;
                break;
            case "CN":
                config.locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case "TW":
                config.locale = Locale.TRADITIONAL_CHINESE;
                break;
            case "EN":
                config.locale = Locale.ENGLISH;
                break;
            case "NONE":
            default:
                if (Locale.getDefault().equals(Locale.JAPAN) ||
                        Locale.getDefault().equals(Locale.SIMPLIFIED_CHINESE) ||
                        Locale.getDefault().equals(Locale.TRADITIONAL_CHINESE) ||
                        Locale.getDefault().equals(Locale.ENGLISH)) {
                    config.locale = Locale.getDefault();
                } else {
                    config.locale = Locale.ENGLISH;
                }
                break;
        }
        resources.updateConfiguration(config, dm);
    }

    private void setTheWord() {
        txtText.setText(getResources().getString(R.string.hi).toString());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // 第一個參數是groupId, 代表你是屬於那一個Group, 通常都會設定為Menu.NONE
        // 第二個參數代表你是Menu Item Id
        // 第三個參數是你放入Item的順序, 假如你都設為Menu.NONE代表你使用預設, 系統會幫你安排順序
        // 第四個參數就是你想要放入的標題
        menu.add(Menu.NONE, JAPANESE, Menu.NONE, "日本語");
        menu.add(Menu.NONE, CHINESE_SIMPLE, Menu.NONE, "简体中文");
        menu.add(Menu.NONE, CHINESE_TRANDITIONAL, Menu.NONE, "繁體中文");
        menu.add(Menu.NONE, ENGLISH, Menu.NONE, "English");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case JAPANESE:
                settings.edit().putString("Language", "JP").commit();
                break;
            case CHINESE_SIMPLE:
                settings.edit().putString("Language", "CN").commit();
                break;
            case CHINESE_TRANDITIONAL:
                settings.edit().putString("Language", "TW").commit();
                break;
            case ENGLISH:
                settings.edit().putString("Language", "EN").commit();
                break;
        }
        init();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //參數1:群組id, 參數2:itemId, 參數3:item順序, 參數4:item名稱
        menu.add(0, 0, 0, "Main3Activity");
        menu.add(0, 1, 0, "Test2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        //依據itemId來判斷使用者點選哪一個item
        switch (item.getItemId()) {
            case 0:
                intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
                return true;
            case 1:
//                intent = new Intent(MainActivity.this, Interval4Game.class);
//                startActivityForResult(intent, 20);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
