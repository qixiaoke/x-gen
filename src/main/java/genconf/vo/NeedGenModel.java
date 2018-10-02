package genconf.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一类数据
 * 配置管理模块的对外接口
 *
 * @author qixin
 */
public class NeedGenModel {

    private String id; // 如：UserGenConf

    private String provider;// 如：XmlModuleGenConf

    private String theme;// 如： simple

    private Map<String, String> mapParams = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Map<String, String> getMapParams() {
        return mapParams;
    }

    public void setMapParams(Map<String, String> mapParams) {
        this.mapParams = mapParams;
    }

    @Override
    public String toString() {
        return "NeedGenModel [id=" + id + ", provider=" + provider + ", theme=" + theme + ", mapParams=" + mapParams
                + "]";
    }
}
