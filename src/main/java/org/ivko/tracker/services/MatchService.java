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
import org.ivko.tracker.model.Match;

/**
 * 
 */
@Stateless
@Path("/matches")
public class MatchService
{
   @PersistenceContext(unitName = "sports-tracker")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Match entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(MatchService.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Match entity = em.find(Match.class, id);
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
      TypedQuery<Match> findByIdQuery = em.createQuery("SELECT DISTINCT m FROM Match m LEFT JOIN FETCH m.team1 LEFT JOIN FETCH m.team2 LEFT JOIN FETCH m.result WHERE m.id = :entityId ORDER BY m.id", Match.class);
      findByIdQuery.setParameter("entityId", id);
      Match entity;
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
   public List<Match> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<Match> findAllQuery = em.createQuery("SELECT m FROM Match m ORDER BY m.id", Match.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<Match> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(Match entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}