package dematic.demo.controllers;

import dematic.demo.models.AntiqueBook;
import dematic.demo.models.BarcodeDTO;
import dematic.demo.repositories.AntiqueBookRepository;
import dematic.demo.repositories.AbstractBookRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/antique")
public class AntiqueController implements Controller<AntiqueBook> {
    private final AbstractBookRepository<AntiqueBook> repository = new AntiqueBookRepository("antiqueBook");
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response addBook(AntiqueBook antiqueBookRequest) {
        AntiqueBook book = repository.findByBarcode(antiqueBookRequest.getBarcode(), antiqueBookRequest);
        if (book != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Book with specified barcode already exists\"}").build();
        }

        book = repository.add(antiqueBookRequest);

        return Response.ok(book).build();
    }

    @GET
    @Path("{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getBook(@PathParam("barcode") String barcode) {
        if (barcode == null || barcode.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Barcode is null or empty\"}").build();
        }
        AntiqueBook book = repository.findByBarcode(barcode,(new AntiqueBook()));

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();

        return Response.ok(book).build();
    }

    @PUT
    @Path("{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response updateBook(@PathParam("barcode") String barcode, AntiqueBook antiqueBookRequest) {
        if (barcode == null || barcode.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Barcode is null or empty\"}").build();
        }

        AntiqueBook book = repository.findByBarcode(barcode, antiqueBookRequest);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();

        repository.update(antiqueBookRequest);

        book = repository.findByBarcode(barcode, antiqueBookRequest);

        return Response.ok(book).build();
    }

    @GET
    @Path("{barcode}/totalprice")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getTotalPrice(@PathParam("barcode") String barcode) {
        if (barcode == null || barcode.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Barcode is null or empty\"}").build();
        }
        AntiqueBook book = repository.findByBarcode(barcode, new AntiqueBook());

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();

        String price = "{\"price\":" + repository.totalPrice(barcode, book) + "}";
        return Response.status(Response.Status.OK).entity(price).build();
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBarcodes() {
        List<BarcodeDTO> books = repository.getAllBarcodes("antiqueBook", new AntiqueBook());
        if (books == null)
            return Response.status(Response.Status.NO_CONTENT).entity("{\"error\":\"No books exist\"}").build();

        return Response.status(Response.Status.OK).entity(books).build();
    }
}
