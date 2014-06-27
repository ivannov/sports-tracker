package org.ivko.tracker.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;

import org.ivko.tracker.model.Match;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.OneToMany;

import org.ivko.tracker.model.Team;

import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class League implements Serializable
{

   private static final long serialVersionUID = 6049109500011380283L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String name;

   @Column
   private String country;

   @Column
   private int startYear;

   @Column
   private int endYear;

   @OneToMany
   private Set<Match> matches = new HashSet<Match>();

   @Column
   private int level;

   @ManyToMany(mappedBy = "leagues")
   private Set<Team> teams = new HashSet<Team>();

   public League() {       
   }
   
    public League(String name, String country, int startYear, int endYear, int level) {
        this.name = name;
        this.country = country;
        this.startYear = startYear;
        this.endYear = endYear;
        this.level = level;
    }

    public Long getId() {
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
      if (!(obj instanceof League))
      {
         return false;
      }
      League other = (League) obj;
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
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   public int getStartYear()
   {
      return startYear;
   }

   public void setStartYear(int startYear)
   {
      this.startYear = startYear;
   }

   public int getEndYear()
   {
      return endYear;
   }

   public void setEndYear(int endYear)
   {
      this.endYear = endYear;
   }

   public Set<Match> getMatches()
   {
      return this.matches;
   }

   public void setMatches(final Set<Match> matches)
   {
      this.matches = matches;
   }

   public int getLevel()
   {
      return level;
   }

   public void setLevel(int level)
   {
      this.level = level;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (name != null && !name.trim().isEmpty())
         result += "name: " + name;
      if (country != null && !country.trim().isEmpty())
         result += ", country: " + country;
      result += ", startYear: " + startYear;
      result += ", endYear: " + endYear;
      result += ", level: " + level;
      return result;
   }

   public Set<Team> getTeams()
   {
      return this.teams;
   }

   public void setTeams(final Set<Team> teams)
   {
      this.teams = teams;
   }
}