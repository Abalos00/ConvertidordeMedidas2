package com.example.convertidordemedidas;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.convertidordemedidas.Calculadora;

public class MenuActivity extends AppCompatActivity {
    private TextView textViewSaludo;
    private ImageView icon1ImageView;
    private ImageView icon2ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textViewSaludo = findViewById(R.id.textViewSaludo);
        icon1ImageView = findViewById(R.id.icon1);

        // Obtiene los datos del usuario que ha iniciado sesión correctamente
        Intent intent = getIntent();
        if (intent != null) {
            String usuario = intent.getStringExtra("usuario");

            if (usuario != null) {
                // Muestra un saludo personalizado al usuario
                String saludo = "¡Bienvenido, " + usuario + "!";
                textViewSaludo.setText(saludo);
            }
        }

        // Configura clics de ImageViews u otras acciones de menú según tu diseño XML
        icon1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calculadoraIntent = new Intent(MenuActivity.this, Calculadora.class);
                startActivity(calculadoraIntent);
            }

        });

        icon2ImageView = findViewById(R.id.icon2);

        icon2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convertidorIntent = new Intent(MenuActivity.this, ConvertidorMedidas.class);
                startActivity(convertidorIntent);
            }
        });


        // Configura otros ImageViews y acciones de menú aquí
    }
}
