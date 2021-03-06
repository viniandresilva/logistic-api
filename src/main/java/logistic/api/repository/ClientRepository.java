package logistic.api.repository;

import logistic.api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByNameContainingIgnoreCase(String name);
}