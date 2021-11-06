package bestflow.service;

import bestflow.entity.ExecInfo;
import bestflow.entity.ExecState;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private ExecInfoService execInfoService;


    @Scheduled(fixedRate = 5000)
    public void flowRun() throws InterruptedException {

        log.info("time run at " + LocalDateTime.now());
        List<ExecInfo> execInfos = execInfoService.list(new QueryWrapper<ExecInfo>().eq("flow_state", ExecState.Init).orderByDesc("insert_date"));
        for (ExecInfo execInfo : execInfos) {
            execInfo.setFlowState(ExecState.Running);
            execInfoService.updateById(execInfo);
            execInfoService.execFlow(execInfo);
        }
    }
}
