package com.example.convertidordemedidas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculadora extends AppCompatActivity {

    private TextView textViewPantalla;
    private Button btnBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        textViewPantalla = findViewById(R.id.textViewPantalla);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);

        // Configurar clics de botones numéricos
        int[] numeros = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        for (int id : numeros) {
            Button boton = findViewById(id);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNumeroClick(boton.getText().toString());
                }
            });
        }

        // Configurar clics de operadores y otros botones
        int[] operadores = {R.id.buttonSuma, R.id.buttonResta, R.id.buttonMultiplicacion, R.id.buttonDivision, R.id.buttonPunto};
        for (int id : operadores) {
            Button boton = findViewById(id);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOperadorClick(boton.getText().toString());
                }
            });
        }

        // Configurar clic en el botón Igual
        Button botonIgual = findViewById(R.id.buttonIgual);
        botonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIgualClick();
            }
        });

        // Configurar clic en el botón Limpiar
        Button botonLimpiar = findViewById(R.id.buttonLimpiar);
        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLimpiarClick();
            }
        });

        Button botonParentesis = findViewById(R.id.buttonParentecis);
        botonParentesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el texto actual en pantalla
                String textoActual = textViewPantalla.getText().toString();

                // Verificar si el último carácter es un paréntesis abierto
                if (!textoActual.isEmpty() && textoActual.charAt(textoActual.length() - 1) == '(') {
                    // Si es un paréntesis abierto, añadir un paréntesis cerrado
                    textViewPantalla.append(")");
                } else {
                    // Si no es un paréntesis abierto, añadir un paréntesis abierto
                    textViewPantalla.append("(");
                }
            }
        });

        Button botonPorcentaje = findViewById(R.id.buttonPorcentaje);
        botonPorcentaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Añadir el símbolo de porcentaje al texto en pantalla
                textViewPantalla.append("%");
            }
        });

        Button botonBorrar = findViewById(R.id.buttonBorrar);
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar un solo carácter del texto en pantalla
                borrarCaracter();
            }
        });

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para volver al menú principal (MainActivity)
                Intent intent = new Intent(Calculadora.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        Button buttonConvertidor = findViewById(R.id.buttonConvertidor);
        buttonConvertidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para ir a la actividad ConvertidorMedidas
                Intent intent = new Intent(Calculadora.this, ConvertidorMedidas.class);
                startActivity(intent);
            }
        });
    }

    private void onNumeroClick(String numero) {
        // Añadir el número presionado al texto en pantalla
        textViewPantalla.append(numero);
    }

    private void onOperadorClick(String operador) {
        // Añadir el operador presionado al texto en pantalla
        textViewPantalla.append(operador);
    }

    private void onIgualClick() {
        try {
            // Obtener la expresión del texto en pantalla
            String expresion = textViewPantalla.getText().toString();

            // Reemplazar el símbolo de porcentaje con su equivalente en formato decimal
            expresion = expresion.replaceAll("%", "/100");

            // Evaluar la expresión
            Expression e = new ExpressionBuilder(expresion).build();
            double resultado = e.evaluate();

            // Mostrar el resultado en la pantalla
            textViewPantalla.setText(String.valueOf(resultado));
        } catch (Exception e) {
            // Manejar errores de expresión inválida
            textViewPantalla.setText("Error");
        }
    }

    private void onLimpiarClick() {
        // Limpiar el texto en pantalla
        textViewPantalla.setText("");
    }

    private void borrarCaracter() {
        // Obtener el texto actual en pantalla
        String textoActual = textViewPantalla.getText().toString();

        // Verificar si hay caracteres para borrar
        if (!textoActual.isEmpty()) {
            // Eliminar el último carácter del texto
            String nuevoTexto = textoActual.substring(0, textoActual.length() - 1);
            textViewPantalla.setText(nuevoTexto);
        }
    }
}
