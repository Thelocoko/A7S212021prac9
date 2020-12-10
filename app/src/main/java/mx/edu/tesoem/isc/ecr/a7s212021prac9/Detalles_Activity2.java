package mx.edu.tesoem.isc.ecr.a7s212021prac9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Detalles_Activity2 extends AppCompatActivity {

    TextView lblnombre, lbledad, lblsexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_2);

        lblnombre = findViewById(R.id.dlblNombre);
        lbledad = findViewById(R.id.dlblEdad);
        lblsexo = findViewById(R.id.dlblSexo);

        String  nonbre = getIntent().getExtras().get("Nombre").toString();
        DatosParcelable dato = getIntent().getParcelableExtra("DatosParcelable");
        getSupportActionBar().setTitle("Fijo");
    }
}