package dematic.demo.repositories;

import dematic.demo.models.AbstractBook;
import dematic.demo.models.ScienceJournal;

public class ScienceJournalRepository extends AbstractBookRepository<ScienceJournal> {
    public ScienceJournalRepository(String tableName) {
        super(tableName);
    }

    @Override
    public double totalPrice(String barcode, AbstractBook book) {
        return book.getQuantity() * book.getPricePerUnit() * ((ScienceJournal)book).getScienceIndex();
    }
}
