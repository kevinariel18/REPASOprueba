package com.krakedev.inventarios3.servicios;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.TipoDocumentosBDD;
import com.krakedev.inventarios3.entidades.TipoDocumento;
import com.krakedev.inventarios3.exepciones.KrakeDevException;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;



@Path("tiposdocumento")
public class TipoDocumentoService {

    @Path("recuperar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarTodos() {
        TipoDocumentosBDD tipoDocBDD = new TipoDocumentosBDD();
        ArrayList<TipoDocumento> tiposDocumentos = null;

        try {
            tiposDocumentos = tipoDocBDD.recuperarTodos();
            return Response.ok(tiposDocumentos).build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
   
   
    @Path("insertar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertar(TipoDocumento tipoDocumento) {
        TipoDocumentosBDD tipoDocBDD = new TipoDocumentosBDD();

        try {
            tipoDocBDD.insertar(tipoDocumento);
            return Response.status(Response.Status.CREATED).build();
        } catch (KrakeDevException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    
    
    
    
}
