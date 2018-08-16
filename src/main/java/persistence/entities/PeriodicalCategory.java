package persistence.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Julia on 12.08.2018
 */
public class PeriodicalCategory implements Serializable {

    private int id;
    private String category;

    public PeriodicalCategory() {
    }

    public PeriodicalCategory(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalCategory that = (PeriodicalCategory) o;
        return id == that.id &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }

    @Override
    public String toString() {
        return "PeriodicalCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
