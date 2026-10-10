package com.example.container2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enlaza con el XML de la pantalla de Bienvenida
        setContentView(R.layout.activity_recycler);

        // Vincula el botón usando el ID exacto del XML (Punto 1 de la rúbrica)
        Button btnIngresar = findViewById(R.id.btnIngresar);

        if (btnIngresar != null) {
            btnIngresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Redirige al Formulario Principal (MainActivity)
                    Intent intent = new Intent(RecyclerActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la bienvenida para que no vuelva atrás al pulsar retroceso
                }
            });
        }
    }
}