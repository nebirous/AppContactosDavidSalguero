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
public class FormularioAdd extends AppCompatActivity{
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
        init();
    }

    public void init(){
        nom = (EditText) findViewById(R.id.editText);
        num = (EditText) findViewById(R.id.editText2);
        numO = (EditText) findViewById(R.id.editText3);
        numO1 = (EditText) findViewById(R.id.editText4);
        numO2 = (EditText) findViewById(R.id.editText5);

    }
    //Método que utiliza el botón guardar
    public void save(View v){
        aux = new Contacto();
        if(nom.length() == 0 && num.length() == 0){
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
    //Método que utiliza el botón cancelar.
    public void cancel(View v){
        finish();
    }


}
