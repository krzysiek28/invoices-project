package com.io.invoices.invoiceshibernate.security;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountRepository;
import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.client.ClientRepository;
import com.io.invoices.invoiceshibernate.facture.Facture;
import com.io.invoices.invoiceshibernate.facture.FactureRepository;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.product.ProductRepository;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import static com.io.invoices.invoiceshibernate.security.SecurityUtils.SECRET;
import static com.io.invoices.invoiceshibernate.security.SecurityUtils.TOKEN_PREFIX;

@Service
public class AuthorizationFilter {

    private final FirmRepository firmRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final FactureRepository factureRepository;
    private final BankAccountRepository bankAccountRepository;

    public AuthorizationFilter(FirmRepository firmRepository, ApplicationUserRepository applicationUserRepository, ClientRepository clientRepository, ProductRepository productRepository, FactureRepository factureRepository, BankAccountRepository bankAccountRepository) {
        this.firmRepository = firmRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.factureRepository = factureRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    private int getUserIdFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        ApplicationUser user = applicationUserRepository.findByUsername(username);

        return user.getId();
    }

    public void isAuthorizedTo(String token, String resourceId, ResourceType resource) throws UnauthorizedException {
        int userId = getUserIdFromToken(token);
        switch(resource) {
            case FIRM:
                firmAuth(userId,Integer.parseInt(resourceId));
                break;
            case CLIENT:
                clientAuth(userId,Integer.parseInt(resourceId));
                break;
            case PRODUCT:
                productAuth(userId, Integer.parseInt(resourceId));
                break;
            case FACTURE:
                factureAuth(userId, Integer.parseInt(resourceId));
                break;
            case BANKACCOUNT:
                bankAuth(userId, resourceId);
                break;
            case USER:
                userAuth(userId, Integer.parseInt(resourceId));
                break;
            default:
                throw new IllegalArgumentException("Blad podczas autoryzacji!");
        }
    }

    private void firmAuth(int userId, int firmId) throws UnauthorizedException {
        Firm one = firmRepository.findOne(firmId);
        if ((one == null) || (!one.getOwner().getId().equals(userId)))
            throw new UnauthorizedException("Nie masz praw dostepu do podanej firmy!");
    }

    private void clientAuth(int userId, int clientId) throws UnauthorizedException {
        Client one = clientRepository.findOne(clientId);
        if ((one == null) || (!one.getOwner().getId().equals(userId)))
            throw new UnauthorizedException("Nie masz praw dostepu do podanego klienta!");
    }

    private void productAuth(int userId, int productId) throws UnauthorizedException {
        Product one = productRepository.findOne(productId);
        if ((one == null) || (!one.getOwner().getOwner().getId().equals(userId)))
            throw new UnauthorizedException("Nie masz praw dostepu do podanego produktu!");
    }

    private void factureAuth(int userId, int factureId) throws UnauthorizedException {
        Facture one = factureRepository.findOne(factureId);
        if ((one == null) || (!one.getFirm().getOwner().getId().equals(userId)))
            throw new UnauthorizedException("Nie masz praw dostepu do podanej faktury!");
    }

    private void bankAuth(int userId, String account) throws UnauthorizedException {
        BankAccount one = bankAccountRepository.findOne(account);
        if ((one == null) || (!one.getFirm().getOwner().getId().equals(userId)))
            throw new UnauthorizedException("Nie masz praw dostepu do podanego konta bankowego!");
    }

    private void userAuth(int userId, int requestUser) throws UnauthorizedException {
        ApplicationUser one = applicationUserRepository.findOne(requestUser);
        if ((one == null) || (!one.getId().equals(userId)))
            throw new UnauthorizedException("Nie masz praw dostepu podanego uzytkownika!");
    }
}
