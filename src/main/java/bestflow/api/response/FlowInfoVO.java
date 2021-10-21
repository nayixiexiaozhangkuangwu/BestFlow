package bestflow.api.response;

import bestflow.entity.FlowInfo;
import bestflow.entity.FlowSub;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/*
任务流详情
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FlowInfoVO extends FlowInfo {
    private List<FlowSub> flowSubs;
}
