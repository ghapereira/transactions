package org.ghapereira;

import org.ghapereira.domain.Transaction;
import org.ghapereira.exceptions.BusinessException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/transactions")
public class TransactionResource {
    @Inject
    TransactionService transactionService;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransaction(Transaction transaction) {

        try {
            transactionService.createTransaction(transaction);
        } catch (BusinessException b) {
            throw new WebApplicationException(b.getMessage(), 422);
        }

        return Response.ok(transaction).status(201).build();
    }

}
