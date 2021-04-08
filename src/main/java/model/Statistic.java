package model;

public class Statistic {
    int id;
    int requestId;
    String keyWord;
    int quantity;

    public Statistic(int requestId, String keyWord, int quantity) {
        this.id = -1;
        this.requestId = requestId;
        this.keyWord = keyWord;
        this.quantity = quantity;
    }

    public Statistic(int id, int requestId, String keyWord, int quantity) {
        this.id = id;
        this.requestId = requestId;
        this.keyWord = keyWord;
        this.quantity = quantity;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
