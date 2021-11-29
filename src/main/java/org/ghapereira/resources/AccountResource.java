package org.ghapereira.resources;

import org.ghapereira.domain.Account;
import org.ghapereira.exceptions.BusinessException;
import org.ghapereira.services.AccountService;
import org.ghapereira.utils.Constants;
import org.ghapereira.utils.ErrorEntity;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/accounts/")
public class AccountResource {
    @Inject
    AccountService accountService;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        try {
            accountService.createAccount(account);
        } catch (BusinessException b) {
            ErrorEntity errorEntity = new ErrorEntity(b.getMessage());

            return Response.status(Constants.HTTP_STATUS_UNPROCESSABLE_ENTITY)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(errorEntity)
                    .build();
        }

        return Response.ok(account).status(Response.Status.CREATED).build();
    }

    @Path("{id:\\d+}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountInfo(@PathParam("id") Long id) {
        Account account;

        try {
            account = accountService.getAccountInfo(id);
        } catch (BusinessException b) {
            ErrorEntity errorEntity = new ErrorEntity(b.getMessage());

            return Response.status(Constants.HTTP_STATUS_UNPROCESSABLE_ENTITY)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(errorEntity)
                    .build();
        }

        return Response.ok(account).build();
    }
}
