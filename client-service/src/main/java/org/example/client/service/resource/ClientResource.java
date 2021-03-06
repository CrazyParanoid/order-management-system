package org.example.client.service.resource;

import org.example.client.service.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource(path = "clients")
public interface ClientResource extends JpaRepository<Client, Long> {
}
