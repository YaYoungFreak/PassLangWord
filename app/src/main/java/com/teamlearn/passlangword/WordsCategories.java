package com.teamlearn.passlangword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class WordsCategories extends AppCompatActivity {

    private ImageButton back_btn;
    private TextView category_title;
    private RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_categories);
        back_btn = findViewById(R.id.back_btn);

        category_title = findViewById(R.id.category_title);

        radioGroup = findViewById(R.id.radioGroup);
        SetCategory();
        LoadChecked();
    }

    public String changedCategory;
    private int RadioButtonID;
    public void SetCategory() {
        SharedPreferences pref;
        pref = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // получаем выбранный RadioButton
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                changedCategory = String.valueOf(radioButton.getText());
                RadioButtonID = radioButton.getId();
                editor.putString("Category", changedCategory);
                editor.putInt("RadioButtonID", RadioButtonID);
                editor.commit();
            }
        });
    }

    private int RecentChecked;
    public void LoadChecked(){
        SharedPreferences pref;
        pref = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        RecentChecked = pref.getInt("RadioButtonID", 0);
        if(RecentChecked > 0) {
            RadioButton rbtn = (RadioButton) radioGroup.findViewById(RecentChecked);
            rbtn.setChecked(true);
        }
    }
    public void onClickBack(View view){
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}