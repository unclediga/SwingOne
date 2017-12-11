package unclediga.myjfx.cell;

public class Pair {
    private String key;
    private Object val;

    public Pair(String s, Object o) {
        setKey(s);
        setVal(o);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }
}
