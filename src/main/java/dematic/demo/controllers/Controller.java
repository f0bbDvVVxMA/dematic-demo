package dematic.demo.controllers;

import dematic.demo.models.AbstractBook;

import javax.ws.rs.core.Response;
public interface Controller<T extends AbstractBook> {
    Response addBook(T book);
    Response getBook(String barcode);
    Response updateBook(String barcode, T bookRequest);
    Response getTotalPrice(String barcode);
    Response getBarcodes();
}
