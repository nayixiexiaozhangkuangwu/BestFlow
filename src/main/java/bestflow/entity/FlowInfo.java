package bestflow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("flow_info")
@ApiModel(value="FlowInfo对象", description="")
public class FlowInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "操作对象ID")
    private String objId;

    @ApiModelProperty(value = "任务流action")
    private String flowAction;

    @ApiModelProperty(value = "任务流详情")
    private String flowParam;

    @ApiModelProperty(value = "任务流状态")
    private String flowState;

    @ApiModelProperty(value = "错误信息")
    private String errMsg;

    @ApiModelProperty(value = "插入时间")
    private Date insertDate;

    @ApiModelProperty(value = "更新日期")
    private Date updateDate;


}
