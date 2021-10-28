package bestflow.service.impl;

import bestflow.api.response.FlowInfoVO;
import bestflow.entity.FlowMain;
import bestflow.entity.FlowMainSubTask;
import bestflow.entity.FlowProject;
import bestflow.entity.FlowSub;
import bestflow.mapper.FlowMainMapper;
import bestflow.service.FlowMainService;
import bestflow.service.FlowMainSubTaskService;
import bestflow.service.FlowProjectService;
import bestflow.service.FlowSubService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 工作流主任务表 服务实现类
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-23
 */
@Service
public class FlowMainServiceImpl extends ServiceImpl<FlowMainMapper, FlowMain> implements FlowMainService {

    @Autowired
    private FlowMainSubTaskService flowMainSubTaskService;

    @Autowired
    private FlowSubService flowSubService;

    @Autowired
    private FlowProjectService flowProjectService;

    @Override
    public List<FlowInfoVO> getFlowInfo() {
        List<FlowInfoVO> flowInfoVOS = new ArrayList<>();

        List<FlowMain> flowMains = list(new QueryWrapper<FlowMain>().eq("is_valid", 1));
        for (FlowMain flowMain : flowMains) {
            FlowInfoVO flowInfoVO = new FlowInfoVO();
            flowInfoVO.setMainId(flowMain.getId());
            flowInfoVO.setMainName(flowMain.getFlowName());

            List<FlowMainSubTask> flowMainSubTasks = flowMainSubTaskService.list(new QueryWrapper<FlowMainSubTask>().eq("main_id", flowMain.getId()));

            if (!flowMainSubTasks.isEmpty()) {
                Integer projectId = flowMainSubTasks.get(0).getProjectId();
                FlowProject project = flowProjectService.getById(projectId);
                flowInfoVO.setProjectId(projectId);
                flowInfoVO.setProjectDesc(project.getProjectDesc());
                flowInfoVO.setProjectUrl(project.getProjectUrl());

                List<FlowSub> flowSubs = new ArrayList<>();
                for (FlowMainSubTask flowMainSubTask : flowMainSubTasks) {
                    FlowSub flowSub = flowSubService.getById(flowMainSubTask.getSubId());
                    flowSub.setExecLevel(flowMainSubTask.getExecLevel());
                    flowSubs.add(flowSub);
                }
                flowInfoVO.setSubList(flowSubs);
            }
            flowInfoVOS.add(flowInfoVO);
        }

        return flowInfoVOS;
    }
}
