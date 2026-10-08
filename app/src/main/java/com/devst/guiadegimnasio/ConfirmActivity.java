package com.devst.guiadegimnasio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class ConfirmActivity extends AppCompatActivity {

    TextView tvTituloConfirm;
    TextView tvGimnasioConfirm;
    TextView tvNombreConfirm;
    TextView tvCorreoConfirm;
    TextView tvTelefonoConfirm;
    Button btnConfirmar;
    Button btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        tvTituloConfirm = findViewById(R.id.tvTituloConfirm);
        tvGimnasioConfirm = findViewById(R.id.tvGimnasioConfirm);
        tvNombreConfirm = findViewById(R.id.tvNombreConfirm);
        tvCorreoConfirm = findViewById(R.id.tvCorreoConfirm);
        tvTelefonoConfirm = findViewById(R.id.tvTelefonoConfirm);
        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnCancelar = findViewById(R.id.btnCancelar);

        // Obtener los extras validando que el Intent no sea nulo
        Intent intentIn = getIntent();
        String gimnasio = "No especificado";
        String nombre = "No especificado";
        String correo = "No especificado";
        String telefono = "No especificado";

        if (intentIn != null)
        {
            if (intentIn.hasExtra("gimnasio")) gimnasio = intentIn.getStringExtra("gimnasio");
            if (intentIn.hasExtra("nombre")) nombre = intentIn.getStringExtra("nombre");
            if (intentIn.hasExtra("correo")) correo = intentIn.getStringExtra("correo");
            if (intentIn.hasExtra("telefono")) telefono = intentIn.getStringExtra("telefono");
        }

// Asignar los textos de forma segura
        tvGimnasioConfirm.setText(getString(R.string.confirm_gimnasio, gimnasio));
        tvNombreConfirm.setText(getString(R.string.confirm_nombre, nombre));
        tvCorreoConfirm.setText(getString(R.string.confirm_correo, correo));
        tvTelefonoConfirm.setText(getString(R.string.confirm_telefono, telefono));


        btnConfirmar.setOnClickListener(v ->
        {
            setResult(RESULT_OK);
            finish();

        });

        btnCancelar.setOnClickListener(v ->
        {
            setResult(RESULT_CANCELED);
            finish();

        });


    }
}