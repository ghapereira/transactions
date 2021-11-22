package org.ghapereira;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/accounts/")
public class AccountResource {

    @Path("{id:\\d+}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccountInfo() {
        return "{\"account_id\": 1, \"document_number\": \"1234567890\"}";
    }
}
