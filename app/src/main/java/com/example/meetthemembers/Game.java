package com.example.meetthemembers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

public class Game extends AppCompatActivity implements View.OnClickListener, exitDialog.exitDialogListener {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button exitBtn;
    Button correctButton;
    ImageView memberImage;
    TextView timeLeft;
    TextView score;
    public ArrayList<Integer> pictures = new ArrayList<Integer>();
    public ArrayList<Integer> implementPictures = new ArrayList<Integer>();
    public ArrayList<String> imageNames = new ArrayList<String>();
    public ArrayList<String> nameList = new ArrayList<String>();
    public ArrayList<String> generateOtherNames = new ArrayList<String>();
    public int iteration = 0;
    public int talliedScore = 0;
    public long millisLeft;
    public CountDownTimer questionTimer = new CountDownTimer(5000, 1000) {

        public void onTick(long millisUntilFinished) {
            timeLeft.setText("0:0" + (int) Math.ceil((double) millisUntilFinished / 1000));
            millisLeft = millisUntilFinished;
        }
        public void onFinish() {
            new CountDownTimer(1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timeLeft.setText("0:00");
                    correctButton.setBackgroundResource(R.drawable.roundedbuttongreen);
                    disableChoices();
                }
                public void onFinish() {
                    correctButton.setBackgroundResource(R.drawable.roundedbuttondefault);
                    enableChoices();
                    memberImage.setImageResource(implementPictures.get(iteration));
                    generateNewAnswers();
                }
            }.start();
        }
    }.start();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Read text file
        try {
            InputStream is = getAssets().open("names.txt");
            Scanner scanner = new Scanner(is);
            int i = 0;
            while (scanner.hasNextLine()) {
                //Add names to ArrayList nameList.
                nameList.add(i, scanner.nextLine());
                i++;
            }
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Create ArrayList imageNames based on ArrayList nameList to access pictures.
        for (int i = 0; i < nameList.size(); i++) {
            imageNames.add(i, nameList.get(i).replaceAll("\\s", "").toLowerCase());
        }

        //Create ArrayList to access pictures in alphabetical order.
        for (int i = 0; i < imageNames.size(); i++) {
            String name = imageNames.get(i);
            pictures.add(getResources().getIdentifier(name, "drawable", getPackageName()));
        }

        implementPictures = pictures;


        //Let's shuffle the array to avoid repetition of images. Then, we can simply iterate through this array as the game progresses.
        Collections.shuffle(implementPictures);

        memberImage = (ImageView) findViewById(R.id.memberImage);

        memberImage.setImageResource(implementPictures.get(iteration));

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(this);
        score = findViewById(R.id.score);
        timeLeft = findViewById(R.id.timeLeft);
        memberImage.setOnClickListener(this);
        memberImage.setImageResource(implementPictures.get(iteration));
        generateNewAnswers();
    }


    public void generateNewAnswers() {
        generateOtherNames = new ArrayList<String>(nameList);

        int actualValue = 1;
        for (int i = 0; i < imageNames.size(); i++) {
            if (getResources().getIdentifier(imageNames.get(i), "drawable", getPackageName()) == implementPictures.get(iteration)) {
                actualValue = i;
                break;
            }
        }

        String memberName = nameList.get(actualValue);

        int rando = (int) (Math.random() * 4) + 1;
        String otherMember = "";

        if (rando == 1) {
            correctButton = btn1;
            btn1.setText(memberName);
            btn2.setText("bruh");

            generateOtherNames.remove(generateOtherNames.indexOf(memberName));
            String otherMember1 = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            btn2.setText(otherMember1);
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember1));
            String otherMember2 = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            btn3.setText(otherMember2);
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember2));
            String otherMember3 = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            btn4.setText(otherMember3);
        } else if (rando == 2) {
            correctButton = btn2;
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn1.setText(otherMember);
            btn2.setText(memberName);
            generateOtherNames.remove(generateOtherNames.indexOf(memberName));
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn3.setText(otherMember);
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn4.setText(otherMember);

        } else if (rando == 3) {
            correctButton = btn3;
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn1.setText(otherMember);
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn2.setText(otherMember);
            btn3.setText(memberName);
            generateOtherNames.remove(generateOtherNames.indexOf(memberName));
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn4.setText(otherMember);
        } else {
            correctButton = btn4;
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn1.setText(otherMember);
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn2.setText(otherMember);
            otherMember = generateOtherNames.get((int) (Math.random() * generateOtherNames.size()));
            generateOtherNames.remove(generateOtherNames.indexOf(otherMember));
            btn3.setText(otherMember);
            btn4.setText(memberName);
        }

        iteration++; //Let's move along in our randomized ArrayList of iterations.
        questionTimer.start();
        if(iteration==nameList.size()){
            iteration=0;
        }

    }

    public String returnContactName(int iteration){
        int actualValue = 1;
        for (int i = 0; i < imageNames.size(); i++) {
            if (getResources().getIdentifier(imageNames.get(i), "drawable", getPackageName()) == implementPictures.get(iteration-1)) {
                actualValue = i;
                break;
            }
        }

        String memberName = nameList.get(actualValue);
        return memberName;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1:
                if(correctButton==btn1){
                    talliedScore++;
                    score.setText(""+talliedScore);
                    new CountDownTimer(1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            btn1.setBackgroundResource(R.drawable.roundedbuttongreen);
                            disableChoices();
                            questionTimer.cancel();
                        }
                        public void onFinish() {
                            btn1.setBackgroundResource(R.drawable.roundedbuttondefault);
                            enableChoices();
                            memberImage.setImageResource(implementPictures.get(iteration));
                            generateNewAnswers();
                        }
                    }.start();
                }
                else{
                    new CountDownTimer(1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            btn1.setBackgroundResource(R.drawable.roundedbuttonred);
                            correctButton.setBackgroundResource(R.drawable.roundedbuttongreen);
                            disableChoices();
                            questionTimer.cancel();
                        }
                        public void onFinish() {
                            btn1.setBackgroundResource(R.drawable.roundedbuttondefault);
                            correctButton.setBackgroundResource(R.drawable.roundedbuttondefault);
                            enableChoices();
                            memberImage.setImageResource(implementPictures.get(iteration));
                            generateNewAnswers();
                        }
                    }.start();

                }
                break;

            case R.id.btn2:
                if(correctButton==btn2){
                    talliedScore++;
                    score.setText(""+talliedScore);
                    new CountDownTimer(1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            btn2.setBackgroundResource(R.drawable.roundedbuttongreen);
                            disableChoices();
                            questionTimer.cancel();
                        }
                        public void onFinish() {
                            btn2.setBackgroundResource(R.drawable.roundedbuttondefault);
                            enableChoices();
                            memberImage.setImageResource(implementPictures.get(iteration));
                            generateNewAnswers();
                        }
                    }.start();
                }
                else{
                    new CountDownTimer(1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            btn2.setBackgroundResource(R.drawable.roundedbuttonred);
                            correctButton.setBackgroundResource(R.drawable.roundedbuttongreen);
                            disableChoices();
                            questionTimer.cancel();
                        }
                        public void onFinish() {
                            btn2.setBackgroundResource(R.drawable.roundedbuttondefault);
                            correctButton.setBackgroundResource(R.drawable.roundedbuttondefault);
                            enableChoices();
                            memberImage.setImageResource(implementPictures.get(iteration));
                            generateNewAnswers();
                        }
                    }.start();

                }
                break;
            case R.id.btn3:
                if(correctButton==btn3){
                    talliedScore++;
                    score.setText(""+talliedScore);
                    new CountDownTimer(1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            btn3.setBackgroundResource(R.drawable.roundedbuttongreen);
                            disableChoices();
                            questionTimer.cancel();
                        }
                        public void onFinish() {
                            btn3.setBackgroundResource(R.drawable.roundedbuttondefault);
                            enableChoices();
                            memberImage.setImageResource(implementPictures.get(iteration));
                            generateNewAnswers();
                        }
                    }.start();
                }
                else{new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        btn3.setBackgroundResource(R.drawable.roundedbuttonred);
                        correctButton.setBackgroundResource(R.drawable.roundedbuttongreen);
                        disableChoices();
                        questionTimer.cancel();
                    }
                    public void onFinish() {
                        btn3.setBackgroundResource(R.drawable.roundedbuttondefault);
                        correctButton.setBackgroundResource(R.drawable.roundedbuttondefault);
                        enableChoices();
                        memberImage.setImageResource(implementPictures.get(iteration));
                        generateNewAnswers();
                    }
                }.start();
                }

                break;
            case R.id.btn4:
                if(correctButton==btn4){
                    talliedScore++;
                    score.setText(""+talliedScore);
                    new CountDownTimer(1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            btn4.setBackgroundResource(R.drawable.roundedbuttongreen);
                            disableChoices();
                            questionTimer.cancel();
                        }
                        public void onFinish() {
                            btn4.setBackgroundResource(R.drawable.roundedbuttondefault);
                            enableChoices();
                            memberImage.setImageResource(implementPictures.get(iteration));
                            generateNewAnswers();
                        }
                    }.start();
                }
                else{new CountDownTimer(1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        btn4.setBackgroundResource(R.drawable.roundedbuttonred);
                        correctButton.setBackgroundResource(R.drawable.roundedbuttongreen);
                        disableChoices();
                        questionTimer.cancel();
                    }
                    public void onFinish() {
                        btn4.setBackgroundResource(R.drawable.roundedbuttondefault);
                        correctButton.setBackgroundResource(R.drawable.roundedbuttondefault);
                        enableChoices();
                        memberImage.setImageResource(implementPictures.get(iteration));
                        generateNewAnswers();
                    }
                }.start();
                }
                break;
            case R.id.memberImage:

                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent
                        .putExtra(ContactsContract.Intents.Insert.NAME,returnContactName(iteration));
                startActivity(intent);
                questionTimer.cancel();
            case R.id.exitBtn:
                questionTimer.cancel();
                openDialog();
            default:
                break;
        }
    }

    public void disableChoices(){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
    }

    public void enableChoices(){
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
    }

    public void openDialog(){
        exitDialog dialog = new exitDialog();
        dialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void onYesClicked(){
        Intent exit = new Intent(this, MainActivity.class);
        this.startActivity(exit);
    }

    public void onNoClicked(){
        questionTimer.start();
    }

    public void onPause(){
        super.onPause();
        questionTimer.cancel();
    }

    public void onResume(){
        super.onResume();
        questionTimer.start();
    }
    }