package dematic.demo.models;

public class BarcodeDTO {
    private String[] barcodes;
    private int quantity;

    public BarcodeDTO(int quantity, String[] barcodes) {
        this.quantity = quantity;
        this.barcodes = barcodes;
    }

    public String[] getBarcode() {
        return barcodes;
    }

    public void setBarcode(String[] barcodes) {
        this.barcodes = barcodes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
