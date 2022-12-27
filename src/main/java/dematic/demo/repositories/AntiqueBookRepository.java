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
        return book.getPricePerUnit() * book.getQuantity() * (OffsetDateTime.now().getYear() - ((AntiqueBook)book).getReleaseYear() / 10.0);
    }
}
