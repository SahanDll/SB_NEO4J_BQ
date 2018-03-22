package com.dev.db.query.filter;

import lombok.Data;

@Data
public class SettingMstFilter {
    private Long indexId;
    private Long indexIdGraterThan;
    private Long indexIdLessThan;
    private String clntNric;
}
