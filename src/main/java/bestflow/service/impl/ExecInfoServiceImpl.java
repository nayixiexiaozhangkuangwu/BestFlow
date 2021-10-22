package bestflow.service.impl;

import bestflow.api.request.FlowBo;
import bestflow.api.response.ExecInfoVO;
import bestflow.entity.ExecInfo;
import bestflow.entity.ExecSub;
import bestflow.mapper.ExecInfoMapper;
import bestflow.service.ExecInfoService;
import bestflow.service.ExecSubService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-19
 */
@Service
public class ExecInfoServiceImpl extends ServiceImpl<ExecInfoMapper, ExecInfo> implements ExecInfoService {
    @Autowired
    private ExecSubService execSubService;

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
}
