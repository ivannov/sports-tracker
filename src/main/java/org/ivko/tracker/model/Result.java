package org.ivko.tracker.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TRESULT")
@XmlRootElement
public class Result implements Serializable {

    private static final long serialVersionUID = 3533562686183926717L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @Version
    @Column(name = "version")
    private int version = 0;

    @Column
    private Integer team1Score;

    @Column
    private Integer team2Score;

    public Result() {
    }

    public Result(Integer team1Score, Integer team2Score) {
        this.team1Score = team1Score;
        this.team2Score = team2Score;
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
        if (!(obj instanceof Result)) {
            return false;
        }
        Result other = (Result) obj;
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

    public Integer getTeam1Score() {
        return this.team1Score;
    }

    public void setTeam1Score(final Integer team1Score) {
        this.team1Score = team1Score;
    }

    public Integer getTeam2Score() {
        return this.team2Score;
    }

    public void setTeam2Score(final Integer team2Score) {
        this.team2Score = team2Score;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (team1Score != null)
            result += "team1Score: " + team1Score;
        if (team2Score != null)
            result += ", team2Score: " + team2Score;
        return result;
    }
}