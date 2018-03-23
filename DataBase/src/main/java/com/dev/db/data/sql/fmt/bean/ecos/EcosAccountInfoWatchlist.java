package com.dev.db.data.sql.fmt.bean.ecos;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "AccountInfoWatchlist", schema = "ECOS")
@Data
public class EcosAccountInfoWatchlist {

    @Id
    @Column(name = "IndexId")
    private Long indexId;
    @Column(name = "WlGrpId")
    private String wlGrpId;
    @Column(name = "StkCode")
    private String stkCode;
    @Column(name = "StkName")
    private String stkName;
    @Column(name = "ClntNICNo")
    private String clntNICNo;
    @Column(name = "TargetPrice")
    private Double targetPrice;
    @Column(name = "Quantity")
    private Integer quantity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastModified")
    private Date lastModified;
    @Transient
    private String schema = "ECOS";
}
