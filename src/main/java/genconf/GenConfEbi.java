package genconf;

import genconf.vo.GenConfModel;
import genconf.vo.ModuleConfModel;

import java.util.Map;

/**
 * 配置管理模块的对外接口设计
 * Ebi：就是业务接口
 *
 * @author qixin
 */
public interface GenConfEbi {

    /**
     * 获取x-gen核心框架 运行所需要的配置数据model
     *
     * @return 核心框架 运行所需要的配置数据model
     */
    GenConfModel getGenConf();

    /**
     * 获取需要生成的所有的模块的配置
     *
     * @return 包含所有需要生成的所有模块的配置数据的Map, key:模块的ID，value:相应的模块的配置数据的model
     */
    Map<String, ModuleConfModel> getMapModuleConf();
}
