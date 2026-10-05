package com.devst.guiadegimnasio;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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


    ActivityResultLauncher<Intent> LauncherConfirm = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result ->
            {
                if(result.getResultCode() == RESULT_OK)
                {
                    Toast.makeText(FormActivity.this, "Inscripción confirmada", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(FormActivity.this, "Inscripción cancelada", Toast.LENGTH_SHORT).show();
                }

            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Inscripción");

        tvGimnasioForm = findViewById(R.id.tvGimnasioForm);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        btnEnviar = findViewById(R.id.btnEnviar);

        String nombreGimnasio = getIntent().getStringExtra("nombreGimnasio");
        tvGimnasioForm.setText("Gimnasio: " + nombreGimnasio);

        btnEnviar.setOnClickListener(v ->
        {
            String Nombre = etNombre.getText().toString().trim();
            String Correo = etCorreo.getText().toString().trim();
            String Telefono = etTelefono.getText().toString().trim();

            if (Nombre.isEmpty())
            {
                etNombre.setError("Ingresa tu Nombre");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(Correo).matches())
            {
                etCorreo.setError("Correo no válido");
                return;
            }

            if (!Telefono.matches("\\d{9}"))
            {
                etTelefono.setError("Debe tener 9 dígitos");
                return;
            }

            Intent intent = new Intent(FormActivity.this, ConfirmActivity.class);
            intent.putExtra("gimnasio", nombreGimnasio);
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