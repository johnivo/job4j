package ru.job4j.warehouse;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Класс определяет базовые параметры продуктов: название, даты срока годности, цену, скидку.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.07.2019
 */
public abstract class Food {

    private String name;

    private Date expiryDate;

    private Date createDate;

    private Double price;

    private Double discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiryDate=" + expiryDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
