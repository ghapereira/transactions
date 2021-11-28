package org.ghapereira;

import org.ghapereira.domain.Account;
import org.ghapereira.repository.AccountRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/accounts/")
public class AccountResource {
    @Inject
    AccountRepository accountRepository;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        if (account.getDocumentNumber() == null) {
            // TODO use value from library for HTTP status
            throw new WebApplicationException("Document number must be provided", 422);
        }

        accountRepository.persist(account);
        return Response.ok(account).status(201).build();
    }

    @Path("{id:\\d+}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountInfo(@PathParam("id") Long id) {
        Account account = accountRepository.findById(id);
        return Response.ok(account).build();
    }
}
