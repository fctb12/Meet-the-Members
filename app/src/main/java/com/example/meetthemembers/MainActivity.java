package com.example.meetthemembers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView homeImage = (ImageView) findViewById(R.id.homeImage);
        homeImage.setImageResource(R.drawable.mdb_transparentlogo);

        start = findViewById(R.id.startButton);
        start.setOnClickListener(this);

    }

    public void onClick(View v){
        Intent showInstructions = new Intent(this, Instructions.class);
        this.startActivity(showInstructions);

    }

}
