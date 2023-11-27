package com.example.convertidordemedidas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConvertidorMedidas extends AppCompatActivity {

    private EditText editTextInput;
    private Spinner spinnerFromUnit;
    private Spinner spinnerToUnit;
    private TextView textViewResult;
    private Button buttonConvert;
    private Button btnBackToMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertidor_medidas);

        editTextInput = findViewById(R.id.editTextInput);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
        textViewResult = findViewById(R.id.textViewResult);
        buttonConvert = findViewById(R.id.buttonConvert);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);

        // Set up spinner with unit options
        String[] unitOptions = {"Centímetros", "Metros", "Kilómetros", "Pulgadas", "Pies"};
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitOptions);
        spinnerFromUnit.setAdapter(unitAdapter);
        spinnerToUnit.setAdapter(unitAdapter);

        // Set onClickListener for the Convert button
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input and selected units
                double inputValue = Double.parseDouble(editTextInput.getText().toString());
                String fromUnit = spinnerFromUnit.getSelectedItem().toString();
                String toUnit = spinnerToUnit.getSelectedItem().toString();

                // Perform conversion based on selected units
                double result = convertUnits(inputValue, fromUnit, toUnit);

                // Display result in textViewResult
                textViewResult.setText(String.format("%.2f %s es %.2f %s", inputValue, fromUnit, result, toUnit));
            }
        });

        Button btnBackToMain = findViewById(R.id.btnBackToMenu);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para volver al menú principal (MainActivity)
                Intent intent = new Intent(ConvertidorMedidas.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }


    // Método para realizar la conversión entre unidades
    private double convertUnits(double value, String fromUnit, String toUnit) {
        double result = 0;
        switch (fromUnit) {
            case "Centímetros":
                switch (toUnit) {
                    case "Centímetros":
                        result = value;
                        break;
                    case "Metros":
                        result = value / 100;
                        break;
                    case "Kilómetros":
                        result = value / 100000;
                        break;
                    case "Pulgadas":
                        result = value / 2.54;
                        break;
                    case "Pies":
                        result = value / 30.48;
                        break;
                }
                break;
            // Agrega más casos según sea necesario
        }
        return result;
    }
}
