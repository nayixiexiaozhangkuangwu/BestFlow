package bestflow.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 工作流对接项目表
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("flow_project")
@ApiModel(value="FlowProject对象", description="工作流对接项目表")
public class FlowProject implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目URL")
    private String projectUrl;

    @ApiModelProperty(value = "项目描述")
    private String projectDesc;

    @ApiModelProperty(value = "插入时间")
    private Date insertDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;


}
