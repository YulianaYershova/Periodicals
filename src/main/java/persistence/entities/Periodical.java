package persistence.entities;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Julia on 27.07.2018
 */
public class Periodical implements Serializable {

    private int id;
    private String title;
    private PeriodicalType periodicalType;
    private PeriodicalCategory periodicalCategory;
    private String period;
    private BigDecimal price;
    private String description;

    public Periodical() {
    }

    public Periodical(int id, String title, PeriodicalType periodicalType, PeriodicalCategory periodicalCategory,
                      String period, BigDecimal price, String description) {
        this.id = id;
        this.title = title;
        this.periodicalType = periodicalType;
        this.periodicalCategory = periodicalCategory;
        this.period = period;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public PeriodicalType getPeriodicalType() {
        return periodicalType;
    }

    public PeriodicalCategory getPeriodicalCategory() {
        return periodicalCategory;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPeriodicalType(PeriodicalType periodicalType) {
        this.periodicalType = periodicalType;
    }

    public void setPeriodicalCategory(PeriodicalCategory periodicalCategory) {
        this.periodicalCategory = periodicalCategory;
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
                Objects.equals(title, that.title) &&
                Objects.equals(periodicalType, that.periodicalType) &&
                Objects.equals(periodicalCategory, that.periodicalCategory) &&
                Objects.equals(period, that.period) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, periodicalType, periodicalCategory, period, price, description);
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", periodicalType=" + periodicalType +
                ", periodicalCategory=" + periodicalCategory +
                ", period='" + period + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
