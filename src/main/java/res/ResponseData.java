package res;

public class ResponseData {
    private int intValue;
    private String sValue;

    public ResponseData(int intValue, String sValue) {
        this.intValue = intValue;
        this.sValue = sValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getsValue() {
        return sValue;
    }

    public void setsValue(String sValue) {
        this.sValue = sValue;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "intValue=" + intValue +
                ", sValue='" + sValue + '\'' +
                '}';
    }
}
