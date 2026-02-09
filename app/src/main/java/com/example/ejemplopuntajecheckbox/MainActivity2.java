package com.example.ejemplopuntajecheckbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox; // Faltaba este import
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    CheckBox ch1, ch2, ch3, ch4;
    Button b1, b2;
    TextView Puntaje, Mostrar;
    int punta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2); // Asegúrate que el XML sea el correcto

        // 1. Recuperar el puntaje de la actividad anterior
        punta = getIntent().getIntExtra("Puntaje:", 0);

        ch1 = findViewById(R.id.cb1);
        ch2 = findViewById(R.id.cb2);
        ch3 = findViewById(R.id.cb3);
        ch4 = findViewById(R.id.cb4);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        Puntaje = findViewById(R.id.Txtp);
        Mostrar = findViewById(R.id.txtm);

        // Mostrar el puntaje inicial que traemos
        Puntaje.setText("Puntaje: " + punta);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica de respuestas fáciles: Android e iOS son correctas (1 y 2)
                if(ch1.isChecked() && ch2.isChecked() && !ch3.isChecked() && !ch4.isChecked()){
                    punta += 1;
                    Mostrar.setText("¡Correcto!");
                } else {
                    Mostrar.setText("Incorrecto");
                }
                Puntaje.setText("Puntaje: " + punta);
                b1.setEnabled(false);
                b1.setText("Ya respondiste");
            }
        });

        b2.setOnClickListener(v -> {
            // 2. Corregido el nombre de la clase actual
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("Puntaje:", punta);
            startActivity(intent);
        });

        // 3. Este bloque debe ir dentro de onCreate
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}