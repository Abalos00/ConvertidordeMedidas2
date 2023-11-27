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
    private ImageView icon3ImageView;
    private ImageView icon4ImageView;
    private ImageView icon5ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textViewSaludo = findViewById(R.id.textViewSaludo);
        icon1ImageView = findViewById(R.id.icon1);
        icon2ImageView = findViewById(R.id.icon2);
        icon3ImageView = findViewById(R.id.icon3);
        icon4ImageView = findViewById(R.id.icon4);
        icon5ImageView = findViewById(R.id.icon5);

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

        icon2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convertidorIntent = new Intent(MenuActivity.this, NotiActivity.class);
                startActivity(convertidorIntent);
            }
        });

        icon3ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convertidorIntent = new Intent(MenuActivity.this, MapaActivity.class);
                startActivity(convertidorIntent);
            }
        });

        icon4ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convertidorIntent = new Intent(MenuActivity.this, ConvertidorMedidas.class);
                startActivity(convertidorIntent);
            }
        });

        icon5ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convertidorIntent = new Intent(MenuActivity.this, ChatActivity.class);
                startActivity(convertidorIntent);
            }
        });

    }
}
