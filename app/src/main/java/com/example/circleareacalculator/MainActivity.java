package com.example.circleareacalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //This referenced data type help to round values into 2 decimal places
    private static final DecimalFormat twoDpFormat = new DecimalFormat("0.00");
    private EditText editTextCircleRadius;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To get EditText for Radius from xml
        editTextCircleRadius = (EditText) findViewById(R.id.editTextRadius);
        //To get Button Component from xml
        Button calcButton = (Button) findViewById(R.id.calculateAreaButton);
        //To create Alert dialog object
        builder = new AlertDialog.Builder(this);
        //Button click  event listener
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateArea();
            }
        });
    }

    //Event handler
    public void calculateArea() {
        try {
            if (editTextCircleRadius.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), R.string.radius_is_null_message, Toast.LENGTH_SHORT).show();
            } else {
                //To convert editText values to integers
                Double circleRadius = Double.parseDouble(editTextCircleRadius.getText().toString());

                //To calculate Circle area
                Double circleARea = (Math.PI * circleRadius * circleRadius);

                //Alert Dialog to display results
                builder.setMessage("Area of the circle is " + twoDpFormat.format(circleARea))
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                //Creating the dialog box
                AlertDialog alert = builder.create();
                alert.setTitle(R.string.dialog_title);
                alert.show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.exception_error, Toast.LENGTH_SHORT).show();
        }
    }
}
