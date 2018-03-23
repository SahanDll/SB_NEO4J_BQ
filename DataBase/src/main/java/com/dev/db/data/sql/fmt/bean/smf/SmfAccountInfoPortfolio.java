package com.dev.db.data.sql.fmt.bean.smf;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "AccountInfoPortfolio", schema = "SMF")
@Data
public class SmfAccountInfoPortfolio {

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
    private String schema = "SMF";
}
