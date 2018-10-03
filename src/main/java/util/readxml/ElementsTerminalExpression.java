package util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElementsTerminalExpression extends ReadXmlExpression {

    private String eleName;

    private String condition;

    public ElementsTerminalExpression(String eleName, String condition) {
        super();
        this.eleName = eleName;
        this.condition = condition;
    }

    @Override
    public String[] interpret(Context context) {
        // 1、获取跟自己名称，相同的多个元素
        // 1、维护父级节点记录
        List<Element> parentEles = context.getPreEles();
        // 获取当前元素，多个
        List<Element> nowEles = new ArrayList<>();

        for (Element parentElement : parentEles) {
            nowEles.addAll(context.getNowEles(parentElement, eleName));
        }

        // 2、判断这个元素是否满足条件
        Iterator<Element> it = nowEles.iterator();
        while (it.hasNext()) {
            Element ele = it.next();
            if (!context.judgeCondition(ele, condition)) {
                // 将不满足条件的移除
                it.remove();
            }
        }

        // 3、获取这个元素的值
        String[] ss = new String[nowEles.size()];

        for (int i = 0; i < ss.length; i++) {
            Element ele = nowEles.get(i);

            if (null != ele.getFirstChild()) {
                ss[i] = ele.getFirstChild().getNodeValue();
            } else {
                ss[i] = "";
            }

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
