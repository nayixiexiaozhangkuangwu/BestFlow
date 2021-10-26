package bestflow.api.response;

import bestflow.entity.FlowSub;
import lombok.Data;

import java.util.List;

@Data
public class FlowInfoVO {
    private Integer mainId;
    private String mainName;
    private Integer projectId;
    private String projectName;
    private String projectUrl;
    private String projectDesc;
    private List<FlowSub> subList;

}
