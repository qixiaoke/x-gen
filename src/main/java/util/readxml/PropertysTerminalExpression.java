package util.readxml;

import org.w3c.dom.Element;

import java.util.List;

public class PropertysTerminalExpression extends ReadXmlExpression {

    // 属性的名字
    private String propName;

    public PropertysTerminalExpression(String propName) {
        super();
        this.propName = propName;
    }

    @Override
    public String[] interpret(Context context) {
        // 1、获取父元素
        List<Element> parentEles = context.getPreEles();

        String[] ss = new String[parentEles.size()];

        for (int i = 0; i < ss.length; i++) {
            ss[i] = parentEles.get(i).getAttribute(propName);
        }

        return ss;
    }

    // 这里，仅仅实现，浅克隆，就可以了。
    @Override
    protected Object clone() {
        Object obj = null;

        try {
            // 1、 首先， 浅克隆，是将基本类型，进行了克隆，这里如：eleName, condition

            obj = super.clone();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
