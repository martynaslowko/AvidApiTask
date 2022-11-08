package Models;

public class Attribute {
    private String group;
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getValue() {
        return value;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
