package app.entities;

public class CupcakeBottom {
    private int cupcakeBottomId;
    private String name;
    private int price;

    @Override
    public String toString() {
        return "cupcakeBottom{" +
                "cupcakeBottomId=" + cupcakeBottomId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public CupcakeBottom(int cupcakeBottomId, String name, int price) {
        this.cupcakeBottomId = cupcakeBottomId;
        this.name = name;
        this.price = price;
    }
}
