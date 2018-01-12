package com.io.invoices.invoiceshibernate.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    ApplicationUser findByUsername(String username);

    ApplicationUser findByEmail(String email);
}
