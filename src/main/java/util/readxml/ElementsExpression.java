package util.readxml;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElementsExpression extends ReadXmlExpression {

    // 用来记录/存储当前元素的子ReadXmlExpression元素
    private List<ReadXmlExpression> eles = new ArrayList<>();

    // 元素的名称
    private String eleName;

    // 判断的条件，就是 [id=e1]
    // root/a/e$[id=e1]/f
    private String condition = "";

    public ElementsExpression(String eleName, String condition) {
        this.eleName = eleName;
        this.condition = condition;
    }

    public void addEle(ReadXmlExpression ele) {
        this.eles.add(ele);
    }

    public boolean removeEle(ReadXmlExpression ele) {
        this.eles.remove(ele);
        return true;
    }

    public void removeAllEles() {
        this.eles.clear();
    }

    public List<ReadXmlExpression> getEles() {
        return eles;
    }

    public void setEles(List<ReadXmlExpression> eles) {
        this.eles = eles;
    }

    @Override
    public String[] interpret(Context context) {
        // 1、维护父级节点记录
        List<Element> parentEles = context.getPreEles();
        // 获取当前元素，多个
        List<Element> nowEles = new ArrayList<>();

        for (Element parentElement : parentEles) {
            nowEles.addAll(context.getNowEles(parentElement, eleName));
        }
        // 判断条件
        Iterator<Element> it = nowEles.iterator();
        while (it.hasNext()) {
            Element ele = it.next();
            if (!context.judgeCondition(ele, condition)) {
                // 将不满足条件的移除
                it.remove();
            }
        }
        // 设置父节点
        context.setPreEles(nowEles);

        // 2、循环解释子元素
        String[] ss = null;

        for (ReadXmlExpression tempEle : eles) {
            ss = tempEle.interpret(context);
        }

        return ss;
    }

    @Override
    protected Object clone() {
        ElementsExpression obj = null;

        try {
            // 1、 首先， 浅克隆，是将基本类型，进行了克隆，这里如：eleName, condition

            obj = (ElementsExpression) super.clone();

            // 2、对eles元素，进行深度克隆
            List<ReadXmlExpression> childRE = new ArrayList<>();

            for (ReadXmlExpression re : eles) {

                // childRE.add(re); //不能写成这种形式，因为，re对象，同样需要克隆
                childRE.add((ElementsExpression) re.clone());
            }

            obj.setEles(childRE);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
