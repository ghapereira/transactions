package org.ghapereira;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/transactions")
public class TransactionResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransaction(TransactionCreatorEntity transactionCreator) {
        return Response.ok("Created transaction for accountId " + transactionCreator.accountId).build();
    }

}
