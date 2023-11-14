package com.example.proyectohospital;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import domain.Datos.Cita;
import org.bson.Document;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CitaDAO {
    MongoClient con;
    MongoDatabase database;
    MongoCollection<Document> collection;
    Gson gson = new Gson();
    public void conectarse(){
        con=ConexionBD.conectar();
        database= con.getDatabase("Citas_ListaEspera");
    }
    public void insertarDatos(Cita cita){
        try{
            this.conectarse();
            collection = database.getCollection("Datos");
            Document documento1= new Document();
            documento1.append("Titular",cita.getTitularCita()).
                    append("Fecha", cita.getFechaCita()).append("Especialidad",cita.getEspecialidadesCitas()).
                    append("Medico",cita.getMedicoCita()).append("Motivo",cita.getMotivoCita());
            collection.insertOne(documento1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Cita> obtenerDatos(){
        this.conectarse();
        List<Cita> citas = new ArrayList<>();
        Document documento = new Document();


        FindIterable findIterable = database.getCollection("Datos").find(documento);


        Iterator<Document> iter = findIterable.iterator();
        while (iter.hasNext()) {
            Document doc = iter.next();
            Cita cita = new Cita();
            cita.setTitularCita(doc.getString("Titular"));
            cita.setFechaCita(doc.getString("Fecha"));
            cita.setEspecialidadesCitas(doc.getString("Especialidad"));
            cita.setMedicoCita(doc.getString("Medico"));
            cita.setMotivoCita(doc.getString("Motivo"));
            citas.add(cita);
        }

        return citas;


    }


    public void eliminarCita(Cita cita){//Preguntar si está bien
        this.conectarse();
        collection = database.getCollection("Datos");
        Document documento1 = new Document("Titular", cita.getTitularCita())
                .append("Fecha", cita.getFechaCita())
                .append("Especialidad", cita.getEspecialidadesCitas())
                .append("Medico", cita.getMedicoCita())
                .append("Motivo", cita.getMotivoCita());
        collection.deleteOne(documento1);
    }
    public void actualizarCita(String medico, String especialidad, String fecha, String motivo, Cita cita){
        this.conectarse();
        collection = database.getCollection("Datos");
        collection.updateOne(new Document("Titular", cita.getTitularCita()),
                new Document("$set", new Document("Fecha", fecha)
                        .append("Especialidad", especialidad)
                        .append("Medico", medico)
                        .append("Motivo", motivo)
                )
        );
    }
}
