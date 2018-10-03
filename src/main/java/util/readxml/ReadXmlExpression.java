package util.readxml;

public abstract class ReadXmlExpression implements Cloneable {

    public abstract String[] interpret(Context ctx);

    // 实现浅克隆
    @Override
    protected Object clone() {
        Object object = null;

        try {
            object = super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
}
