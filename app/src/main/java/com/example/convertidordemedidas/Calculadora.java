package com.example.convertidordemedidas;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Calculadora extends AppCompatActivity {

    private StringBuilder entrada = new StringBuilder();
    private double valor1 = Double.NaN;
    private double valor2;
    private char operador;

    private TextView pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        pantalla = findViewById(R.id.textViewPantalla);

        Button[] botonesNumericos = new Button[10];
        for (int i = 0; i < 10; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            botonesNumericos[i] = findViewById(id);
            botonesNumericos[i].setOnClickListener(view -> agregarDigito(((Button) view).getText().toString()));
        }

        Button botonPunto = findViewById(R.id.buttonPunto);
        botonPunto.setOnClickListener(view -> agregarPunto());

        Button botonSuma = findViewById(R.id.buttonSuma);
        botonSuma.setOnClickListener(view -> seleccionarOperador('+'));

        Button botonResta = findViewById(R.id.buttonResta);
        botonResta.setOnClickListener(view -> seleccionarOperador('-'));

        Button botonMultiplicacion = findViewById(R.id.buttonMultiplicacion);
        botonMultiplicacion.setOnClickListener(view -> seleccionarOperador('*'));

        Button botonDivision = findViewById(R.id.buttonDivision);
        botonDivision.setOnClickListener(view -> seleccionarOperador('/'));

        Button botonIgual = findViewById(R.id.buttonIgual);
        botonIgual.setOnClickListener(view -> calcularResultado());

        Button botonLimpiar = findViewById(R.id.buttonLimpiar);
        botonLimpiar.setOnClickListener(view -> reiniciarCalculadora());

        Button botonBorrar = findViewById(R.id.buttonBorrar);
        botonBorrar.setOnClickListener(view -> borrarUltimoDigito());

        Button botonConvertidor = findViewById(R.id.buttonConvertidor);
        botonConvertidor.setOnClickListener(view -> {
            Intent intent = new Intent(Calculadora.this, ConvertidorMedidas.class);
            startActivity(intent);
        });
    }

    private void agregarDigito(String digito) {
        entrada.append(digito);
        actualizarPantalla();
    }

    private void agregarPunto() {
        if (!entrada.toString().contains(".")) {
            entrada.append(".");
            actualizarPantalla();
        }
    }

    private void seleccionarOperador(char op) {
        if (!Double.isNaN(valor1)) {
            valor2 = Double.parseDouble(entrada.toString());
            calcularResultado();
            operador = op;
            entrada = new StringBuilder();
            entrada.append(formatResultado(valor1));
            entrada.append(operador);
            actualizarPantalla();
        } else {
            valor1 = Double.parseDouble(entrada.toString());
            operador = op;
            entrada = new StringBuilder();
            entrada.append(formatResultado(valor1));
            entrada.append(operador);
            actualizarPantalla();
        }
    }

    private void calcularResultado() {
        if (!Double.isNaN(valor1)) {
            valor2 = Double.parseDouble(entrada.toString());
            switch (operador) {
                case '+':
                    valor1 += valor2;
                    break;
                case '-':
                    valor1 -= valor2;
                    break;
                case '*':
                    valor1 *= valor2;
                    break;
                case '/':
                    if (valor2 != 0) {
                        valor1 /= valor2;
                    } else {
                        pantalla.setText(getString(R.string.error_message));
                        reiniciarCalculadora();
                        return;
                    }
                    break;
            }
            entrada = new StringBuilder(formatResultado(valor1));
            valor2 = Double.NaN;
            actualizarPantalla();
        }
    }

    private String formatResultado(double resultado) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##########");
        return decimalFormat.format(resultado);
    }

    private void reiniciarCalculadora() {
        entrada = new StringBuilder();
        valor1 = Double.NaN;
        valor2 = 0;
        operador = ' ';
        actualizarPantalla();
    }

    private void borrarUltimoDigito() {
        if (entrada.length() > 0) {
            entrada.deleteCharAt(entrada.length() - 1);
            actualizarPantalla();
        }
    }

    private void actualizarPantalla() {
        pantalla.setText(entrada.toString());
    }
}
