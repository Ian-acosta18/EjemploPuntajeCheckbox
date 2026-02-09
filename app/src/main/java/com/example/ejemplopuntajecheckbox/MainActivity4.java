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

public class MainActivity4 extends AppCompatActivity {
    CheckBox ch1, ch2, ch3, ch4;
    Button b1, b2;
    TextView Puntaje, Mostrar;
    int punta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        // Recuperamos el puntaje acumulado que viene de la actividad 3
        punta = getIntent().getIntExtra("Puntaje:", 0);

        ch1 = findViewById(R.id.cb1); // Cámara (Correcta)
        ch2 = findViewById(R.id.cb2); // GPS (Correcta)
        ch3 = findViewById(R.id.cb3); // Impresora
        ch4 = findViewById(R.id.cb4); // Escáner

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        Puntaje = findViewById(R.id.Txtp);
        Mostrar = findViewById(R.id.txtm);

        // Mostramos el puntaje actual en pantalla
        Puntaje.setText("Puntaje: " + punta);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificamos que se seleccionen solo las correctas (1 y 2)
                if(ch1.isChecked() && ch2.isChecked() && !ch3.isChecked() && !ch4.isChecked()){
                    punta += 1;
                    Mostrar.setText("¡Muy bien!");
                } else {
                    Mostrar.setText("Incorrecto");
                }
                Puntaje.setText("Puntaje: " + punta);
                b1.setEnabled(false);
                b1.setText("Ya respondiste");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enviamos el puntaje final a la pantalla de resultados
                Intent intent = new Intent(MainActivity4.this, ResultActivity.class);
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