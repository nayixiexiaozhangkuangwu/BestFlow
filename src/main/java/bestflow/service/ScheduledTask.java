package bestflow.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private FlowMainService flowMainService;

//    @Scheduled(fixedRate = 2000)
//    public void flowRun() {
//
//        log.info("time run at " + LocalDateTime.now());
////        List<FlowInfoVO> flowInfoVOS = flowMainService.getFlowInfo();
////
////        for (FlowInfoVO infoVO : flowInfoVOS) {
////
////        }
//
//
//    }
}
