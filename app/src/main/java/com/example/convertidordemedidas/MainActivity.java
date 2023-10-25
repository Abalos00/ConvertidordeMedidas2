package com.example.convertidordemedidas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText editTextUsuario;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = editTextUsuario.getText().toString();
                String password = editTextPassword.getText().toString();

                UsuarioDataSource dataSource = new UsuarioDataSource(MainActivity.this);
                dataSource.open();

                boolean autenticado = dataSource.autenticaUsuario(usuario, password);

                if (autenticado) {
                    Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                    menuIntent.putExtra("usuario", usuario);
                    startActivity(menuIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }

                dataSource.close();

            }
        });

        TextView forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        TextView signUpTextView = findViewById(R.id.signUpTextView);

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotPasswordIntent = new Intent(MainActivity.this, ContrasenaOlvidada.class);
                startActivity(forgotPasswordIntent);
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(registrationIntent);
            }
        });
    }
}