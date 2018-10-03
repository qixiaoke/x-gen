package util.readxml;

public class ParseModel {

    /**
     * 元素名称
     */
    private String eleName;

    /**
     * 是否是属性值，不是属性值就是元素的值
     */
    private boolean propertyValue;

    /**
     * 是否是终结符
     */
    private boolean end;

    /**
     *
     * 是否是单个值
     */
    private boolean singleValue;

    /**
     * 条件表达式
     *
     */
    private String condition;

    public String getEleName() {
        return eleName;
    }

    public void setEleName(String eleName) {
        this.eleName = eleName;
    }

    public boolean isPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(boolean propertyValue) {
        this.propertyValue = propertyValue;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isSingleValue() {
        return singleValue;
    }

    public void setSingleValue(boolean singleValue) {
        this.singleValue = singleValue;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
