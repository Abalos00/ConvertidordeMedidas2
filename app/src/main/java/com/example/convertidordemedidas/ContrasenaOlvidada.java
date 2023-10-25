package com.example.convertidordemedidas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContrasenaOlvidada extends AppCompatActivity {

    private EditText correoElectronicoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrasena_olvidada);

        correoElectronicoEditText = findViewById(R.id.editTextCorreoElectronico);

        Button enviarCorreoButton = findViewById(R.id.buttonEnviarCorreo);
        enviarCorreoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí puedes implementar la lógica para enviar un correo de recuperación
                enviarCorreoRecuperacion();
            }
        });
    }

    private void enviarCorreoRecuperacion() {
        // Aquí implementa la lógica para enviar un correo de recuperación
        // Puedes usar la dirección de correo electrónico ingresada en correoElectronicoEditText

        String correoElectronico = correoElectronicoEditText.getText().toString();

        // Validar la dirección de correo electrónico (puedes agregar validación adicional)

        if (!isValidEmail(correoElectronico)) {
            Toast.makeText(this, "Dirección de correo electrónico no válida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí debes enviar el correo de recuperación
        // Esto puede implicar el envío de un enlace temporal o un código de recuperación al correo especificado.

        // Después de enviar el correo, puedes mostrar un mensaje de éxito o redirigir al usuario.
        Toast.makeText(this, "Correo de recuperación enviado a " + correoElectronico, Toast.LENGTH_SHORT).show();
        finish(); // Cierra la actividad después de enviar el correo
    }

    private boolean isValidEmail(String email) {
        // Implementa la lógica para validar una dirección de correo electrónico
        // Puedes usar una expresión regular u otras validaciones según tus requisitos.
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
