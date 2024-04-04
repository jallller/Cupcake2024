package app.entities;

public class OrderLine {

    //boolean done/undone for admin
    private int orderLineId;
    private int orderId;
    private int userId;
    private int cupcakeTopId;
    private int cupcakeBottomId;
    private int quantity;

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineId=" + orderLineId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", cupcakeTopId=" + cupcakeTopId +
                ", cupcakeBottomId=" + cupcakeBottomId +
                ", quantity=" + quantity +
                '}';
    }

    public OrderLine(int orderLineId, int orderId, int userId, int cupcakeTopId, int cupcakeBottomId, int quantity) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.userId = userId;
        this.cupcakeTopId = cupcakeTopId;
        this.cupcakeBottomId = cupcakeBottomId;
        this.quantity = quantity;
    }
}
