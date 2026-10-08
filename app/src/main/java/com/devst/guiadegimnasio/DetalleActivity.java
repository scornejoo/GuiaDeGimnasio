package com.devst.guiadegimnasio;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {

    // Declaración de variables
    TextView tvNombre;
    TextView tvDescripcion;
    TextView tvHorario;
    TextView tvDireccion;
    TextView tvTelefono;
    TextView tvCorreo;
    TextView tvWeb;

    Button btnMapa;
    Button btnWeb;
    Button btnLlamar;
    Button btnCorreo;
    Button btnInscribirme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // Conexión con los layouts
        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvHorario = findViewById(R.id.tvHorario);
        tvDireccion = findViewById(R.id.tvDireccion);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvWeb = findViewById(R.id.tvWeb);

        btnMapa = findViewById(R.id.btnMapa);
        btnWeb = findViewById(R.id.btnWeb);
        btnLlamar = findViewById(R.id.btnLlamar);
        btnCorreo = findViewById(R.id.btnCorreo);
        btnInscribirme = findViewById(R.id.btnInscribirme);

        // Definir texto en cada sección según el Intent seguro
        int numero = getIntent().getIntExtra("gimnasio", -1);

        if (numero == 1)
        {
            tvNombre.setText(getString(R.string.gym1_nombre));
            tvDescripcion.setText(getString(R.string.gym1_desc));
            tvHorario.setText(getString(R.string.gym1_horario));
            tvDireccion.setText(getString(R.string.gym1_direccion));
            tvTelefono.setText("+56 9 1111 2233");
            tvCorreo.setText("gimnasio@powerfit.cl");
            tvWeb.setText("https://www.powerfit.cl");
        }
        else if (numero == 2)
        {
            tvNombre.setText(getString(R.string.gym2_nombre));
            tvDescripcion.setText(getString(R.string.gym2_desc));
            tvHorario.setText(getString(R.string.gym2_horario));
            tvDireccion.setText(getString(R.string.gym2_direccion));
            tvTelefono.setText("+56 9 4444 5555");
            tvCorreo.setText("gimnasio@izonegym.cl");
            tvWeb.setText("https://www.izonegym.cl");
        }
        else if (numero == 3)
        {
            tvNombre.setText(getString(R.string.gym3_nombre));
            tvDescripcion.setText(getString(R.string.gym3_desc));
            tvHorario.setText(getString(R.string.gym3_horario));
            tvDireccion.setText(getString(R.string.gym3_direccion));
            tvTelefono.setText("+56 9 9999 4466");
            tvCorreo.setText("gimnasio@vida.cl");
            tvWeb.setText("https://www.vida.cl");
        }
        else
        {
            // CONTROL DE EXCEPCIÓN: Si los datos fallan, evitamos que la app rompa
            tvNombre.setText(getString(R.string.error_gimnasio_desconocido));
            tvDescripcion.setText(getString(R.string.error_sin_datos));
            tvHorario.setText("");
            tvDireccion.setText("");
            tvTelefono.setText("");
            tvCorreo.setText("");
            tvWeb.setText("");
            btnInscribirme.setEnabled(false);
        }

        // CORRECCIÓN: Eventos de clics limpiados con llamadas dinámicas a getString()
        btnMapa.setOnClickListener(v ->
        {
            String direccionCodificada = Uri.encode(tvDireccion.getText().toString().trim());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + direccionCodificada));
            try
            {
                startActivity(intent);
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(this, getString(R.string.toast_error_mapa), Toast.LENGTH_SHORT).show();
            }
        });

        btnWeb.setOnClickListener(v ->
        {
            String direccion = tvWeb.getText().toString().trim();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(direccion));
            try
            {
                startActivity(intent);
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(this, getString(R.string.toast_error_web), Toast.LENGTH_SHORT).show();
            }
        });

        btnLlamar.setOnClickListener(v ->
        {
            String telefono = tvTelefono.getText().toString().trim().replace(" ", "");
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telefono ));
            try
            {
                startActivity(intent);
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(this, getString(R.string.toast_error_llamada), Toast.LENGTH_SHORT).show();
            }
        });

        btnCorreo.setOnClickListener(v ->
        {
            String correo = tvCorreo.getText().toString().trim();
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + correo));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.correo_asunto));
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.correo_cuerpo));
            try
            {
                startActivity(intent);
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(this, getString(R.string.toast_error_correo), Toast.LENGTH_SHORT).show();
            }
        });

        btnInscribirme.setOnClickListener(v ->
        {
            String nombre = tvNombre.getText().toString().trim();
            Intent intent = new Intent(DetalleActivity.this, FormActivity.class);
            intent.putExtra("nombreGimnasio", nombre);
            startActivity(intent);
        });
    }
}
