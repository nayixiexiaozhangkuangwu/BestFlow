package bestflow.service;

import bestflow.api.request.FlowBo;
import bestflow.api.response.FlowInfoVO;
import bestflow.entity.FlowInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-19
 */
public interface FlowInfoService extends IService<FlowInfo> {

    /*
    查看查看任务流执行情况
     */
    List<FlowInfoVO> listAll(FlowBo bo);

}
