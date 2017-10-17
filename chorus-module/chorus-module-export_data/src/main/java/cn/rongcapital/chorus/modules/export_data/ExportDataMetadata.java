package cn.rongcapital.chorus.modules.export_data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.xd.module.options.spi.*;
import org.springframework.xd.module.options.spi.Source;
import org.springframework.xd.module.options.spi.page.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.xd.module.options.spi.page.PageElementType.*;

/**
 * Created by alan on 24/04/2017.
 */
@Mixin({})
public class ExportDataMetadata {

    private Long projectId;
    private String tableId;
    private String externalRDB;
    private String dwConnectUrl;
    private String dwDbName;
    private String dwTableName;
    private String dwUserName;
    private String dwLocation;
    private String rdbName;
    private String rdbConnectUrl;
    private String rdbUserName;
    private String rdbPassword;
    private String rdbTable;
    private String where;
    private String columnNameMapStr;
    private String exportStrategy;
    private Integer retryCount;

    private static transient List<PageOption> exportStrategyList = new ArrayList<PageOption>() {{
        add(new PageOption("全量导出", ExportStrategy.ALL));
        add(new PageOption("sql导出", ExportStrategy.SQL));
    }};

    @PageElement(InputText)
    public Long getProjectId() {
        return projectId;
    }

    @ModuleOption(value = "project id ", hidden = true, achieveMethod = AchieveMethod.CONTEXT)
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @PageElement(InputText)
    public String getTableId() {
        return tableId;
    }

    @ModuleOption(value = "internal table id", hidden = true, from = {"rdbTable"})
    @Action(broadcast = {"columnNameMapStr"})
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @PageElement(DropDown)
    public String getExternalRDB() {
        return externalRDB;
    }

    @ModuleOption(value = "external RDB", to = {"rdbConnectUrl", "rdbUserName", "rdbPassword", "rdbTable"}, order = 4)
    @Source(url = "/external_datasource/rdb", param = {@SourceParam(key = "projectId", value = "projectId")},
            metaData = {
                    @SourceMetaData(key = "value", value = "name"),
                    @SourceMetaData(key = "displayValue", value = "name"),
            }
    )

    @Action(assign = true, broadcast = {"rdbTable"})
    @AssignAction(options = {
            @AssignActionOption(key = "rdbName", value = "name"),
            @AssignActionOption(key = "rdbConnectUrl", value = "connUrl"),
            @AssignActionOption(key = "rdbUserName", value = "connUser"),
            @AssignActionOption(key = "rdbPassword", value = "connPass"),
    })
    public void setExternalRDB(String externalRDB) {
        this.externalRDB = externalRDB;
    }

    @PageElement(Radio)
    @PageOptionList("exportStrategyList")
    public String getExportStrategy() {
        return exportStrategy;
    }

