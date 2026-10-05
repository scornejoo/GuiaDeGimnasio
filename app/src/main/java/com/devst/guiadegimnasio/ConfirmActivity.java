package com.devst.guiadegimnasio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


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

        String gimnasio = getIntent().getStringExtra("gimnasio");
        String nombre = getIntent().getStringExtra("nombre");
        String correo = getIntent().getStringExtra("correo");
        String telefono = getIntent().getStringExtra("telefono");

        tvGimnasioConfirm.setText("Gimnasio: " + gimnasio);
        tvNombreConfirm.setText("Nombre: " + nombre);
        tvCorreoConfirm.setText("Correo: " + correo);
        tvTelefonoConfirm.setText("Telefono: " + telefono);

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