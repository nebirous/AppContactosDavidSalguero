package com.example.nebirous.appcontactos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nebir on 18/10/2015.
 */
public class Formulario extends AppCompatActivity{
     Contacto aux;
     List<String> pasar;
     Context ctx = this;
     EditText nom;
     EditText num;
     EditText numO;
     EditText numO1;
     EditText numO2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formul);
        Intent i=getIntent();
        Bundle b = i.getExtras();
        int pos = b.getInt("pos");
        aux = Matriz.getContacto(pos);
        init();
    }
    //Método de inicio. Se diferencia del de Formularioadd porque este recoge los datos del contacto
    //que le hemos pasado por el bundle y los mete en los campos.
    public void init(){
         nom = (EditText) findViewById(R.id.editText);
         num = (EditText) findViewById(R.id.editText2);
         numO = (EditText) findViewById(R.id.editText3);
         numO1 = (EditText) findViewById(R.id.editText4);
         numO2 = (EditText) findViewById(R.id.editText5);
//        ImageButton save = (ImageButton) findViewById(R.id.imageButton);
//        ImageButton cancel = (ImageButton) findViewById(R.id.imageButton2);

        nom.setText(aux.getNombre());
        num.setText(aux.getPrimero());
        if(aux.tamNumeros()>1) {
            numO.setText(aux.getNumero(1));
        }
        if(aux.tamNumeros()>2) {
            numO1.setText(aux.getNumero(2));
        }
        if(aux.tamNumeros()>3) {
            numO2.setText(aux.getNumero(3));
        }
//
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
    //Método utilizado por el botón guardar
    public void save(View v){

        if(nom.length() == 0 || num.length() == 0){
            Toast.makeText(ctx, R.string.str_añadir, Toast.LENGTH_LONG).show();
        }else{
            aux.setNombre(nom.getText().toString());
            pasar = new ArrayList<String>();
            pasar.add(num.getText().toString());
            if(numO.length()!=0){
                pasar.add(numO.getText().toString());
            }
            if(numO1.length()!=0){
                pasar.add(numO1.getText().toString());
            }
            if(numO2.length()!=0){
                pasar.add(numO2.getText().toString());
            }
            aux.setNums(pasar);
            Matriz.setContacto(aux);
            Matriz.guardar(aux);
            ListViewV3.actualiza();
            finish();

        }
    }
    //Método que utiliza el botón cancelar
    public void cancel (View v){
        finish();
    }


}
