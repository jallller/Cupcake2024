package app.entities;

public class Order {
    public Order(int orderIdId, int user_Id, int price){
        this.orderId = orderId;
        this.userId = user_Id;
        this.price = price;

    }

    public Order(int price) {
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
