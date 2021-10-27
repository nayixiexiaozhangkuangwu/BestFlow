package bestflow.service;

import bestflow.api.response.FlowInfoVO;
import bestflow.entity.FlowMain;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工作流主任务表 服务类
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-23
 */
public interface FlowMainService extends IService<FlowMain> {

    /*
    查看任务流信息
     */
    List<FlowInfoVO> getFlowInfo();

}
