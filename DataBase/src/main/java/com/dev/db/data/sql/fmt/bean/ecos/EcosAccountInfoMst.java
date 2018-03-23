package com.dev.db.data.sql.fmt.bean.ecos;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "AccountInfoMst", schema = "ECOS")
@Data
public class EcosAccountInfoMst {

    @Id
    @Column(name = "IndexId")
    private Long indexId;
    @Column(name = "ClntCode")
    private String clntCode;
    @Column(name = "ClntNICNo")
    private String clntNICNo;
    @Column(name = "ClntOICNo")
    private String clntOICNo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastModified")
    private Date lastModified;
}
