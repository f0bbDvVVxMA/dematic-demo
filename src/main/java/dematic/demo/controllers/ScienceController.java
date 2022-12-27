package dematic.demo.controllers;

import dematic.demo.models.BarcodeDTO;
import dematic.demo.models.ScienceJournal;
import dematic.demo.repositories.AbstractBookRepository;
import dematic.demo.repositories.ScienceJournalRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/science")
public class ScienceController implements Controller<ScienceJournal> {
    private final AbstractBookRepository<ScienceJournal> repository = new ScienceJournalRepository("scienceJournal");
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response addBook(ScienceJournal scienceJournalRequest) {
        ScienceJournal book = null;
        book = repository.findByBarcode(scienceJournalRequest.getBarcode(), new ScienceJournal());
        if (book != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Book with specified barcode already exists\"}").build();
        }

        book = repository.add(scienceJournalRequest);

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
        ScienceJournal book = repository.findByBarcode(barcode,(new ScienceJournal()));

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();

        return Response.ok(book).build();
    }

    @PUT
    @Path("{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response updateBook(@PathParam("barcode") String barcode, ScienceJournal scienceJournalRequest) {
        if (barcode == null || barcode.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Barcode is null or empty\"}").build();
        }

        ScienceJournal book = repository.findByBarcode(barcode, scienceJournalRequest);

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();

        repository.update(scienceJournalRequest);

        book = repository.findByBarcode(barcode, scienceJournalRequest);

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
        ScienceJournal book = repository.findByBarcode(barcode, new ScienceJournal());

        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Book with specified barcode doesn't exist\"}").build();
        String price = "{\"price\":" + repository.totalPrice(barcode, book) + "}";
        return Response.status(Response.Status.OK).entity(price).build();
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBarcodes() {
        List<BarcodeDTO> books = repository.getAllBarcodes("scienceJournal", new ScienceJournal());
        if (books == null)
            return Response.status(Response.Status.NO_CONTENT).entity("{\"error\":\"No books exist\"}").build();

        return Response.status(Response.Status.OK).entity(books).build();
    }
}
