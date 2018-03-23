package com.dev.db.data.sql.fmt.bean.ecos;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "AccountInfoPortfolio", schema = "ECOS")
@Data
public class EcosAccountInfoPortfolio {

    @Id
    @Column(name = "IndexId")
    private Long indexId;
    @Column(name = "ClntCode")
    private String clntCode;
    @Column(name = "StkCode")
    private String stkCode;
    @Column(name = "StkName")
    private String stkName;
    @Column(name = "AvgBuyPrice")
    private Double avgBuyPrice;
    @Column(name = "Quantity")
    private Integer quantity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastModified")
    private Date lastModified;
    @Transient
    private String schema = "ECOS";
}
