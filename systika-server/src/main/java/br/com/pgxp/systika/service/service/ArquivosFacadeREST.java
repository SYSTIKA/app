/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pgxp.systika.service.service;

import br.com.pgxp.systika.business.ArquivosBC;
import br.com.pgxp.systika.domain.Arquivos;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.util.Beans;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author escritorio
 */

@javax.ejb.Stateless
@Path("arquivos")
public class ArquivosFacadeREST  {
    
    
    private ArquivosBC arquivosBC = Beans.getReference(ArquivosBC.class);

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Arquivos entity) {
        arquivosBC.insert(entity);
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Arquivos entity) {
        arquivosBC.update(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        arquivosBC.delete(id);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Arquivos find(@PathParam("id") Long id) {
        return arquivosBC.load(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Arquivos> findAll() {
        return arquivosBC.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(arquivosBC.findAll().size());
    }
    
}
