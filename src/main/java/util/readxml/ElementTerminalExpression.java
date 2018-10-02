package util.readxml;

import org.w3c.dom.Element;

import java.util.List;

public class ElementTerminalExpression extends ReadXmlExpression {

    private String eleName;

    private String condition;

    public ElementTerminalExpression(String eleName, String condition) {
        this.eleName = eleName;
        this.condition = condition;
    }

    @Override
    public String[] interpret(Context ctx) {

        // 1.获取到自己这个元素
        // 1.1先要获取到父元素
        List<Element> pEles = ctx.getPreEles();
        // 1.2根据父元素和自己元素的名字，查找到自己这个元素
        Element ele;
        if(pEles.size() == 0) {
            // 说明是根元素
            ele = ctx.getDocument().getDocumentElement();
        } else {
            ele = ctx.getNowEles(pEles.get(0), eleName).get(0);
        }

        // 2.判断这个元素是否满足条件
        if(!ctx.judgeCondition(ele, condition)) {
            return new String[0];
        }

        // 3.获取这个元素的值
        String[] ss = new String[1];
        if(ele.getFirstChild() != null) {
            ss[0] = ele.getFirstChild().getNodeValue();
        } else {
            ss[0] = "";
        }

        return ss;
    }
}
