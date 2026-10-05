package com.devst.guiadegimnasio;


import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class FormActivity extends AppCompatActivity {


    TextView tvTituloForm;
    TextView tvGimnasioForm;
    EditText etNombre;
    EditText etCorreo;
    EditText etTelefono;
    Button btnEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTituloForm = findViewById(R.id.tvTituloForm);
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

            else if (!Patterns.EMAIL_ADDRESS.matcher(Correo).matches())
            {
                etCorreo.setError("Correo no válido");
                return;
            }

            else if (!Telefono.matches("\\d{9}"))
            {
                etTelefono.setError("Debe tener 9 dígitos");
                return;
            }
            else
            {
                Toast.makeText(this, "Datos validos", Toast.LENGTH_SHORT).show();
            }


        });
    }


    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }
}