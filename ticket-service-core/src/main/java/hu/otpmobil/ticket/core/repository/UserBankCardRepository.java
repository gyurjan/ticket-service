package hu.otpmobil.ticket.core.repository;

import hu.otpmobil.ticket.core.model.UserBankCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBankCardRepository extends JpaRepository<UserBankCard, String> {

    Optional<UserBankCard> findByCardId(String cardId);

}
