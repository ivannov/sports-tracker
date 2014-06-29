package org.ivko.tracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.joda.time.DateTime;

@Entity
@Table(name = "TMATCH")
@XmlRootElement
public class Match implements Serializable {

    private static final long serialVersionUID = -5569640326597506894L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @Version
    @Column(name = "version")
    private int version = 0;

    @Column
    @OneToOne
    private Team team1;

    @Column
    @OneToOne
    private Team team2;

    @Column
    private Integer team1Result;
    
    @Column
    private Integer team2Result;
 
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date matchTime;
    
    @ManyToOne
    private League league;

    public Match() {
    }

    public Match(Team team1, Team team2, Integer team1Result, Integer team2Result, League league, DateTime matchTime) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Result = team1Result;
        this.team2Result = team2Result;
        this.league = league;
        this.matchTime = matchTime.toDate();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Match)) {
            return false;
        }
        Match other = (Match) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public Team getTeam1() {
        return this.team1;
    }

    public void setTeam1(final Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return this.team2;
    }

    public void setTeam2(final Team team2) {
        this.team2 = team2;
    }

    public Date getMatchTime() {
        return this.matchTime;
    }

    public void setMatchTime(final Date matchTime) {
        this.matchTime = matchTime;
    }
    
    @XmlTransient
    public DateTime getJodaMatchTime() {
        return new DateTime(matchTime.getTime());
    }

    public void setJodaMatchTime(final DateTime matchTime) {
        this.matchTime = matchTime.toDate();
    }    

    public Integer getTeam1Result() {
        return team1Result;
    }

    public void setTeam1Result(Integer team1Result) {
        this.team1Result = team1Result;
    }

    public Integer getTeam2Result() {
        return team2Result;
    }

    public void setTeam2Result(Integer team2Result) {
        this.team2Result = team2Result;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        result += "serialVersionUID: " + serialVersionUID;
        return result;
    }
}