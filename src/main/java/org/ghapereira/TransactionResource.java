package org.ghapereira;

import org.ghapereira.domain.Account;
import org.ghapereira.domain.Transaction;
import org.ghapereira.repository.AccountRepository;
import org.ghapereira.repository.TransactionRepository;

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
    TransactionRepository transactionRepository;

    @Inject
    AccountRepository accountRepository;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransaction(Transaction transaction) {
        // TODO check mandatory fields
        Account existingAccount = accountRepository.findById(transaction.accountId);

        if (existingAccount == null) {
            throw new WebApplicationException("Account id does not exist", 422);
        }

        transaction.account = existingAccount;
        transactionRepository.persist(transaction);
        return Response.ok(transaction).status(201).build();
    }

}
