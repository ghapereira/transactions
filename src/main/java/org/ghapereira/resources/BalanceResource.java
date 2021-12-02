package org.ghapereira.resources;

import org.ghapereira.domain.Balance;
import org.ghapereira.exceptions.BusinessException;
import org.ghapereira.services.BalanceService;
import org.ghapereira.utils.Constants;
import org.ghapereira.utils.ErrorEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/balance/{accountId:\\d+}")
public class BalanceResource {
    @Inject
    BalanceService balanceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountBalance(@PathParam("accountId") Long accountId) {
        Balance balance;
        try {
            balance = balanceService.calculateBalance(accountId);
        } catch (BusinessException b) {
            ErrorEntity errorEntity = new ErrorEntity(b.getMessage());

            return Response.status(Constants.HTTP_STATUS_UNPROCESSABLE_ENTITY)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(errorEntity)
                    .build();
        }

        return Response.ok(balance).build();
    }
}
