package org.ghapereira.resources;

import org.ghapereira.domain.Transaction;
import org.ghapereira.exceptions.BusinessException;
import org.ghapereira.services.TransactionService;
import org.ghapereira.utils.Constants;
import org.ghapereira.utils.ErrorEntity;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
            ErrorEntity errorEntity = new ErrorEntity(b.getMessage());

            return Response.status(Constants.HTTP_STATUS_UNPROCESSABLE_ENTITY)
                .type(MediaType.APPLICATION_JSON)
                .entity(errorEntity)
                .build();
        }

        return Response.ok(transaction).status(Response.Status.CREATED).build();
    }

}
