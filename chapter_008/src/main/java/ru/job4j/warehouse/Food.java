package ru.job4j.warehouse;

import java.time.LocalDateTime;

/**
 * Класс определяет базовые параметры продуктов: название, даты срока годности, цену, скидку.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 29.07.2019
 */
public abstract class Food implements Reproduct, VegetablesReproduct {

    private String name;

    private LocalDateTime expiryDate;

    private LocalDateTime createDate;

    private Double price;

    private Double discount;

    public Food(String name, LocalDateTime createDate, LocalDateTime expiryDate, Double price, Double discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
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
