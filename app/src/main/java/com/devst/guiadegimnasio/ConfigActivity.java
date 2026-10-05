package com.devst.guiadegimnasio;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ConfigActivity extends AppCompatActivity {

    TextView tvTituloConfig;
    TextView tvInfoConfig;
    Button btnWifi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTituloConfig = findViewById(R.id.tvTituloConfig);
        tvInfoConfig = findViewById(R.id.tvInfoConfig);
        btnWifi = findViewById(R.id.btnWifi);

        btnWifi.setOnClickListener(v ->
        {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            try
            {
                startActivity(intent);
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(this, "No se pueden abrir los ajustes de Wi-Fi", Toast.LENGTH_SHORT).show();
            }

        });

    }
}