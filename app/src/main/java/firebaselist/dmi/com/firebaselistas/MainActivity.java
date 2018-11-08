package firebaselist.dmi.com.firebaselistas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    FirebaseDatabase db;

    //1. Crear el adaptador
    FirebaseListAdapter<Usuario> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= FirebaseDatabase.getInstance();

        //2. Referenciar el list view
        lista= findViewById(R.id.lista);

        //Leer la lista
        //ingresar a leer datos de los usuarios
        //Query es para peticion a la base de datos

        //3. Hacer Query a mostrar
      Query ref= db.getReference().child("usuarios");


      //4. Mirar cómo se ve cada renglón de la lista --> FirebaseListOptions
        //Set layout hace como se ve cada renglón de la lista
        //Query es con qué llenamos la lista

        FirebaseListOptions<Usuario> options= new FirebaseListOptions.Builder<Usuario>().setLayout(R.layout.renglon).setQuery(ref, Usuario.class).build();

        // 5. Definir el adaptador (construirlo)
        adapter= new FirebaseListAdapter<Usuario>(options) {
            @Override

            //Equivale al método getView, lo que hace es generar vistas de los datos tantas veces haya los datos
            protected void populateView(@NonNull View v, @NonNull Usuario model, int position) {
                TextView renglon_correo= v.findViewById(R.id.renglon_correo);
                TextView renglon_nombre= v.findViewById(R.id.renglon_nombre);
                TextView renglon_uid= v.findViewById(R.id.renglon_uid);

                //sacar de la base de datos (model) la información de Usuario

                renglon_correo.setText(model.correo);
                renglon_nombre.setText(model.nombre);
                renglon_uid.setText(model.uid);

            }
        };

        // 6. Añadir el adaptador a la lista
        lista.setAdapter(adapter);

    }

    // 7. Configuración del adaptador


    @Override
    protected void onStart() {
        super.onStart();
        //iniciar adaptador
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
