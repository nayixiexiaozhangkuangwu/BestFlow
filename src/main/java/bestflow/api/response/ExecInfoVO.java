package bestflow.api.response;

import bestflow.entity.ExecInfo;
import bestflow.entity.ExecSub;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/*
任务流详情
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExecInfoVO extends ExecInfo {
    private List<ExecSub> execSubs;
}
