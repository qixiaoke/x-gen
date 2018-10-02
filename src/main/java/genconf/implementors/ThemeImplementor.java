package genconf.implementors;

import genconf.vo.GenTypeModel;

import java.util.Map;

public interface ThemeImplementor {

    /**
     * 根据themeID和注册theme时配置的相应参数，来获取theme中定义的能生成的功能类型
     * @param themeId
     * @param params
     * @return
     */
    Map<String, GenTypeModel> getMapGenTypes(String themeId, Map<String, String> params);

    Map<String, String> getMapGenOutTypes(String themeId, Map<String, String> params);

    Map<String, String> getMapProviders(String themeId, Map<String, String> params);
}
