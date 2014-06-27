package org.ivko.tracker.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
import java.util.HashSet;
import org.ivko.tracker.model.League;
import javax.persistence.ManyToMany;

@Entity
@XmlRootElement
public class Team implements Serializable
{

   private static final long serialVersionUID = -7418427691465048397L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String name;

   @Column
   private String city;

   @Column
   private String country;

   @Column
   private Integer foundationYear;

   @Column
   private String stadium;

   @ManyToMany
   private Set<League> leagues = new HashSet<League>();

   public Team()
   {
   }

   public Team(String name, String city)
   {
      this.name = name;
      this.city = city;
   }

   public Team(String name, String city, String country, Integer foundationYear, String stadium)
   {
      this.name = name;
      this.city = city;
      this.country = country;
      this.foundationYear = foundationYear;
      this.stadium = stadium;
   }

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Team))
      {
         return false;
      }
      Team other = (Team) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   public String getCity()
   {
      return this.city;
   }

   public void setCity(final String city)
   {
      this.city = city;
   }

   public String getCountry()
   {
      return this.country;
   }

   public void setCountry(final String country)
   {
      this.country = country;
   }

   public Integer getFoundationYear()
   {
      return this.foundationYear;
   }

   public void setFoundationYear(final Integer foundationYear)
   {
      this.foundationYear = foundationYear;
   }

   public String getStadium()
   {
      return this.stadium;
   }

   public void setStadium(final String stadium)
   {
      this.stadium = stadium;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (name != null && !name.trim().isEmpty())
         result += "name: " + name;
      if (city != null && !city.trim().isEmpty())
         result += ", city: " + city;
      if (country != null && !country.trim().isEmpty())
         result += ", country: " + country;
      if (foundationYear != null)
         result += ", foundationYear: " + foundationYear;
      if (stadium != null && !stadium.trim().isEmpty())
         result += ", stadium: " + stadium;
      return result;
   }

   public Set<League> getLeagues()
   {
      return this.leagues;
   }

   public void setLeagues(final Set<League> leagues)
   {
      this.leagues = leagues;
   }
}