package com.example.alcoholcaloriecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.AlcoholCalorieCalc.model.Beer;
import com.AlcoholCalorieCalc.model.Wine;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting all our references together
        // Type spinner
        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        spinnerType.setOnItemSelectedListener(this);
        String[] types = {"Beer", "Wine"};
        ArrayAdapter aaType = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        aaType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(aaType);

        // Size spinner
        Spinner spinnerSize = (Spinner) findViewById(R.id.spinnerSize);
        spinnerSize.setOnItemSelectedListener(this);
        String[] sizes = {"mL", "g"};
        ArrayAdapter aaSize = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sizes);
        aaSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(aaSize);

        // TextBoxes
        EditText inputABV = findViewById(R.id.inputABV);
        EditText inputSize = findViewById(R.id.inputSize);
        EditText inputSweetness = findViewById(R.id.inputSweetness);

        // Button behaviour
        Button button = (Button) findViewById(R.id.buttonKcal);
        button.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        // Get values from text fields
                        double abv = -1;
                        int size = -1;
                        Double sweetness = -1.0;
                        try {
                            abv = Double.parseDouble(String.valueOf(inputABV.getText()).toString());
                            size = Integer.parseInt(inputSize.getText().toString());
                            sweetness = Double.parseDouble(inputSweetness.getText().toString());
                        } catch (Exception e) {
                            // Invalid input toast message
                            Toast.makeText(getApplicationContext(), "Invalid field(s)", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Validate value ranges
                        if(abv < 0 || abv > 100){
                            // Invalid abv toast message
                            Toast.makeText(getApplicationContext(), "Invalid abv", Toast.LENGTH_SHORT).show();
                        }
                        if(size < 0){
                            // Invalid size toast message
                            Toast.makeText(getApplicationContext(), "Invalid size", Toast.LENGTH_SHORT).show();
                        }

                        // Input good, create drink object and get calories
                        int roundedCals = -1;
                        switch (spinnerType.getSelectedItem().toString().toUpperCase()) {
                            case "BEER":
                                Beer beer = new Beer(abv, size, sweetness);
                                roundedCals = (int) Math.ceil(beer.getCals());
                                break;
                            case "WINE":
                                Wine wine = new Wine(abv, size, sweetness);
                                roundedCals = (int) Math.ceil(wine.getCals());
                                break;
                        }

                        // Update calories
                        TextView textKcal = findViewById(R.id.textKcal);
                        String kcalDisplayString = roundedCals + " calories";
                        textKcal.setText(kcalDisplayString);
                        Toast.makeText(getApplicationContext(), "Updated cals", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        Spinner spinnerSize = (Spinner) findViewById(R.id.spinnerSize);

        // Get the selected items
        String selectedType = spinnerType.getSelectedItem().toString();
        String selectedSize = spinnerSize.getSelectedItem().toString();

        // Check which spinner triggered the event
        if (adapterView == spinnerType) {
            // If Type spinner triggered the event, update Size spinner
            if (selectedType.equals("Beer")) {
                spinnerSize.setSelection(0); // Set Size spinner to mL
            } else if (selectedType.equals("Wine")) {
                spinnerSize.setSelection(1); // Set Size spinner to g
            }
        } else if (adapterView == spinnerSize) {
            // If Size spinner triggered the event, update Type spinner
            if (selectedSize.equals("mL")) {
                spinnerType.setSelection(0); // Set Type spinner to Beer
            } else if (selectedSize.equals("g")) {
                spinnerType.setSelection(1); // Set Type spinner to Wine
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
