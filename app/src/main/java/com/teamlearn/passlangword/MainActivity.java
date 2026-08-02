package com.teamlearn.passlangword;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btn_check, btn_give, btn_copy;
    private ImageButton three_line_btn;
    private EditText user_translate;
    private TextView right_answer, ask_word, infodel;
    private MainDBCode mainDBCode;
    private CopyFile copyFile;
    public Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDBCode = new MainDBCode(this);
        copyFile = new CopyFile();


        btn_check = findViewById(R.id.btn_check);
        user_translate = findViewById(R.id.user_translate);
        ask_word = findViewById(R.id.ask_word);
        right_answer = findViewById(R.id.right_answer);
        btn_give = findViewById(R.id.btn_give);
        three_line_btn = findViewById(R.id.three_line_btn);

        infodel = findViewById(R.id.infodel);
        btn_copy = findViewById(R.id.btn_copy);

        copyFile.copyAssetToInternalStorage(this, "words.db",
                    "/data/data/com.teamlearn.passlangword/databases/words.db");

        /*
        SharedPreferences pref;

        pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int a = pref.getInt("DBcreated", 0);
        if (a == 0){
            copyFile.copyAssetToInternalStorage(this, "words.db",
                    "/data/data/com.teamlearn.passlangword/databases/words.db");
            editor.putInt("DBcreated", 1);
            editor.commit();
            }
        */

    }
    


    public void onClickCheck(View view) {
        String user_translate_code = user_translate.getText().toString();
        String right_answer_code = right_answer.getText().toString();

        if (user_translate_code.equals(right_answer_code)) {
            right_answer.setTextColor(getResources().getColor(R.color.right_answer));
        }
        else{
            Toast.makeText(getApplicationContext(), "Неверно!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickAnswer(View view){
        String user_translate_code = user_translate.getText().toString();
        String right_answer_code = right_answer.getText().toString();
        if (user_translate_code.equals(right_answer_code)) {
            right_answer.setTextColor(getColor(R.color.right_answer));
        }
        else {
            right_answer.setTextColor(getColor(R.color.wrong_answer));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainDBCode.OpenReadDB();
    }

    public void onClickThreeLine(View view){
        Intent intent = new Intent(MainActivity.this, WordsCategories.class);
        startActivity(intent);
    }
    public void onClickGive(View view){
    mainDBCode.ChangeRandomWord();
    infodel.setText(mainDBCode.randomnum);
    ask_word.setText(mainDBCode.rand_ask_word);
    right_answer.setText(mainDBCode.successful_answer);
    right_answer.setTextColor(getColor(R.color.transparent));

    /*  String pril = "com.teamlearn.passlangword";
        appChecker = new AppChecker(pril);
        appChecker.checkRunningApp();*/

    /*
    while (true) {
        if (Helper.isAppRunning(this, "com.google.android.youtube")) {
            Log.e(TAG, "App is Running!");
        } else {
            Log.e(TAG, "App is NOT Runnong");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

     */

    /*
//Вывод строки word из DB в TextView
infodel.setText("");
for (String title : mainDBCode.readingDB()){
    infodel.append(title);
    infodel.append(", ");
}
    */
    }

    public void onClickCopy(View view){
        //copyFile.copyAssetToInternalStorage(this, "words.db", "/data/data/com.teamlearn.passlangword/databases/words.db");
        /* //Находим количество строк в таблице(длину)
        mainDBCode.ChangeRandomWord();
        String a = String.valueOf(mainDBCode.rowCount);
        ask_word.setText(a);
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainDBCode.closeDB();
    }



}

