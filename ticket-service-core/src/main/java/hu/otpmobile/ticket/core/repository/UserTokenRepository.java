package hu.otpmobile.ticket.core.repository;

import hu.otpmobile.ticket.core.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {

    Optional<UserToken> findByToken(String token);

}
