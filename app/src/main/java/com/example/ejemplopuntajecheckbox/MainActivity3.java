package com.example.ejemplopuntajecheckbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    CheckBox ch1, ch2, ch3, ch4;
    Button b1, b2;
    TextView Puntaje, Mostrar;
    int punta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        // Recuperamos el puntaje acumulado de la actividad 2
        punta = getIntent().getIntExtra("Puntaje:", 0);

        ch1 = findViewById(R.id.cb1); // Google Play Store (Correcta)
        ch2 = findViewById(R.id.cb2); // WhatsApp
        ch3 = findViewById(R.id.cb3); // App Store (Correcta)
        ch4 = findViewById(R.id.cb4); // Netflix

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        Puntaje = findViewById(R.id.Txtp);
        Mostrar = findViewById(R.id.txtm);

        // Mostramos el puntaje que llevamos hasta ahora
        Puntaje.setText("Puntaje: " + punta);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificamos que se seleccionen las tiendas oficiales (1 y 3)
                if(ch1.isChecked() && ch3.isChecked() && !ch2.isChecked() && !ch4.isChecked()){
                    punta += 1;
                    Mostrar.setText("¡Excelente!");
                } else {
                    Mostrar.setText("Incorrecto");
                }
                Puntaje.setText("Puntaje: " + punta);
                b1.setEnabled(false);
                b1.setText("Pregunta terminada");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí podrías ir a una pantalla final de resultados (MainActivity4 o Resultados)
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                intent.putExtra("PuntajeFinal", punta);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}