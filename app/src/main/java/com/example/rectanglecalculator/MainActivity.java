package com.example.rectanglecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    //Variables within the view
    private EditText widthInputEditText;
    private EditText heightInputEditText;
    private TextView areaCalculationTextView;
    private TextView perimeterCalculationTextView;

    //Shared preferences object to save & restore
    private SharedPreferences savedValues;

    //Variables needed
    //These are string because the input is a EditText
    private String widthString = "";
    private String heightString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        widthInputEditText = (EditText)findViewById(R.id.widthEditText);
        heightInputEditText = (EditText)findViewById(R.id.heightEditText);
        areaCalculationTextView = (TextView)findViewById(R.id.areaCalculationTextView);
        perimeterCalculationTextView = (TextView)findViewById(R.id.perimeterCalculatedTextView);

        //Set listener
        //This is what calls onEditorAction
        //Basically what happens when you hit enter or whatever
        //Is specified with `actionId == EditorAction.IME_ACTION_...`
        heightInputEditText.setOnEditorActionListener(this);
        widthInputEditText.setOnEditorActionListener(this);

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
        /*
        IME_ACTION_DONE is used to trigger the calculation and display when the
        user presses the "Done" action on the keyboard. If you want to move
        to the next EditText field and trigger the calculation, you can use IME_ACTION_NEXT
         */
        if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
            calculateAndDisplay();
//            perimeterCalculationTextView.setText("23");
//            areaCalculationTextView.setText("12");
        }
        return false;
    }


    public void calculateAndDisplay(){
        float widthFloat; //Create variables to hold the strings
        float heightFloat;

        //Checking width from string.
        widthString = widthInputEditText.getText().toString();
        if(widthString.equals("")){
            widthFloat = 0; //Convert empty string to 0
        } else {
            widthFloat = Float.parseFloat(widthString);
        }

        //Now checking the height
        heightString = heightInputEditText.getText().toString();
        if(heightString.equals("")){
            heightFloat = 0;
        } else {
            heightFloat = Float.parseFloat(heightString);
        }

        //Now displaying area & perimeter
        float area = (widthFloat * heightFloat);
        float perimeter = (widthFloat * 2) + (heightFloat * 2);
        NumberFormat numberFormat = NumberFormat.getInstance();
        areaCalculationTextView.setText(numberFormat.format(area));
        perimeterCalculationTextView.setText(numberFormat.format(perimeter));

    }
}