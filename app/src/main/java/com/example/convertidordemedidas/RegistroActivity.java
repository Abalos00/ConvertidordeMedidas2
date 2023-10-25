package com.example.convertidordemedidas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextContrasena;
    private EditText editTextCorreo;
    private EditText editTextNumero;
    private EditText editTextNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextNumero = findViewById(R.id.editTextTelefono);
        editTextNombreUsuario = findViewById(R.id.editTextNombreUsuario);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(view -> {
            // Obtener los datos ingresados por el usuario
            String nombre = editTextNombre.getText().toString();
            String contrasena = editTextContrasena.getText().toString();
            String correo = editTextCorreo.getText().toString();
            String numero = editTextNumero.getText().toString();
            String nombreUsuario = editTextNombreUsuario.getText().toString();

            // Crear una instancia de Usuario y establecer los datos ingresados
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombreUsuario(nombreUsuario);
            nuevoUsuario.setContrasena(contrasena);
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setNumero(numero);
            nuevoUsuario.setNombre(nombre);

            // Crear una instancia de UsuarioDataSource y abrir la base de datos
            UsuarioDataSource dataSource = new UsuarioDataSource(RegistroActivity.this);
            dataSource.open();

            // Insertar el nuevo usuario en la base de datos
            long resultado = dataSource.insertUsuario(nuevoUsuario);

            // Cerrar la base de datos
            dataSource.close();

            // Verificar el resultado de la inserción
            if (resultado != -1) {
                // La inserción fue exitosa, mostrar un mensaje de éxito o realizar acciones adicionales
                Toast.makeText(RegistroActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                // Ocurrió un error durante la inserción, mostrar un mensaje de error
                Toast.makeText(RegistroActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
