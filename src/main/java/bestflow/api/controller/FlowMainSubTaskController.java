package bestflow.api.controller;


import bestflow.api.response.RestResponse;
import bestflow.entity.FlowMainSubTask;
import bestflow.service.FlowMainSubTaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 任务关联表 前端控制器
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-23
 */
@RestController
@RequestMapping("/flow-main-sub-task")
public class FlowMainSubTaskController {

    @Autowired
    private FlowMainSubTaskService flowMainSubTaskService;

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public RestResponse add(@RequestBody List<FlowMainSubTask> tasks) {

        flowMainSubTaskService.saveOrUpdateBatch(tasks);

        return RestResponse.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public RestResponse delete(@RequestBody List<Integer> ids) {

        flowMainSubTaskService.removeByIds(ids);

        return RestResponse.success();
    }

}

