package genconf.confmanager;

import genconf.implementors.GenConfImplementor;
import genconf.implementors.ModuleGenConfImplementor;
import genconf.vo.GenConfModel;
import genconf.vo.ModuleConfModel;
import genconf.vo.NeedGenModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 负责真正去湖区配置数据，并缓存配置数据
 * 并不管业务功能（职责划分清楚）
 *
 * @author qixin
 */
public class ConfManager {

    private static ConfManager manager = null;

    private ConfManager(GenConfImplementor provider) {
        readConf(provider);
    }

    public static ConfManager getInstance(GenConfImplementor provider) {
        if (manager == null) {
            manager = new ConfManager(provider);
        }
        return manager;
    }

    // 定义需要缓存的数据
    private GenConfModel genConf = new GenConfModel();

    private Map<String, ModuleConfModel> mapModuleConf = new HashMap<>();

    private void readConf(GenConfImplementor provider) {
        // 获取配置数据
        readGenConf(provider);

        // 缓存下来
        for (NeedGenModel ngm : genConf.getNeedGens()) {
            readOneModelGenConf(ngm);
        }
    }

    private void readGenConf(GenConfImplementor provider) {
        genConf.setNeedGens(provider.getNeedGens());
        genConf.setThemes(provider.getThemes());
        genConf.setMapContants(provider.getMapConstants());
    }

    private void readOneModelGenConf(NeedGenModel ngm) {
        ModuleConfModel mcm;

        String providerClassName
                = this.getGenConf().getThemeById(ngm.getTheme())
                .getMapProviders().get(ngm.getProvider());

        ModuleGenConfImplementor userGenConfImpl = null;
        try {
            userGenConfImpl = (ModuleGenConfImplementor) Class.forName(providerClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mcm = userGenConfImpl.getBaseModuleConfModel(ngm.getMapParams());
        mcm.setUseTheme(ngm.getTheme());
        mcm.setMapNeedGenTypes(userGenConfImpl.getMapNeedGenTypes(ngm.getMapParams()));
        mcm.setMapExtends(userGenConfImpl.getMapExtends(ngm.getMapParams()));

        this.mapModuleConf.put(mcm.getModuleId(), mcm);
    }

    public GenConfModel getGenConf() {
        return genConf;
    }

    public Map<String, ModuleConfModel> getMapModuleConf() {
        return mapModuleConf;
    }
}
