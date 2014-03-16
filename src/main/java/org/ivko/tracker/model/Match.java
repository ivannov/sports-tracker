package org.ivko.tracker.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import org.ivko.tracker.model.Team;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.lang.Override;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Result result;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date matchDate;

    public Match() {
    }

    public Match(Team team1, Team team2, Result result, Date matchDate) {
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;
        this.matchDate = matchDate;
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

    public Date getMatchDate() {
        return this.matchDate;
    }

    public void setMatchDate(final Date matchDate) {
        this.matchDate = matchDate;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        result += "serialVersionUID: " + serialVersionUID;
        return result;
    }
}