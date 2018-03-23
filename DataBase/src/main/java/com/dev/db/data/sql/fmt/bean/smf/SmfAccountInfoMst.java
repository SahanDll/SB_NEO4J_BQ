package com.dev.db.data.sql.fmt.bean.smf;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "AccountInfoMst", schema = "SMF")
@Data
public class SmfAccountInfoMst {

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
