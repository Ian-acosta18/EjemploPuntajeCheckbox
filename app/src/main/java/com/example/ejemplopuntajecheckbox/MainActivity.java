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

public class MainActivity extends AppCompatActivity {
    CheckBox ch1,ch2,ch3,ch4;
    Button b1,b2;
    TextView Puntaje,Mostrar;
    int punta = 0;
    boolean verificar = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ch1=findViewById(R.id.cb1);
        ch2=findViewById(R.id.cb2);
        ch3=findViewById(R.id.cb3);
        ch4=findViewById(R.id.cb4);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        Puntaje=findViewById(R.id.Txtp);
        Mostrar=findViewById(R.id.txtm);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(ch3.isChecked() && ch4.isChecked() && !ch1.isChecked() && !ch2.isChecked()){
                    punta += 1;
                    Mostrar.setText("Correcto");
                    Puntaje.setText("Puntaje: "+punta);

                }else{
                    punta+=0;
                    Mostrar.setText("Incorrecto");
                    Puntaje.setText("Puntaje: "+ punta);
                }
                b1.setEnabled(false);

                b1.setText("Ya respondiste");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("Puntaje:", punta);
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