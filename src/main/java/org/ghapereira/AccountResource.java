package org.ghapereira;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/accounts/")
public class AccountResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAccount(AccountCreatorEntity accountCreator) {
        return Response.ok("Created account with document " + accountCreator.document_number).build();
    }

    @Path("{id:\\d+}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountInfo() {
        return Response.ok("{\"account_id\": 1, \"document_number\": \"1234567890\"}").build();
    }
}
