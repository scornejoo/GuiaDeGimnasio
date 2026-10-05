package com.devst.guiadegimnasio;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }
}