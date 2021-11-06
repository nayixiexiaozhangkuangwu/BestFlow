package bestflow.service.impl;

import bestflow.api.request.FlowBo;
import bestflow.api.response.ExecInfoVO;
import bestflow.entity.*;
import bestflow.mapper.ExecInfoMapper;
import bestflow.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-19
 */
@Service
@Slf4j
public class ExecInfoServiceImpl extends ServiceImpl<ExecInfoMapper, ExecInfo> implements ExecInfoService {
    @Autowired
    private ExecSubService execSubService;
    @Autowired
    private FlowMainService flowMainService;
    @Autowired
    private FlowSubService flowSubService;
    @Autowired
    private FlowMainSubTaskService flowMainSubTaskService;


    @Override
    public List<ExecInfoVO> listAll(FlowBo bo) {
        QueryWrapper<ExecInfo> queryWrapper = new QueryWrapper<>();

        if (StringUtils.hasLength(bo.getFlowId())) {
            queryWrapper.eq("id", bo.getFlowId());
        }

        List<ExecInfo> voList = list(queryWrapper);

        List<ExecInfoVO> flowInfoVOS = new ArrayList<>();

        QueryWrapper<ExecSub> flowSubQueryWrapper = new QueryWrapper<>();

        if (StringUtils.hasLength(bo.getFlowId())) {
            flowSubQueryWrapper.eq("flow_id", bo.getFlowId());
        }

        List<ExecSub> execSubs = execSubService.list(flowSubQueryWrapper);

        for (ExecInfo execInfo : voList) {

            ExecInfoVO flowInfoVO = new ExecInfoVO();
            BeanUtils.copyProperties(execInfo, flowInfoVO);
            execSubs = execSubs.stream().filter(flowSub -> flowSub.getFlowId().equals(execInfo.getId())).collect(Collectors.toList());
            flowInfoVO.setExecSubs(execSubs);

            flowInfoVOS.add(flowInfoVO);
        }

        return flowInfoVOS;
    }

    @Override
    @Async
    public void execFlow(ExecInfo execInfo) {
        String flowMainAction = execInfo.getFlowAction();
        FlowMain flowMain = flowMainService.getOne(new QueryWrapper<FlowMain>().eq("flow_name", flowMainAction));

        if (flowMain.getIsValid().equals(1)) {

            List<FlowMainSubTask> flowMainSubTasks = flowMainSubTaskService.list(new QueryWrapper<FlowMainSubTask>().orderByAsc("exec_level"));
            for (FlowMainSubTask flowMainSubTask : flowMainSubTasks) {
                FlowSub flowSub = flowSubService.getById(flowMainSubTask.getSubId());

                log.info(flowSub.getSubName());

                ExecSub execSub = new ExecSub();
                execSub.setId(UUID.randomUUID().toString());
                execSub.setObjId(flowMain.getObjId());
                execSub.setFlowId(execInfo.getId());
                execSub.setSubAction(flowSub.getSubName());
                execSub.setSubState(ExecState.Running.name());
                execSubService.save(execSub);

                if (!getResult(execSub.getId())) {
                    execInfo.setFlowState(ExecState.Failed);
                    updateById(execInfo);
                    return;
                }
            }

            execInfo.setFlowState(ExecState.Completed);
            updateById(execInfo);
        }


    }

    private boolean getResult(String subTaskId) {

        ExecSub execSub = execSubService.getById(subTaskId);
        int retryCount = 0;
        while (execSub.getSubState().equals(ExecState.Running.name())) {
            log.info(String.format("[%s]%s state %s", execSub.getFlowId(), execSub.getSubAction(), execSub.getSubState()));

            if (retryCount > 10) {
                execSub.setSubState(ExecState.Running.name());
                return false;
            }
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            execSub = execSubService.getById(subTaskId);

            ++retryCount;
        }

        return true;

    }
}
