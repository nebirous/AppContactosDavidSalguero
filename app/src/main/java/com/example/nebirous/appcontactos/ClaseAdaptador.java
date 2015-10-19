package com.example.nebirous.appcontactos;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class ClaseAdaptador extends ArrayAdapter<Contacto>{

    private Context ctx;
    private int res;
    private List<Contacto> agenda;
    private LayoutInflater linflater;
    private ImageView iv, iv2;

    static class ViewHolder {

        public TextView tv1, tv2;
        public ImageView iv, iv2;

    }
    public ClaseAdaptador(Context context, int resource, List<Contacto> objects) {
        super(context, resource, objects);
        this.ctx = context; //ACTIVIDAD
        this.res = resource; //LAYOUT DEL ITEM
        this.agenda = objects; //LISTA DE VALORES
        this.linflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1
        ViewHolder gv = new ViewHolder();
        if (convertView == null) {
            convertView = linflater.inflate(res, null);

            TextView tv = (TextView) convertView.findViewById(R.id.tv1);
            gv.tv1 = tv;
            tv = (TextView) convertView.findViewById(R.id.tv2);
            gv.tv2 = tv;
            iv = (ImageView) convertView.findViewById(R.id.iv1);
            gv.iv = iv;
            convertView.setTag(gv);
            gv.iv.setId(position);

        } else{
            gv = (ViewHolder) convertView.getTag();
        }

        gv.tv1.setText(agenda.get(position).getNombre());
        gv.tv2.setText(agenda.get(position).getPrimero());
        if (agenda.get(position).tamNumeros() > 1){
            gv.iv.setVisibility(View.VISIBLE);
        }else{
            gv.iv.setVisibility(View.INVISIBLE);
        }


        //2
        /*TextView tv = (TextView) convertView.findViewById(R.id.tv1);
        tv.setText(valores[position]);
        tv = (TextView) convertView.findViewById(R.id.tv1);
        tv.setText(valores[position]);*/
        //3
        return convertView;
    }
    public boolean borrar(int position){
        try{
            agenda.remove(position);
            this.notifyDataSetChanged();
        }catch (IndexOutOfBoundsException e){
            return false;
        }

        return true;
    }
    public void mas(View v){
        Contacto aux = agenda.get(v.getId());
        String s= ctx.getString(R.string.str_masNum)+" "+aux.getNombre()+"\n";
        s+= aux.toStringMod();
        System.out.println("------------------"+s);
            AlertDialog.Builder masNumeros = new AlertDialog.Builder(ctx);
            masNumeros.setMessage(s);
            masNumeros.setCancelable(true);
            masNumeros.setPositiveButton(R.string.str_salir, null);
            AlertDialog muestra = masNumeros.create();
            muestra.show();
    }







}