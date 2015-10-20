package com.example.nebirous.appcontactos;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nebir on 18/10/2015.
 */
public class Matriz {

    private static List<Contacto> agenda;

    public Matriz(Context ctx){
        agenda = getListaContactos(ctx);
    }

    public List<Contacto> getAgenda(){
        return agenda;
    }

    public List<String> getNumeros(long id,Context ctx){
        return getListaTelefonos(id,ctx);
    }

    public static void setContacto(Contacto contacto){
        agenda.add(contacto);
    }

    public static List<Contacto> getListaContactos(Context ctx){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = ? and " +
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "= ?";
        String argumentos[] = new String[]{"1","1"};
        String orden = ContactsContract.Contacts.DISPLAY_NAME + " collate localized asc";
        Cursor cursor = ctx.getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int indiceNombre = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        List<Contacto> lista = new ArrayList<>();
        Contacto contacto;
        while(cursor.moveToNext()){
            contacto = new Contacto();
            contacto.setId(cursor.getLong(indiceId));
            contacto.setNombre(cursor.getString(indiceNombre));
            lista.add(contacto);
        }
        return lista;
    }

    public static List<String> getListaTelefonos(long id, Context ctx){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
        String argumentos[] = new String[]{id+""};
        String orden = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Cursor cursor = ctx.getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceNumero = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        List<String> lista = new ArrayList<>();
        String numero;
        while(cursor.moveToNext()){
            numero = cursor.getString(indiceNumero);
            lista.add(numero);
        }
        return lista;
    }

    public static Contacto getContacto (int posicion){
        return agenda.get(posicion);
    }
    //Nos permite ordenar la lista de contactos
    public static void ordenar(){
        Collections.sort(agenda);
    }
    //Este método nos permite guardar un contacto modificado sin que se sobreescriba, borrando el anterior y añadiendo uno
    //nuevo con los datos modificados
    public static void guardar (Contacto aux){
        borrar(aux);
        agenda.add(aux);
    }
    //Permite borrar un contacto de la agenda.
    public static void borrar(Contacto aux){
        Contacto c;
        for (int i=0; i< agenda.size();i++){
            c = agenda.get(i);
                if(c.getNombre() == aux.getNombre())
                    agenda.remove(i);
        }
    }
}
