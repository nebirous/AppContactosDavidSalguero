package com.example.nebirous.appcontactos;

import java.util.List;

/**
 * Created by Nebirous on 15/10/2015.
 */
public class Contacto implements Comparable<Contacto>{
    private long id;
    private String Nombre;
    private List<String> nums;


    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    public Contacto() {
    }

    public Contacto(long id, String nombre) {
        this.id = id;
        Nombre = nombre;
    }
    public String getPrimero(){
        return nums.get(0);
    }

    public String getNumero(int posicion){
        return nums.get(posicion);
    }
    public void setNum(List<String> num){
        nums = num;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
    //Para dar formato a la cadena de números, para que salgan en diferentes lineas en la ventana
    public String toStringMod(){
        String numeros = "";
        for(int i = 0; i < nums.size(); i++){
            numeros += getNumero(i) + "\n";
            System.out.println("------"+ numeros+"\n");
        }
        return numeros;
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int compareTo(Contacto contacto) {
        int r = this.Nombre.compareToIgnoreCase(contacto.Nombre);
        if(r==0){
            r=(int)(this.id-contacto.id);
        }
        return r;
    }
    public List<String> devolverNums(){
        return nums;
    }
    public String getNums() {
        String numeros = toStringMod();
        return numeros;
    }
    //Devuelve un int con el tamaño del List nums
    public int tamNumeros(){
        return nums.size();
    }
}
