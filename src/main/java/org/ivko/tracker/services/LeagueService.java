package org.ivko.tracker.services;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.ivko.tracker.model.League;
import org.ivko.tracker.model.Match;

/**
 * 
 */
@Stateless
@Path("leagues")
public class LeagueService {
    @PersistenceContext(unitName = "sports-tracker")
    private EntityManager em;

    @POST
    @Consumes("application/json")
    public Response create(League entity) {
        em.persist(entity);
        return Response.created(
                UriBuilder.fromResource(LeagueService.class).path(String.valueOf(entity.getId()))
                        .build()).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Long id) {
        League entity = em.find(League.class, id);
        if (entity == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        em.remove(entity);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public Response findById(@PathParam("id") Long id) {
        TypedQuery<League> findByIdQuery = em
                .createQuery(
                        "SELECT DISTINCT l FROM League l LEFT JOIN FETCH l.matches LEFT JOIN FETCH l.teams WHERE l.id = :entityId ORDER BY l.id",
                        League.class);
        findByIdQuery.setParameter("entityId", id);
        League entity;
        try {
            entity = findByIdQuery.getSingleResult();
        } catch (NoResultException nre) {
            entity = null;
        }
        if (entity == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }

    @GET
    @Produces("application/json")
    public List<League> listAll(@QueryParam("start") Integer startPosition,
            @QueryParam("max") Integer maxResult) {
        TypedQuery<League> findAllQuery = em
                .createQuery(
                        "SELECT DISTINCT l FROM League l ORDER BY l.id",
                        League.class);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        final List<League> results = findAllQuery.getResultList();
        return results;
    }

    @GET
    @Path("/{id:[0-9][0-9]*}/matches")
    @Produces("application/json")
    public Set<Match> listAllMatchesInLeague(@PathParam("id") Long leagueId,
            @QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
        TypedQuery<League> findAllMatchesQuery = em.createQuery("SELECT l FROM League l LEFT JOIN FETCH l.matches WHERE l.id = :leagueId", League.class);
        findAllMatchesQuery.setParameter("leagueId", leagueId);
        if (startPosition != null) {
            findAllMatchesQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllMatchesQuery.setMaxResults(maxResult);
        }
        
        return findAllMatchesQuery.getSingleResult().getMatches();
    }
    
    @GET
    @Produces("application/json")
    @Path("/year/{year:[1-2][0-9][0-9][0-9]}")
    public List<League> listAllLeaguesByYear(@PathParam("year") Integer year) {
        TypedQuery<League> findAllLeaguesByYear = em.createQuery("SELECT l FROM League l where l.endYear = :year", League.class);
        findAllLeaguesByYear.setParameter("year", year);       
        return findAllLeaguesByYear.getResultList();
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes("application/json")
    public Response update(League entity) {
        entity = em.merge(entity);
        return Response.noContent().build();
    }
}