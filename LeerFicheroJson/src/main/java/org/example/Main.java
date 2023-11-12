package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static final ObjectMapper JSON_MAPPER= new ObjectMapper();
    public static void main(String[] args) {
        try{
            ArrayList<Persona> personas =JSON_MAPPER.readValue(new File("src/main/resources/Persona.json"),
                    JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Persona.class));

            for (int i=0; i<personas.size(); i++){
                System.out.println(personas.get(i).getNombre()+" ");
                System.out.println(personas.get(i).getApellidos()+" ");
                System.out.println(personas.get(i).getEdad()+" ");
                System.out.println(personas.get(i).isCarnet()+" ");
                System.out.println();
                //Para convertir los registros en un objeto
                Persona p=new Persona();
                p=(Persona) personas.get(i);
                //System.out.println(p);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}