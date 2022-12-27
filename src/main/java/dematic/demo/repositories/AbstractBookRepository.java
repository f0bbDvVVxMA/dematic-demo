package dematic.demo.repositories;

import dematic.demo.models.AbstractBook;
import dematic.demo.models.BarcodeDTO;
import dematic.demo.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBookRepository<T extends AbstractBook> {
    private final String tableName;

    protected AbstractBookRepository(String tableName) {
        this.tableName = tableName;
    }

    public T add(T book)
    {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return book;
    }

    public T findByBarcode(String barcode, AbstractBook book)
    {
        Transaction transaction = null;
        T result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = (T) session.get(book.getClass(), barcode);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return result;
    }

    public T update(AbstractBook book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return (T)book;
    }

    public double totalPrice(String barcode, AbstractBook book) {
        return book.getPricePerUnit() * book.getQuantity();
    }

    public List<BarcodeDTO> getAllBarcodes(String tableName, AbstractBook book) {
        Transaction transaction = null;
        List<BarcodeDTO> books = new ArrayList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select quantity, LISTAGG(barcode, '-') from " + tableName + " group by quantity");
            List rows = query.list();
            transaction.commit();
            for (Object row : rows) {
                String[] barcodes = ((String)((Object[])row)[1]).split("-");

                int quantity = ((int)((Object[])row)[0]);
                books.add(new BarcodeDTO(quantity, barcodes));
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return books;
    }
}
