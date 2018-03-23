package com.dev.db.query.filter;

import lombok.Data;

@Data
public class AccountInfoPortfolioFilter {
    private Long indexId;
    private Long indexIdGraterThan;
    private Long indexIdLessThan;
    private String clntCode;
    private String stkCode;
    private String stkName;
}