    @ModuleOption(value = "export strategy ", to = {"where"}, order = 2)
    @Action(control = true)
    @ControlAction(options = {
            @ControlActionOption(key = "ALL", display = {}, hidden = {"where"}),
            @ControlActionOption(key = "SQL", display = {"where"}, hidden = {}),
    })
    public void setExportStrategy(String exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    @PageElement(InputText)
    @NotNull
    public String getDwConnectUrl() {
        return dwConnectUrl;
    }

    @ModuleOption(value = "dw connect url", hidden = true, achieveMethod = AchieveMethod.CONTEXT)
    public void setDwConnectUrl(String dwConnectUrl) {
        this.dwConnectUrl = dwConnectUrl;
    }

    @PageElement(InputText)
    @NotNull
    public String getDwDbName() {
        return dwDbName;
    }

    @ModuleOption(value = "dw db name", hidden = true, achieveMethod = AchieveMethod.CONTEXT)
    public void setDwDbName(String dwDbName) {
        this.dwDbName = dwDbName;
    }

    @PageElement(DropDown)
    @NotNull
    public String getDwTableName() {
        return dwTableName;
    }

    @ModuleOption(value = "dw table name", from = {"projectId"}, order = 1)
    @Source(url = "/internal_datasource/table", param = {
            @SourceParam(key = "projectId", value = "projectId")
    }, metaData = {
            @SourceMetaData(key = "value", value = "name"),
            @SourceMetaData(key = "displayValue", value = "name"),
    })
    @Action(assign = true)
    @AssignAction(options = {
            @AssignActionOption(key = "tableId", value = "id")
    })
    public void setDwTableName(String dwTableName) {
        this.dwTableName = dwTableName;
    }

    @PageElement(InputText)
    @NotNull
    public String getDwUserName() {
        return dwUserName;
    }

    @ModuleOption(value = "dw user name", hidden = true, defaultValue = "hive")
    public void setDwUserName(String dwUserName) {
        this.dwUserName = dwUserName;
    }

    @PageElement(InputText)
    @NotNull
    public String getDwLocation() {
        return dwLocation;
    }

    @ModuleOption(value = "dw file location", hidden = true)
    public void setDwLocation(String dwLocation) {
        this.dwLocation = dwLocation;
    }

    @PageElement(InputText)
    public String getRdbName() {
        return rdbName;
    }

    @ModuleOption(value = "rdb name",hidden = true, from = {"externalRDB"})
    public void setRdbName(String rdbName) {
        this.rdbName = rdbName;
    }

    @PageElement(InputText)
    @NotNull
    public String getRdbConnectUrl() {
        return rdbConnectUrl;
    }

    @ModuleOption(value = "rdb connect url",hidden = true, from = {"externalRDB"})
    @Action(broadcast = {"rdbTable"})
    public void setRdbConnectUrl(String rdbConnectUrl) {
        this.rdbConnectUrl = rdbConnectUrl;
    }

    @PageElement(InputText)
    @NotNull
    public String getRdbUserName() {
        return rdbUserName;
    }

    @ModuleOption(value = "rdb user name",hidden = true, from = {"externalRDB"})
    @Action(broadcast = {"rdbTable"})
    public void setRdbUserName(String rdbUserName) {
        this.rdbUserName = rdbUserName;
    }

    @PageElement(Password)
    @NotNull
    public String getRdbPassword() {
        return rdbPassword;
    }

    @ModuleOption(value = "rdb password",hidden = true, from = {"externalRDB"})
    @Action(broadcast = {"rdbTable"})
    public void setRdbPassword(String rdbPassword) {
        this.rdbPassword = rdbPassword;
    }

    @PageElement(DropDown)
    public String getRdbTable() {
        return rdbTable;
    }

    @ModuleOption(value = "rdb table", from = {"rdbConnectUrl", "rdbUserName", "rdbPassword"}, order = 5)
    @Source(url = "/external_datasource/table", param = {
            @SourceParam(key = "url", value = "rdbConnectUrl"),
            @SourceParam(key = "userName", value = "rdbUserName"),
            @SourceParam(key = "password", value = "rdbPassword"),
    }, metaData = {
            @SourceMetaData(key = "value", value = "name"),
            @SourceMetaData(key = "displayValue", value = "name"),
    })
    @Action(broadcast = {"columnNameMapStr"})
    public void setRdbTable(String rdbTable) {
        this.rdbTable = rdbTable;
    }

    @PageElement(TextArea)
    @Variable("[{\"name\":\"$whereToday\", \"desc\":\"where内可用变量, 默认执行当天日期（yyyy-MM-dd）\"}," +
            "{\"name\":\"$whereYesterday\", \"desc\":\"where内可用变量, 默认执行前一天日期（yyyy-MM-dd）\"}," +
            "{\"name\":\"$whereVar1\", \"desc\":\"where内可用变量1\"}," +
            "{\"name\":\"$whereVar2\", \"desc\":\"where内可用变量2\"}," +
            "{\"name\":\"$whereVar3\", \"desc\":\"where内可用变量3\"}," +
            "{\"name\":\"$whereVar4\", \"desc\":\"where内可用变量4\"}," +
            "{\"name\":\"$whereVar5\", \"desc\":\"where内可用变量5\"}]")
    public String getWhere() {
        return where;
    }

    @ModuleOption(value = "dw where clause", defaultValue = "", from = {"exportStrategy"}, order = 3)
    public void setWhere(String where) {
        this.where = where;
    }

    @PageElement(MultiPairTable)
    @NotNull
    @Size(min = 1)
    public String getColumnNameMapStr() {
        return columnNameMapStr;
    }

    @ModuleOption(value = "rdb to dw column name mapping", from = {"rdbTable", "dwTableName"}, order = 6)
    @Sources(
            {
                    @Source(url = "/external_datasource/field", param = {
                            @SourceParam(key = "url", value = "rdbConnectUrl"),
                            @SourceParam(key = "userName", value = "rdbUserName"),
                            @SourceParam(key = "password", value = "rdbPassword"),
                            @SourceParam(key = "table", value = "rdbTable"),
                    }, metaData = {
                            @SourceMetaData(key = "value", value = "name"),
                            @SourceMetaData(key = "displayValue", value = "name"),
                    }),
                    @Source(url = "/internal_datasource/field", param = {
                            @SourceParam(key = "tableId", value = "tableId")
                    }, metaData = {
                            @SourceMetaData(key = "value", value = "name"),
                            @SourceMetaData(key = "displayValue", value = "name"),
                    })
            }
    )
    public void setColumnNameMapStr(String columnNameMapStr) {
        this.columnNameMapStr = columnNameMapStr;
    }

    @PageElement(InputText)
    @Min(0)
    @Max(3)
    public Integer getRetryCount() {
        return retryCount;
    }

    @ModuleOption(value = "retry count", defaultValue = "0")
    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }
}

enum ExportStrategy {
    ALL, SQL
}
