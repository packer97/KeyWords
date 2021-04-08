package model;

public class Request {
    int id;
    String url;
    long dateTime;

    public Request(String url, long dateTime) {
        this.id = -1;
        this.url = url;
        this.dateTime = dateTime;
    }

    public Request(int id, String url, long dateTime) {
        this.id = id;
        this.url = url;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
