package com.devst.guiadegimnasio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

// Declaracion de Variables

    Button btnGimnasio1;
    Button btnGimnasio2;
    Button btnGimnasio3;
    Button btnAjustes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Conectar con los Layout

        btnGimnasio1 = findViewById(R.id.btnGimnasio1);
        btnGimnasio2 = findViewById(R.id.btnGimnasio2);
        btnGimnasio3 = findViewById(R.id.btnGimnasio3);
        btnAjustes = findViewById(R.id.btnAjustes);

        btnGimnasio1.setOnClickListener(v ->
        {
        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        intent.putExtra("gimnasio", 1);
        startActivity(intent);
        });

        btnGimnasio2.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
            intent.putExtra("gimnasio", 2);
            startActivity(intent);
        });

        btnGimnasio3.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
            intent.putExtra("gimnasio", 3);
            startActivity(intent);
        });

        btnAjustes.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(intent);
        });









    }
}