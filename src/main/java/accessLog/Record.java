package accessLog;

public class Record {
    private String ipAddress;
    private String dateTime;
    private String request;
    private int statusCode;
    private int quantity;

    public Record(String ipAddress, String dateTime, String request, int statusCode, int quantity) {
        this.ipAddress = ipAddress;
        this.dateTime = dateTime;
        this.request = request;
        this.statusCode = statusCode;
        this.quantity = quantity;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getIpAddress() { return ipAddress; }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String getDateTime() { return dateTime; }
    public void setRequest(String request) {
        this.request = request;
    }
    public String getRequest() { return request; }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public int getStatusCode() { return statusCode; }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() { return quantity; }
}
