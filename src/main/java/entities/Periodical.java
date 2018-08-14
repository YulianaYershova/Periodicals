package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Julia on 27.07.2018
 */
public class Periodical implements Serializable {

    private int id;
    private PeriodicalType type;
    private PeriodicalCategory category;
    private String period;
    private BigDecimal price;
    private String description;

    public Periodical() {
    }

    public Periodical(int id, PeriodicalType type, PeriodicalCategory category, String period, BigDecimal price, String description) {
        this.id = id;
        this.type = type;
        this.category = category;
        this.period = period;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public PeriodicalType getType() {
        return type;
    }

    public PeriodicalCategory getCategory() {
        return category;
    }

    public String getPeriod() {
        return period;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(PeriodicalType type) {
        this.type = type;
    }

    public void setCategory(PeriodicalCategory category) {
        this.category = category;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodical that = (Periodical) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(category, that.category) &&
                Objects.equals(period, that.period) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, category, period, price, description);
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", category=" + category +
                ", period='" + period + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
