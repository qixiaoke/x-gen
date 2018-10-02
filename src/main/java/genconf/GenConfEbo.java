package genconf;

import genconf.confmanager.ConfManager;
import genconf.implementors.GenConfImplementor;
import genconf.vo.GenConfModel;
import genconf.vo.ModuleConfModel;

import java.util.Map;

/**
 * 业务实现对象
 * 负责完成配置管理模块的业务功能
 *
 * @author qixin
 */
public class GenConfEbo implements GenConfEbi {

    private static GenConfEbo ebo = null;

    /**
     * 持有获取核心框架配置数据的具体实现接口
     */
    private GenConfImplementor provider;

    private GenConfEbo(GenConfImplementor provider) {
        this.provider = provider;
    }

    public static GenConfEbo getInstance(GenConfImplementor provider) {
        if(ebo == null) {
            ebo = new GenConfEbo(provider);
        }
        return ebo;
    }

    @Override
    public GenConfModel getGenConf() {
        return ConfManager.getInstance(provider).getGenConf();
    }

    @Override
    public Map<String, ModuleConfModel> getMapModuleConf() {
        return ConfManager.getInstance(provider).getMapModuleConf();
    }
}
