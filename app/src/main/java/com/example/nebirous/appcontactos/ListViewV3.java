package com.example.nebirous.appcontactos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ListViewV3 extends AppCompatActivity {

    private static ClaseAdaptador clAdaptador;
    private List<Contacto> lista;
    private ListView lv;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_app);
        init();
    }
    //Creación del menú de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contactos_app, menu);

        return true;
    }
    //Contenido del menú de opciones. Solo cuenta con un botón añadir
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent = new Intent(this, FormularioAdd.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void init(){
        lv = (ListView) findViewById(R.id.listView);
        Matriz m=new Matriz(this);
        lista = m.getAgenda();

        for(Contacto aux:lista)
            aux.setNums(m.getNumeros(aux.getId(),this));


        clAdaptador = new ClaseAdaptador(this, R.layout.elemento_lista, lista);
        lv.setAdapter(clAdaptador);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                llamar(posicion);
            }
        });

        registerForContextMenu(lv);

    }

    //Creación del menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, menu);
    }
    //Menú contextual
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        long id = item.getItemId();

        AdapterView.AdapterContextMenuInfo VistaInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int posicion = VistaInfo.position;

        if(id==R.id.mnborrar){
            clAdaptador.borrar(posicion);
            return true;
        }else if(id == R.id.mneditar){
            intent = new Intent(this, Formulario.class);
            Bundle b=new Bundle();
            b.putInt("pos",posicion);
            intent.putExtras(b);
            startActivity(intent);
            return true;
        }
        return super.onContextItemSelected(item);

    }
    //Llama a la clase mas, la cual se encarga de abrir la ventana que nos muestra todos los teléfonos
    // de un contacto
    public void ventana(View v){
        clAdaptador.mas(v);
    }
    //Método utilizado para actualizar la agenda y ordenarla. Es utilizado después de cualquier modificación
    public static void actualiza(){
        Matriz.ordenar();
        clAdaptador.notifyDataSetChanged();
    }
    //Método utilizado para que al clicar en un listview se llame al primer número del contacto
    public void llamar(int posicion){
        String fono= Matriz.getContacto(posicion).getNumero(0);
        Uri numero = Uri.parse( "tel:" + fono.toString() );
        Intent llamar = new Intent(Intent.ACTION_CALL, numero);
        startActivity(llamar);
    }
}