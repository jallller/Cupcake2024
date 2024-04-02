package app.entities;

public class cupcakeTop {
    private int cupcakeTopId;
    private String name;

    @Override
    public String toString() {
        return "cupcakeTop{" +
                "cupcakeTopId=" + cupcakeTopId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public cupcakeTop(int cupcakeTopId, String name, int price) {
        this.cupcakeTopId = cupcakeTopId;
        this.name = name;
        this.price = price;
    }

    private int price;

}
