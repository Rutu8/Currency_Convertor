package com.task.currency_convertor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Second_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Button button = findViewById(R.id.click_here);

        button.setOnClickListener(view -> {
                Intent intent = new Intent(Second_Activity.this, MainActivity.class);
                startActivity(intent);
            });
        }
    }
