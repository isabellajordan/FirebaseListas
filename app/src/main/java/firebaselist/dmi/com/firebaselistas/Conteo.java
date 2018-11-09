package firebaselist.dmi.com.firebaselistas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Conteo extends AppCompatActivity {

    //CONTEO DE FIRMAS!!!!!!!!!!!!!!

    Button btn_firmar1;
    Button btn_firmar2;

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteo);

        btn_firmar1= findViewById(R.id.btn_firmar1);
        btn_firmar2= findViewById(R.id.btn_firmar2);

        db= FirebaseDatabase.getInstance();

        //sacar porcentaje de cuántos firmaron

        btn_firmar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //crear Ramas para base de datos
                db.getReference().child("firmas").child("peticion1").push().setValue("F");
            }
        });

        btn_firmar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //crear Ramas para base de datos
                db.getReference().child("firmas").child("peticion2").push().setValue("F");
            }
        });

        //PARA CONTABILIZAR
        //addValue está pendiente de cada que se modifica algo en la rama padre
        db.getReference().child("firmas").child("peticion1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Devuelve el número de firmas (like)
                String firmas= "FIRMAR ("+dataSnapshot.getChildrenCount()+")";
                //Agregarlo al botón
                btn_firmar1.setText(firmas);

                // PARA LEER LAS "F" QUE ESTÁN EN LISTA

                //Dato es un String
                for (DataSnapshot dato: dataSnapshot.getChildren()){

                    // Nombre de la rama y valor de la rama
                    Log.e("CLAVE", ""+ dato.getKey());
                    Log.e("VALOR", ""+ dato.getValue());

                    //Transformar el dato a String
                    String valor= dato.getValue(String.class);
                    Log.e("VALOR TRANSFORMADO", ""+ valor);

                    //TAREA PARA REPASAR EL PARCIAL
                    //Poner en vez de set value F un objeto de fecha de publicación --> año, mes, dia, horas, minutos

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Para contabilizar la petición 2
        db.getReference().child("firmas").child("peticion2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Devuelve el número de firmas (like)
                String firmas= "FIRMAR ("+dataSnapshot.getChildrenCount()+")";
                //Agregarlo al botón
                btn_firmar2.setText(firmas);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
