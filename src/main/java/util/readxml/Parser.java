package util.readxml;

import java.util.*;

public class Parser {

    // 定义常量
    private static final String BACKLASH = "/";
    private static final String DOT = ".";
    private static final String DOLLAR = "$";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    // 存储的是，按照分解的先后顺序的元素名称
    private static List<String> listEle = null;

    // 构建工具类，
    // 构造方法私有化，不允许外界创建对象
    private Parser() {

    }

    /**
     * 根据传入的表达式，通过解析，组合成为一个抽象的语法树
     *
     * @param expr
     *            要解析的表达式
     * @return 表达式对应的抽象语法树
     */
    // root/a/b/c
    public static ReadXmlExpression parse(String expr) {
        // 第一大步: 分解表达式，得到需要解析的元素名称和该元素对应的解析模型；
        // 就是，root 对应一个ParseModel, 你不能简简单单的写一个root,就可以了。
        // 同样，a 对应一个ParseModel

        Map<String, ParseModel> mapPath = parseMapPath(expr);

        // 第二大步：根据元素对应的解析模型，转换成，相应的解释器对象，如ElementExpression
        List<ReadXmlExpression> list = mapPath2Expression(mapPath);
        // 第三大步：按照先后顺序组成为抽象语法树
        ReadXmlExpression retTree = buildTree(list);

        return retTree;
    }

    // ----------------------------第一大步-------------------
    /**
     * 第一大步：
     *
     * 按照从左到右的顺序来解析表达式，得到相应的元素名称和该元素对应的解析模型
     *
     * @param expr
     * @return
     */
    private static Map<String, ParseModel> parseMapPath(String expr) {
        listEle = new ArrayList<>();

        // root/a/b/c.name
        StringTokenizer tokenizer = new StringTokenizer(expr, BACKLASH);

        Map<String, ParseModel> mapPath = new HashMap<>();

        while (tokenizer.hasMoreTokens()) {
            String onePath = tokenizer.nextToken();

            if (tokenizer.hasMoreTokens()) {
                // 还有下一个，说明不是结尾

                setParsePath(onePath, false, false, mapPath);
            } else {
                // 说明 结尾了
                int dotIndex = onePath.indexOf(DOT);

                if (dotIndex > 0) {
                    // 说明是属性结尾
                    String eleName = onePath.substring(0, dotIndex);
                    String propName = onePath.substring(dotIndex + 1);
                    // 设置属性前面的元素
                    setParsePath(eleName, false, false, mapPath);

                    // 设置属性
                    setParsePath(propName, true, true, mapPath);
                } else {
                    // 说明是 元素结尾
                    setParsePath(onePath, true, false, mapPath);
                }
                // 已经解析到了结尾了，就退出吧
                break;
            }
        }

        return mapPath;
    }

    private static void setParsePath(String eleName, boolean end, boolean propertyValue,
                                     Map<String, ParseModel> mapPath) {
        ParseModel pm = new ParseModel();

        pm.setEnd(end);
        pm.setPropertyValue(propertyValue);
        pm.setSingleValue(!(eleName.indexOf(DOLLAR) > 0));

        // 去掉$
        eleName = eleName.replace(DOLLAR, "");

        // 条件设置
        int tempBegin = 0;
        int tempEnd = 0;

        if (((tempBegin = eleName.indexOf(OPEN_BRACKET)) > 0)) {
            tempEnd = eleName.indexOf(CLOSE_BRACKET);
            pm.setCondition(eleName.substring(tempBegin + 1, tempEnd));

            eleName = eleName.substring(0, tempBegin);
        }

        // 名称设置
        pm.setEleName(eleName);

        mapPath.put(eleName, pm);

        listEle.add(eleName);
    }

    // ----------------------------第二大步-------------------
    /**
     * 第二大步：根据元素对应的解析模型，转换成，相应的解释器对象，如ElementExpression
     *
     * @param mapPath
     * @return
     */
    private static List<ReadXmlExpression> mapPath2Expression(Map<String, ParseModel> mapPath) {
        List<ReadXmlExpression> result = new ArrayList<>();
        // 一定要按照分解的先后顺序，来转换成相应的解释器对象
        for (String key : listEle) {
            ParseModel pm = mapPath.get(key);
            ReadXmlExpression obj = parseModel2ReadXmlExpression(pm);

            result.add(obj);

        }

        return result;
    }

    private static ReadXmlExpression parseModel2ReadXmlExpression(ParseModel pm) {
        ReadXmlExpression obj = null;

        if (!pm.isEnd()) {
            if (pm.isSingleValue()) { // 非结尾的，就是单个元素的非终结符
                obj = new ElementExpression(pm.getEleName(), pm.getCondition());
            } else {
                obj = new ElementsExpression(pm.getEleName(), pm.getCondition());
            }
        } else if (pm.isPropertyValue()) {

            if (pm.isSingleValue()) {// 单个属性结尾
                obj = new PropertyTerminalExpression(pm.getEleName());
            } else {
                obj = new PropertysTerminalExpression(pm.getEleName());
            }

        } else {
            if (pm.isSingleValue()) {// 单个属性结尾
                obj = new ElementTerminalExpression(pm.getEleName(), pm.getCondition());
            } else {
                obj = new ElementsTerminalExpression(pm.getEleName(), pm.getCondition());
            }
        }

        return obj;
    }

    // ----------------------------第三大步-------------------

    /**
     * 第三大步：按照先后顺序组成为抽象语法树
     *
     * @param listExpression
     * @return
     */
    private static ReadXmlExpression buildTree(List<ReadXmlExpression> listExpression) {
        // 第一个对象，也就是根对象，返回去的对象
        ReadXmlExpression retRe = null;
        // 用来临时记录，上一个元素，作为父元素
        ReadXmlExpression preEle = null;

        for (ReadXmlExpression re : listExpression) {
            // 说明当前元素，re是根元素
            if (null == preEle) {
                retRe = re; // 设置返回去的元素
                preEle = re; // 将当前元素，设置为父元素
            } else {
                // 对当前元素的类型，进行判断
                if (preEle instanceof ElementExpression) {
                    ElementExpression elementExpression = (ElementExpression) preEle;
                    // 将当前元素，添加到父元素的下面
                    elementExpression.addEle(re);

                    // 并且，将当前元素，作为前一个元素，也就是作为父级元素
                    preEle = elementExpression;

                } else if (preEle instanceof ElementsExpression) {
                    ElementsExpression eles = (ElementsExpression) preEle;
                    eles.addEle(re);

                    preEle = eles;
                }

            }

        }

        return retRe;
    }
}
