package com.example.convertidordemedidas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConvertidorMedidas extends AppCompatActivity {

    private EditText editTextValor;
    private Spinner spinnerUnidadesOrigen;
    private Spinner spinnerUnidadesDestino;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertidor_medidas);

        editTextValor = findViewById(R.id.editTextValor);
        spinnerUnidadesOrigen = findViewById(R.id.spinnerUnidadesOrigen);
        spinnerUnidadesDestino = findViewById(R.id.spinnerUnidadesDestino);
        textViewResultado = findViewById(R.id.textViewResultado);

        Button buttonConvertir = findViewById(R.id.buttonConvertir);

        // Configurar los adaptadores para los spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unidades_medida, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerUnidadesOrigen.setAdapter(adapter);
        spinnerUnidadesDestino.setAdapter(adapter);

        buttonConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertirUnidades();
            }
        });
    }

    private void convertirUnidades() {
        String unidadOrigen = spinnerUnidadesOrigen.getSelectedItem().toString();
        String unidadDestino = spinnerUnidadesDestino.getSelectedItem().toString();

        // Obtener el valor ingresado
        String valorStr = editTextValor.getText().toString();
        if (!valorStr.isEmpty()) {
            double valor = Double.parseDouble(valorStr);

            // Realizar la conversión entre unidades
            double resultado = realizarConversion(unidadOrigen, unidadDestino, valor);

            // Mostrar el resultado
            textViewResultado.setText(valor + " " + unidadOrigen + " = " + resultado + " " + unidadDestino);
        } else {
            textViewResultado.setText("Por favor, ingresa un valor válido.");
        }
    }

    private double realizarConversion(String unidadOrigen, String unidadDestino, double valor) {
        // Definir factores de conversión para diferentes unidades de longitud
        // Puedes agregar más unidades y factores según tus necesidades
        double factorConversion = 1.0;

        if (unidadOrigen.equals("Kilómetro") && unidadDestino.equals("Metro")) {
            factorConversion = 1000.0;
        } else if (unidadOrigen.equals("Kilómetro") && unidadDestino.equals("Centímetro")) {
            factorConversion = 100000.0;
        } else if (unidadOrigen.equals("Kilómetro") && unidadDestino.equals("Pulgada")) {
            factorConversion = 39370.1;
        } else if (unidadOrigen.equals("Kilómetro") && unidadDestino.equals("Pie")) {
            factorConversion = 3280.84;
        } else if (unidadOrigen.equals("Metro") && unidadDestino.equals("Kilómetro")) {
            factorConversion = 0.001;
        } else if (unidadOrigen.equals("Metro") && unidadDestino.equals("Centímetro")) {
            factorConversion = 100.0;
        } else if (unidadOrigen.equals("Metro") && unidadDestino.equals("Pulgada")) {
            factorConversion = 39.3701;
        } else if (unidadOrigen.equals("Metro") && unidadDestino.equals("Pie")) {
            factorConversion = 3.28084;
        } else if (unidadOrigen.equals("Centímetro") && unidadDestino.equals("Kilómetro")) {
            factorConversion = 0.00001;
        } else if (unidadOrigen.equals("Centímetro") && unidadDestino.equals("Metro")) {
            factorConversion = 0.01;
        } else if (unidadOrigen.equals("Centímetro") && unidadDestino.equals("Pulgada")) {
            factorConversion = 0.393701;
        } else if (unidadOrigen.equals("Centímetro") && unidadDestino.equals("Pie")) {
            factorConversion = 0.0328084;
        } else if (unidadOrigen.equals("Pulgada") && unidadDestino.equals("Kilómetro")) {
            factorConversion = 0.0000254;
        } else if (unidadOrigen.equals("Pulgada") && unidadDestino.equals("Metro")) {
            factorConversion = 0.0254;
        } else if (unidadOrigen.equals("Pulgada") && unidadDestino.equals("Centímetro")) {
            factorConversion = 2.54;
        } else if (unidadOrigen.equals("Pulgada") && unidadDestino.equals("Pie")) {
            factorConversion = 0.0833333;
        } else if (unidadOrigen.equals("Pie") && unidadDestino.equals("Kilómetro")) {
            factorConversion = 0.0003048;
        } else if (unidadOrigen.equals("Pie") && unidadDestino.equals("Metro")) {
            factorConversion = 0.3048;
        } else if (unidadOrigen.equals("Pie") && unidadDestino.equals("Centímetro")) {
            factorConversion = 30.48;
        } else if (unidadOrigen.equals("Pie") && unidadDestino.equals("Pulgada")) {
            factorConversion = 12.0;
        }

        return valor * factorConversion;
    }
}
