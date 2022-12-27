package dematic.demo.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import javax.persistence.*;

@MappedSuperclass
public class AbstractBook {
    @Id
    @NotEmpty(message = "Barcode is required")
    @Column(name = "barcode")
    private String barcode;
    @NotEmpty(message = "Name is required")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Author is required")
    @Column(name = "author")
    private String author;
    @Min(value = 0, message = "Quantity must be greater than or equal to zero")
    @NotNull(message = "Quantity is required")
    @Column(name = "quantity")
    private int quantity;
    @Min(value = 0, message = "Price must be greater than or equal to zero")
    @NotNull(message = "Price is required")
    @Column(name = "pricePerUnit")
    private float pricePerUnit;

    public AbstractBook(AbstractBook book) {
        this.barcode = book.barcode;
        this.name = book.name;
        this.author = book.author;
        this.pricePerUnit = book.pricePerUnit;
        this.quantity = book.quantity;
    }

    public AbstractBook() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }


}