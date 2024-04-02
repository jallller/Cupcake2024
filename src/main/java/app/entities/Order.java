package app.entities;

public class Order {
    public Order(int orderIdId, int userId, int price){
        this.orderId = orderId;
        this.userId = userId;
        this.price = price;

    }

    private int orderId;
    private int userId;
    private int price;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}
