package bestflow.api.controller;


import bestflow.api.request.FlowBo;
import bestflow.api.response.RestResponse;
import bestflow.service.ExecInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author weijie.wu
 * @since 2021-10-19
 */
@RestController
@RequestMapping("/flow-info")
public class ExecInfoController {

    @Autowired
    private ExecInfoService execInfoService;

    @ApiOperation(value = "查看工作流执行情况")
    @PostMapping("/list")
    public RestResponse list(@RequestBody FlowBo bo) {

        return RestResponse.success(execInfoService.listAll(bo));
    }


}

