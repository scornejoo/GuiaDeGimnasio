package com.devst.guiadegimnasio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    TextView tvGimnasioForm;
    EditText etNombre;
    EditText etCorreo;
    EditText etTelefono;
    Button btnEnviar;
    Toolbar toolbar;

    // CORRECCIÓN: Uso de getString() para limpiar advertencias en los Toast
    ActivityResultLauncher<Intent> LauncherConfirm = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result ->
            {
                if(result.getResultCode() == RESULT_OK)
                {
                    Toast.makeText(FormActivity.this, getString(R.string.toast_inscripcion_confirmada), Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    etCorreo.setText("");
                    etTelefono.setText("");
                    etNombre.requestFocus();
                }
                else
                {
                    Toast.makeText(FormActivity.this, getString(R.string.toast_inscripcion_cancelada), Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // CORRECCIÓN: Validación de Toolbar nulo y uso seguro de recursos string
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.titulo_inscripcion)); // Usa "Consulta de inscripción" o el recurso que prefieras
        }

        tvGimnasioForm = findViewById(R.id.tvGimnasioForm);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        btnEnviar = findViewById(R.id.btnEnviar);

        String nombreGimnasio = getIntent().getStringExtra("nombreGimnasio");

        // CORRECCIÓN: Validar nulo antes de concatenar y usar la plantilla de strings.xml si se prefiere
        String gimnasioEnvio = (nombreGimnasio != null) ? nombreGimnasio : getString(R.string.error_gimnasio_desconocido);
        tvGimnasioForm.setText(getString(R.string.confirm_gimnasio, gimnasioEnvio));

        btnEnviar.setOnClickListener(v ->
        {
            // 1. Limpiar errores visuales previos
            etNombre.setError(null);
            etCorreo.setError(null);
            etTelefono.setError(null);

            String Nombre = etNombre.getText().toString().trim();
            String Correo = etCorreo.getText().toString().trim().toLowerCase();
            String Telefono = etTelefono.getText().toString().trim();

            // 2. CORRECCIÓN: Validación de Nombre vacío con recurso string
            if (Nombre.isEmpty())
            {
                etNombre.setError(getString(R.string.error_nombre_vacio));
                etNombre.requestFocus();
                return;
            }

            if (!Nombre.matches("[\\p{L} ]{3,}"))
            {
                etNombre.setError(getString(R.string.error_nombre_invalido));
                etNombre.requestFocus();
                return;
            }

            // 3. CORRECCIÓN: Validación de Correo con recursos string
            if (Correo.isEmpty())
            {
                etCorreo.setError(getString(R.string.error_correo_vacio));
                etCorreo.requestFocus();
                return;
            }

            if (!Correo.matches("(?i)[A-Za-z0-9._%+-]+@gmail\\.com")) {
                etCorreo.setError(getString(R.string.error_correo_invalido_2));
                etCorreo.requestFocus();
                return;
            }

            // 4. CORRECCIÓN: Validación de Teléfono
            if (Telefono.isEmpty())
            {
                etTelefono.setError(getString(R.string.error_telefono_vacio));
                etTelefono.requestFocus();
                return;
            }

            if (!Telefono.matches("9\\d{8}"))
            {
                etTelefono.setError(getString(R.string.error_telefono_digitos));
                etTelefono.requestFocus();
                return;
            }

            Intent intent = new Intent(FormActivity.this, ConfirmActivity.class);
            intent.putExtra("gimnasio", gimnasioEnvio);
            intent.putExtra("nombre", Nombre);
            intent.putExtra("correo", Correo);
            intent.putExtra("telefono", Telefono);
            LauncherConfirm.launch(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }
}
