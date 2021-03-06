package cn.rongcapital.chorus.das.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by fuxiangli on 2016-11-30.
 */
@Data
public class TableAuthorityWithTableDO {
    private Long projectId;

    private String projectCode;

    private String projectName;

    private Long tableInfoId;

    private String tableName;

    private String userId;

    private Date endDate;
}
