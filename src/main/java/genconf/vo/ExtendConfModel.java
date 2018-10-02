package genconf.vo;


import java.util.Arrays;

/**
 * 针对的是：第二类用户需要生成的模块的配置数据
 *
 * @author qixin
 */
public class ExtendConfModel {

    private String id;

    // 当isSingle=true时，就一个值
    private String value;

    // 当isSingle=false时，value 有多个值
    private String[] values;

    private boolean isSingle = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    @Override
    public String toString() {
        return "ExtendConfModel [id=" + id + ", value=" + value + ", values=" + Arrays.toString(values) + ", isSingle="
                + isSingle + "]";
    }
}
