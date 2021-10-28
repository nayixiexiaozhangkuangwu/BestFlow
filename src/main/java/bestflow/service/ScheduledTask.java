package bestflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTask {

    @Autowired
    private FlowMainService flowMainService;

    @Scheduled(fixedRate = 2000)
    public void flowRun() {

        System.out.println("time run at " + LocalDateTime.now());
//        List<FlowInfoVO> flowInfoVOS = flowMainService.getFlowInfo();
//
//        for (FlowInfoVO infoVO : flowInfoVOS) {
//
//        }


    }
}
