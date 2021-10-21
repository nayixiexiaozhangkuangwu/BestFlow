package bestflow.service.impl;

import bestflow.api.request.FlowBo;
import bestflow.api.response.FlowInfoVO;
import bestflow.entity.FlowInfo;
import bestflow.entity.FlowSub;
import bestflow.mapper.FlowInfoMapper;
import bestflow.service.FlowInfoService;
import bestflow.service.FlowSubService;
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
public class FlowInfoServiceImpl extends ServiceImpl<FlowInfoMapper, FlowInfo> implements FlowInfoService {
    @Autowired
    private FlowSubService flowSubService;

    @Override
    public List<FlowInfoVO> listAll(FlowBo bo) {
        QueryWrapper<FlowInfo> queryWrapper = new QueryWrapper<>();

        if (StringUtils.hasLength(bo.getFlowId())) {
            queryWrapper.eq("id", bo.getFlowId());
        }

        List<FlowInfo> voList = list(queryWrapper);

        List<FlowInfoVO> flowInfoVOS = new ArrayList<>();

        QueryWrapper<FlowSub> flowSubQueryWrapper = new QueryWrapper<>();

        if (StringUtils.hasLength(bo.getFlowId())) {
            flowSubQueryWrapper.eq("flow_id", bo.getFlowId());
        }

        List<FlowSub> flowSubs = flowSubService.list(flowSubQueryWrapper);

        for (FlowInfo flowInfo : voList) {

            FlowInfoVO flowInfoVO = new FlowInfoVO();
            BeanUtils.copyProperties(flowInfo, flowInfoVO);
            flowSubs = flowSubs.stream().filter(flowSub -> flowSub.getFlowId().equals(flowInfo.getId())).collect(Collectors.toList());
            flowInfoVO.setFlowSubs(flowSubs);

            flowInfoVOS.add(flowInfoVO);
        }

        return flowInfoVOS;
    }
}
