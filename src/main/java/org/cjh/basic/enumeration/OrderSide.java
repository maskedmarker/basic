package org.cjh.basic.enumeration;

public enum OrderSide {

    BUY("1", "buy"), SELL("2", "sell");
    
    private String code;
    private String text;
    
    private OrderSide(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
