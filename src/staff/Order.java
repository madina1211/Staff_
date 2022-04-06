package staff;

public class Order {

    private Integer orderID;
    private String orderDate;
    private Integer workerID;
    private Integer orderTypeID;
    private String content;

    public Order (Integer orderID, String orderDate, Integer workerID,
                  Integer orderTypeID, String content) {
        this.setOrderID(orderID);
        this.setOrderDate(orderDate);
        this.setWorkerID(workerID);
        this.setOrderTypeID(orderTypeID);
        this.content = content;
    }


    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getWorkerID() {
        return workerID;
    }

    public void setWorkerID(Integer workerID) {
        this.workerID = workerID;
    }

    public Integer getOrderTypeID() {
        return orderTypeID;
    }

    public void setOrderTypeID(Integer orderTypeID) {
        this.orderTypeID = orderTypeID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
