package com.devst.guiadegimnasio;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {
    TextView tvInfoConfig;
    Button btnWifi;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // CORRECCIÓN: Validación de nulos y uso de recurso de strings para el título
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.titulo_ajustes));
        }

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
                // CORRECCIÓN: Toast migrado a recursos string
                Toast.makeText(this, getString(R.string.toast_error_wifi), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
