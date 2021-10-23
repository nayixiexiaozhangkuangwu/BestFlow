package bestflow.api.controller;


import bestflow.api.response.RestResponse;
import bestflow.entity.FlowProject;
import bestflow.service.FlowProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 工作流对接项目表 前端控制器
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-23
 */
@RestController
@RequestMapping("/flow-project")
public class FlowProjectController {

    @Autowired
    private FlowProjectService flowProjectService;

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public RestResponse add(
            @RequestBody FlowProject flowProject
    ) {
        flowProjectService.saveOrUpdate(flowProject);

        return RestResponse.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public RestResponse delete(
            @RequestBody List<Integer> ids
    ) {
        flowProjectService.removeByIds(ids);

        return RestResponse.success();
    }
}

