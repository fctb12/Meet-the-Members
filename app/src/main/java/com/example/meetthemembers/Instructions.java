package com.example.meetthemembers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

public class Instructions extends AppCompatActivity implements View.OnClickListener{

    Button beginGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        beginGame = findViewById(R.id.beginGame);
        beginGame.setOnClickListener(this);

    }

    public void onClick(View v){
        Intent startGame = new Intent(this, Game.class);
        this.startActivity(startGame);
    }
}
