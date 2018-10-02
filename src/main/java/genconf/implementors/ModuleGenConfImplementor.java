package genconf.implementors;

import genconf.vo.ExtendConfModel;
import genconf.vo.ModuleConfModel;

import java.util.List;
import java.util.Map;

public interface ModuleGenConfImplementor {

    /**
     * 根据注册的需要生成模块的配置参数，来获取响应的需要生成模块的配置数据model，数据只是基本的部分
     * @param params
     * @return
     */
    ModuleConfModel getBaseModuleConfModel(Map<String, String> params);

    /**
     * 根据注册的需要生成模块的配置参数，来获取需要生成模块中配置的需要生成的功能类型
     * @param params
     * @return
     */
    Map<String, List<String>> getMapNeedGenTypes(Map<String, String> params);

    Map<String, ExtendConfModel> getMapExtends(Map<String, String> param);
}
