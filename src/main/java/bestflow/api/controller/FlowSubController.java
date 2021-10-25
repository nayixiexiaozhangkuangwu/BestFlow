package bestflow.api.controller;


import bestflow.api.response.RestResponse;
import bestflow.entity.FlowSub;
import bestflow.service.FlowSubService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 工作流子任务表 前端控制器
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-23
 */
@RestController
@RequestMapping("/flow-sub")
public class FlowSubController {

    @Autowired
    private FlowSubService flowSubService;

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public RestResponse add(
            @RequestBody List<FlowSub> flowSubs
    ) {
        flowSubService.saveOrUpdateBatch(flowSubs);

        return RestResponse.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public RestResponse delete(@RequestBody List<Integer> ids) {

        flowSubService.removeByIds(ids);

        return RestResponse.success();
    }

    @ApiOperation(value = "查询")
    @GetMapping("/list")
    public List<FlowSub> list() {

        return flowSubService.list();
    }

}

