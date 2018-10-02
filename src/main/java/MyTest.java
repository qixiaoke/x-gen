import util.readxml.Context;
import util.readxml.ElementExpression;
import util.readxml.ElementTerminalExpression;
import util.readxml.ReadXmlExpression;

public class MyTest {

    public static void main(String[] args) throws Exception {
        // 构建元素对象
        ElementExpression genConf = new ElementExpression("GenConf", "");
        ElementExpression needGens = new ElementExpression("NeedGens", "");
        ElementExpression needGen = new ElementExpression("NeedGen", "");
        ElementExpression params = new ElementExpression("Params", "");

        ElementTerminalExpression param = new ElementTerminalExpression("Param", "");

        // 构建抽象语法树
        genConf.addEle(needGens);
        needGens.addEle(needGen);
        needGen.addEle(params);
        params.addEle(param);

        // 创建下文对象
        Context context = Context.getInstance("xgenconfxml/GenConf.xml");

        // 开始解析
        String[] ss = genConf.interpret(context);

        // 打印结果
        System.out.println("---->:\t" + ss[0]);
    }
}
