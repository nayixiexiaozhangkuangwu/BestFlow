package bestflow.api.controller;


import bestflow.api.response.RestResponse;
import bestflow.entity.FlowMain;
import bestflow.service.FlowMainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 工作流主任务表 前端控制器
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-23
 */
@Api(value = "任务管理")
@RestController
@RequestMapping("/flow-main")
public class FlowMainController {

    @Autowired
    private FlowMainService flowMainService;

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public RestResponse add(
            @RequestBody FlowMain flowMain
    ) {

        flowMainService.saveOrUpdate(flowMain);

        return RestResponse.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public RestResponse delete(
            @RequestBody List<Integer> ids
    ) {
        flowMainService.removeByIds(ids);

        return RestResponse.success();
    }

    @ApiOperation(value = "查询")
    @GetMapping("/list")
    public List<FlowMain> list() {

        return flowMainService.list();
    }

    @ApiOperation(value = "任务详情")
    @GetMapping("/list/detail")
    public RestResponse listDetail() {

        return RestResponse.success(flowMainService.getFlowInfo());
    }
}

