package dematic.demo.controllers;

import dematic.demo.models.*;
import dematic.demo.repositories.BookRepository;
import dematic.demo.repositories.AbstractBookRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/books")

public class BooksController implements Controller<Book> {

    private final AbstractBookRepository<Book> repository = new BookRepository("book");
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book bookRequest) {
        Book book = repository.findByBarcode(bookRequest.getBarcode(), Book.class);
        if (book != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Book with specified barcode already exists\"}").build();
        }

        book = repository.add(bookRequest);

        return Response.ok(book).build();
    }

    @GET
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("barcode") String barcode) {
        if (barcode == null || barcode.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Barcode is null or empty\"}").build();
        }
        Book book = repository.findByBarcode(barcode, Book.class);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();

        return Response.ok(book).build();
    }

    @PUT
    @Path("{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("barcode") String barcode, Book bookRequest) {
        if (barcode == null || barcode.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Barcode is null or empty\"}").build();
        }

        Book book = repository.findByBarcode(barcode, Book.class);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();

        repository.update(bookRequest);

        book = repository.findByBarcode(barcode, Book.class);

        return Response.ok(book).build();
    }

    @GET
    @Path("{barcode}/totalprice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTotalPrice(@PathParam("barcode") String barcode) {
        if (barcode == null || barcode.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Barcode is null or empty\"}").build();
        }
        Book book = repository.findByBarcode(barcode, Book.class);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();
        String jsonPrice = "{\"price\":" + repository.totalPrice(barcode, new Book()) + "}";
        return Response.ok(jsonPrice).build();
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBarcodes() {
        List<BarcodeDTO> books = repository.getAllBarcodes("book");
        if (books == null)
            return Response.status(Response.Status.NO_CONTENT).entity("{\"error\":\"No books exist\"}").build();

        return Response.status(Response.Status.OK).entity(books).build();
    }

}