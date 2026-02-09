package com.example.ejemplopuntajecheckbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    TextView txtFinal, txtMensaje;
    Button btnReiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        txtFinal = findViewById(R.id.txtPuntosFinal);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnReiniciar = findViewById(R.id.btnReiniciar);

        // Recuperamos el puntaje final enviado desde la MainActivity4
        int puntajeTotal = getIntent().getIntExtra("PuntajeFinal", 0);

        // Mostramos el puntaje
        txtFinal.setText("Puntaje Final: " + puntajeTotal + " / 4");

        // Mensaje personalizado según el desempeño
        if (puntajeTotal == 4) {
            txtMensaje.setText("¡Excelente! Eres un experto en apps móviles.");
        } else if (puntajeTotal >= 2) {
            txtMensaje.setText("¡Buen trabajo! Tienes buenos conocimientos.");
        } else {
            txtMensaje.setText("Sigue practicando para aprender más.");
        }

        // Botón para volver al inicio del test
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                // Limpiamos el historial de actividades para empezar de cero
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_result), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}