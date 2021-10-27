package bestflow;

import bestflow.api.controller.FlowMainController;
import bestflow.api.controller.FlowMainSubTaskController;
import bestflow.api.controller.FlowProjectController;
import bestflow.api.controller.FlowSubController;
import bestflow.entity.FlowMain;
import bestflow.entity.FlowMainSubTask;
import bestflow.entity.FlowProject;
import bestflow.entity.FlowSub;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ApplicationTests {

    @Test
    void genCode() {
        // 1、创建代码生成器

        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath:" + projectPath);
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("weijie.wu");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        /*
         * mp生成service层代码，默认接口名称第一个字母有 I
         * UcenterService
         * */
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setEntityName("%s");
        gc.setIdType(IdType.ASSIGN_UUID); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/best_flow?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(""); //模块名
        pc.setParent("bestflow");
        pc.setController("api.controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("flow_sub");
//        strategy.setFieldPrefix("operate_");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }

    @Autowired
    private FlowProjectController flowProjectController;

    @Autowired
    private FlowMainController flowMainController;

    @Autowired
    private FlowSubController flowSubController;

    @Autowired
    private FlowMainSubTaskController flowMainSubTaskController;

    @Test
    void createTask() {

        deleteData();
        insertData();

    }

    private void deleteData() {
        flowProjectController.delete(flowProjectController.list().stream().map(FlowProject::getId).collect(Collectors.toList()));
        flowMainController.delete(flowMainController.list().stream().map(FlowMain::getId).collect(Collectors.toList()));
        flowSubController.delete(flowSubController.list().stream().map(FlowSub::getId).collect(Collectors.toList()));
        flowMainSubTaskController.delete(flowMainSubTaskController.list().stream().map(FlowMainSubTask::getId).collect(Collectors.toList()));
    }

    private void insertData() {
        /*添加项目-test*/
        FlowProject flowProject = new FlowProject();
        flowProject.setProjectDesc("test");
        flowProject.setProjectUrl("http://test");
        flowProject.setProjectName("test-wu");
        flowProjectController.add(flowProject);

        /*主任务-做红烧肉*/
        FlowMain flowMain = new FlowMain();
        flowMain.setFlowName("braised_pork_in_soy_sauce");
        flowMainController.add(flowMain);

        /*子任务-*/
        List<FlowSub> flowSubs = new ArrayList<>();
        List<String> subs = Arrays.asList("cut_meat", "wash_meat", "fry_meat", "add_chili", "cook_meat", "add_sugar");
        int i = 0;
        for (String sub: subs) {

            FlowSub flowSub = new FlowSub();
            flowSub.setSubName(sub);
            flowSubs.add(flowSub);
            i++;
        }

        flowSubController.add(flowSubs);

        /*添加任务流关联表*/
        List<FlowMainSubTask> flowMainSubTasks = new ArrayList<>();
        for (int j = 0 ; j < subs.size(); j++) {
            FlowMainSubTask flowMainSubTask = new FlowMainSubTask();
            flowMainSubTask.setMainId(flowMainController.list().get(0).getId());
            flowMainSubTask.setSubId(flowSubs.get(j).getId());
            flowMainSubTask.setExecLevel(j);
            flowMainSubTask.setProjectId(flowProjectController.list().get(0).getId());
            flowMainSubTasks.add(flowMainSubTask);
        }
        flowMainSubTaskController.add(flowMainSubTasks);
    }

}
