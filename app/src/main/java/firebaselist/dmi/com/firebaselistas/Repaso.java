package firebaselist.dmi.com.firebaselistas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Repaso extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repaso);

        //REPASO DE CÓMO REGISTRAR USUARIOS DE MANERA COMPLETA

        //autenticador. Funciones --> login, sign out, sign in
        auth= FirebaseAuth.getInstance();

        db= FirebaseDatabase.getInstance();

        auth.createUserWithEmailAndPassword("aleku@shinanime.com","123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override

            //saber si la operación fue correcta
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    //Si es exitosa la operación ya estoy log

                    //Si estoy logeado puedo pedir el usuario actual
                    //Con esto registro el usuario online
                  String uid=auth.getCurrentUser().getUid();

                  //Crear el usuario para meterlo a la base de datos
                    Usuario nuevo_usuario= new Usuario();

                    //setear los parámetros
                    nuevo_usuario.uid= uid;

                    //Estos datos se pueden extraer de editText
                    nuevo_usuario.nombre= "Isa";
                    nuevo_usuario.correo= "isabellajordan@correoIcesi.com";
                    nuevo_usuario.contrasena= "123456";


// Creando ramas a la base de datos
//El uid de la rama concuerda con el uid del usuario
                    db.getReference().child("usuarios").child(uid).setValue(nuevo_usuario);



                }else{
                    //donde no fue exitosa la operación
                    Toast.makeText(Repaso.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
