/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pgxp.systika.service;

import br.com.pgxp.systika.business.ArquivosBC;
import br.com.pgxp.systika.domain.Arquivos;
import br.gov.frameworkdemoiselle.util.Beans;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author escritorio
 */
@Path(value = "/manutencao")
public class Manutencao {
    
    
    private ArquivosBC arquivosBC= Beans.getReference(ArquivosBC.class);

    @POST
    @Path("/salvar/{apiKey}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void salvar(@PathParam("apiKey") String apiKey, Arquivos arquivos){
        arquivosBC.insert(arquivos);
    }

//    @POST
//    @Path("/salvar/lote/{apiKey}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void salvarLote(@PathParam("apiKey") String apiKey, List<Pedido> lote);
//
    @GET
    @Path("/carregar/{id}/{apiKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public Arquivos carregar(@PathParam("apiKey") String apiKey, @PathParam("id") Long id){
        return arquivosBC.load(id);
    }
//
//    @GET
//    @Path("/cancelar/{id}/{apiKey}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public void cancelar(@PathParam("apiKey") String apiKey, @PathParam("id") Integer id);
//
    @GET
    @Path("/buscarTodos/{apiKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Arquivos> buscarTodos(@PathParam("apiKey") String apiKey){
        return arquivosBC.findAll();
    }
}
