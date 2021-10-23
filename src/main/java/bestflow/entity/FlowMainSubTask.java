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
 * 任务关联表
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("flow_main_sub_task")
@ApiModel(value="FlowMainSubTask对象", description="任务关联表")
public class FlowMainSubTask implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "主任务ID")
    private Integer mainId;

    @ApiModelProperty(value = "子任务ID")
    private Integer subId;

    @ApiModelProperty(value = "项目ID")
    private Integer projectId;

    @ApiModelProperty(value = "子任务执行级别")
    private Integer execLevel;

    @ApiModelProperty(value = "插入时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date insertDate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;


}
