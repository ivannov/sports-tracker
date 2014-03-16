package org.ivko.tracker.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.ivko.tracker.model.Result;

/**
 * 
 */
@Stateless
@Path("/results")
public class ResultService
{
   @PersistenceContext(unitName = "sports-tracker")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Result entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(ResultService.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Result entity = em.find(Result.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Result> findByIdQuery = em.createQuery("SELECT DISTINCT r FROM Result r WHERE r.id = :entityId ORDER BY r.id", Result.class);
      findByIdQuery.setParameter("entityId", id);
      Result entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Result> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<Result> findAllQuery = em.createQuery("SELECT DISTINCT r FROM Result r ORDER BY r.id", Result.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<Result> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(Result entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}