package com.dev.db.query.filter;

import lombok.Data;

@Data
public class AccountInfoWatchlistFilter {
    private Long indexId;
    private Long indexIdGraterThan;
    private Long indexIdLessThan;
    private String clntNric;
    private String stkCode;
    private String stkName;
}
