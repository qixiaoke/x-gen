package genconf.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据模型，其实就是将xml中的数据，用内存表示出来
 * 每一个模块，描述的model
 *
 * @author qixin
 */
public class ModuleConfModel {

    // 用户需要生成的模块的标识
    private String moduleId;

    // 用户需要生成这个模块所使用的外部主题的标识
    private String useTheme;

    // 用户需要生成的具体功能，key：需要生成的功能的标识
    // value：该功能生成后的多种输出类型的标识的集合
    private Map<String, ExtendConfModel> mapExtends = new HashMap<>();

    // 模块生成所需要的扩展数据，key：数据的ID
    // value：对应的扩展数据的model
    private Map<String, List<String>> mapNeedGenTypes = new HashMap<>();

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getUseTheme() {
        return useTheme;
    }

    public void setUseTheme(String useTheme) {
        this.useTheme = useTheme;
    }

    public Map<String, ExtendConfModel> getMapExtends() {
        return mapExtends;
    }

    public void setMapExtends(Map<String, ExtendConfModel> mapExtends) {
        this.mapExtends = mapExtends;
    }

    public Map<String, List<String>> getMapNeedGenTypes() {
        return mapNeedGenTypes;
    }

    public void setMapNeedGenTypes(Map<String, List<String>> mapNeedGenTypes) {
        this.mapNeedGenTypes = mapNeedGenTypes;
    }

    @Override
    public String toString() {
        return "ModuleConfModel [moduleId=" + moduleId + ", useTheme=" + useTheme + ", mapExtends=" + mapExtends
                + ", mapNeedGenTypes=" + mapNeedGenTypes + "]";
    }
}
