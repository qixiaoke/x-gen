package genconf.implementors;

import genconf.vo.NeedGenModel;
import genconf.vo.ThemeModel;

import java.util.List;
import java.util.Map;

/**
 * 获取核心框架配置数据的接口
 */
public interface GenConfImplementor {

    /**
     * 注册的需要生成的模块的配置数据
     * @return
     */
    List<NeedGenModel> getNeedGens();

    /**
     * 注册的外部主题的配置数据
     * @return
     */
    List<ThemeModel> getThemes();

    /**
     * 注册的定义的公共常量
     * @return
     */
    Map<String, String> getMapConstants();
}
