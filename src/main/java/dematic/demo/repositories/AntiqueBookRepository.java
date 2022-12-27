package dematic.demo.repositories;

import dematic.demo.models.AbstractBook;
import dematic.demo.models.AntiqueBook;
import java.time.OffsetDateTime;


public class AntiqueBookRepository extends AbstractBookRepository<AntiqueBook> {
    public AntiqueBookRepository(String tableName) {
        super(tableName);
    }

    @Override
    public double totalPrice(String barcode, AbstractBook book) {
        AntiqueBook antiqueBook = findByBarcode(barcode, book);
        return antiqueBook.getPricePerUnit() * antiqueBook.getQuantity() * (OffsetDateTime.now().getYear() - antiqueBook.getReleaseYear() / 10.0);
    }
}
