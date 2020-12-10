package mx.edu.tesoem.isc.ecr.a7s212021prac9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gvdatos;
    EditText txtnombre, txtedad, txtsexo;
    List<Datos> datos = new ArrayList<>();
    AdaptadorBae adaptadorBae;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvdatos = findViewById(R.id.gvDatos);
        txtnombre = findViewById(R.id.txtnombre);
        txtedad = findViewById(R.id.txtedad);
        txtsexo = findViewById(R.id.txtsexo);

        Verificar();
        gvdatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datos dato = (Datos) adaptadorBae.getItem(position);
                DatosParcelable datosParcelable = new DatosParcelable(dato.getNombre(), dato.getEdad(), dato.getSexo());
                Intent intent = new Intent(MainActivity.this, Detalles_Activity2.class);
                intent.putExtra("Nombre", dato.getNombre());
                intent.putExtra("DatosParcelable", datosParcelable);
                startActivity(intent);
            }
        });
    }

    private void Verificar(){
        almacen conexion = new almacen();

        if (conexion.Existe(this)){
            if (conexion.Leer(this)){
                datos = conexion.getDatos();
                adaptadorBae = new AdaptadorBae(datos, this);
                gvdatos.setAdapter(adaptadorBae);
            }else{
                Toast.makeText(this, "No se puede leer la información", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No existe el archivo, favor de grabar información", Toast.LENGTH_SHORT).show();
        }
    }

    public void Grabar(View view){
        Datos dato = new Datos();
        almacen conexion = new almacen();

        dato.setNombre(txtnombre.getText().toString());
        dato.setEdad(txtedad.getText().toString());
        dato.setSexo(txtsexo.getText().toString());

        datos.add(dato);
        if(conexion.Escribir(this, datos)){
            Toast.makeText(this, "Se escribieron correctamente", Toast.LENGTH_SHORT).show();
            Verificar();
        }else{
            Toast.makeText(this, "Error al escribir...", Toast.LENGTH_SHORT).show();
        }
    }
}