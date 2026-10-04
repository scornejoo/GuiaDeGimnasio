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

//    Declaración de variables

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

//        Conexion con los layouts

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

//        Definir texto en cada sección

        int numero = getIntent().getIntExtra("gimnasio", 1);
        if (numero == 1)
        {
            tvNombre.setText("Power Fit Center");
            tvDescripcion.setText("Musculación y pesas libres");
            tvHorario.setText("Lunes - Viernes 06:00-22:00");
            tvDireccion.setText("Av. Providencia 1234, Santiago");
            tvTelefono.setText("+56 9 1111 2233");
            tvCorreo.setText("gimnasio@powerfit.cl");
            tvWeb.setText("https://www.powerfit.cl");
        }
        else if (numero == 2)
        {
            tvNombre.setText("Iron Zone Gym");
            tvDescripcion.setText("Crossfit y entrenamiento funcional");
            tvHorario.setText("Lunes - Viernes 06:00-22:00");
            tvDireccion.setText("Av. Departamental 987, Santiago");
            tvTelefono.setText("+56 9 4444 5555");
            tvCorreo.setText("gimnasio@izonegym.cl");
            tvWeb.setText("https://www.izonegym.cl");
        }
        else
        {
            tvNombre.setText("Vida Activa");
            tvDescripcion.setText("Mueve tu cuerpo - Elige sano");
            tvHorario.setText("Lunes - Viernes 08:00-20:00");
            tvDireccion.setText("Av. Lautaro 495, Santiago");
            tvTelefono.setText("+56 9 9999 4466");
            tvCorreo.setText("gimnasio@vida.cl");
            tvWeb.setText("https://www.vida.cl");
        }

//        Damos acceso a que se abran apps desde nuestra aplicación

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
                Toast.makeText(this, "No hay app que pueda mostrar el mapa", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "No hay app que pueda abrir esta pagina web", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(this, "No hay app que pueda llamar en este telefono", Toast.LENGTH_SHORT).show();

            }
        });


        btnCorreo.setOnClickListener(v ->
        {
            String correo = tvCorreo.getText().toString().trim();
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + correo));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta de inscripcion");
            intent.putExtra(Intent.EXTRA_TEXT, "Hola, quisiera más información sobre sus planes.");
            try
            {
                startActivity(intent);
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(this, "No hay app de correo instalada", Toast.LENGTH_SHORT).show();
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