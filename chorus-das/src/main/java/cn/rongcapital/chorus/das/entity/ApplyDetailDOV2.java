package cn.rongcapital.chorus.das.entity;

import lombok.Data;

/**
 * Created by fuxiangli on 2016-11-28.
 */
@Data
public class ApplyDetailDOV2 {
    private Long applyDetailId;
    private String columnName;
    private String columnInfoId;
    private String columnDesc;
    private String securityLevel;
    private String statusName;
    private String reason;
    private String dealInstruction;
}
