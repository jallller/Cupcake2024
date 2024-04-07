package app.entities;

public class OrderLine {

    //boolean done/undone for admin
    private int orderLineId;

    private int cupcakeTopId;
    private int cupcakeBottomId;
    private int quantity;

    public OrderLine(int orderLineId, int cupcakeTopId, int cupcakeBottomId, int quantity) {
        this.orderLineId = orderLineId;
        this.cupcakeTopId = cupcakeTopId;
        this.cupcakeBottomId = cupcakeBottomId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineId=" + orderLineId +
                ", cupcakeTopId=" + cupcakeTopId +
                ", cupcakeBottomId=" + cupcakeBottomId +
                ", quantity=" + quantity +
                '}';
    }
}
