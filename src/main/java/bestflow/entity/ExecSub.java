package bestflow.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("exec_sub")
@ApiModel(value="FlowSub对象", description="")
public class ExecSub implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "操作对象ID")
    private String objId;

    @ApiModelProperty(value = "主任务ID")
    private String flowId;

    @ApiModelProperty(value = "子任务action")
    private String subAction;

    @ApiModelProperty(value = "任务流状态")
    private String subState;

    @ApiModelProperty(value = "错误信息")
    private String errMsg;

    @ApiModelProperty(value = "插入时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date insertDate;

    @ApiModelProperty(value = "更新日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;


}
