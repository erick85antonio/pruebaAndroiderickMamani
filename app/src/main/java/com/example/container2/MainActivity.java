package com.example.container2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerEntrenamiento;
    private RadioGroup radioGroupIntensidad;
    private CheckBox cbCalentamiento, cbHidratacion, cbEstiramiento;
    private ProgressBar progressBarMeta;
    private TextView tvProgresoMeta;
    private RatingBar ratingBarDificultad;
    private Button btnRegistrar;

    private RecyclerView recyclerViewHistorial;
    private ContactosAdapter adapter;
    private List<ContactosModel> historialEntrenamientos;
    private int progresoActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular componentes con el XML de FitTracker
        spinnerEntrenamiento = findViewById(R.id.spinnerEntrenamiento);
        radioGroupIntensidad = findViewById(R.id.radioGroupIntensidad);
        cbCalentamiento = findViewById(R.id.cbCalentamiento);
        cbHidratacion = findViewById(R.id.cbHidratacion);
        cbEstiramiento = findViewById(R.id.cbEstiramiento);
        progressBarMeta = findViewById(R.id.progressBarMeta);
        tvProgresoMeta = findViewById(R.id.tvProgresoMeta);
        ratingBarDificultad = findViewById(R.id.ratingBarDificultad);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        recyclerViewHistorial = findViewById(R.id.recyclerViewHistorial);

        // Configurar Spinner con las opciones requeridas por la rúbrica
        String[] opcionesEntrenamiento = {"Fuerza", "Cardio", "Yoga", "Calistenia"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesEntrenamiento);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntrenamiento.setAdapter(spinnerAdapter);

        // Inicializar RecyclerView e Historial
        historialEntrenamientos = new ArrayList<>();
        adapter = new ContactosAdapter(historialEntrenamientos);
        recyclerViewHistorial.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHistorial.setAdapter(adapter);

        // Lógica del botón registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tipo = spinnerEntrenamiento.getSelectedItem().toString();

                int seleccionadoId = radioGroupIntensidad.getCheckedRadioButtonId();
                RadioButton rbSeleccionado = findViewById(seleccionadoId);
                String intensidad = (rbSeleccionado != null) ? rbSeleccionado.getText().toString() : "Baja";

                int dificultad = (int) ratingBarDificultad.getRating();

                // Añadir la sesión de entrenamiento al historial
                ContactosModel nuevaSesion = new ContactosModel(tipo, intensidad, dificultad);
                historialEntrenamientos.add(nuevaSesion);
                adapter.notifyItemInserted(historialEntrenamientos.size() - 1);

                // Aumentar la barra de progreso un 25% por entrenamiento
                if (progresoActual < 100) {
                    progresoActual += 25;
                    progressBarMeta.setProgress(progresoActual);
                    tvProgresoMeta.setText("Progreso Meta Diaria: " + progresoActual + "%");
                }

                Toast.makeText(MainActivity.this, "¡Entrenamiento registrado con éxito!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}