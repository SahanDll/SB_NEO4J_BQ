package com.dev.db.sql.bo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name="ClientMst", schema="dbo")
public class ClientMst {
    @Id
    @Column(name = "IndexId")
    private Long indexId;
    @Column(name = "ClntNric")
    private String clntNric;
    @Column(name = "ClntName")
    private String clntName;


    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public String getClntNric() {
        return clntNric;
    }

    public void setClntNric(String clntNric) {
        this.clntNric = clntNric;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    @Override
    public String toString() {
        return "ClientMst{" +
                "indexId=" + indexId +
                ", clntNric='" + clntNric + '\'' +
                ", clntName='" + clntName + '\'' +
                '}';
    }
}
