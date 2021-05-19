package hu.otpmobil.ticket.core.service;

import hu.otpmobil.common.dto.PaymentRequest;
import hu.otpmobil.common.dto.PaymentResult;
import hu.otpmobil.common.error.Errors;
import hu.otpmobil.ticket.core.model.UserBankCard;
import hu.otpmobil.ticket.core.repository.UserBankCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private UserBankCardRepository repository;

    public PaymentResult pay(PaymentRequest paymentRequest) {
        logger.info("Payment request received: userId: {}, cardId: {}",
                paymentRequest.getUserId(), paymentRequest.getCardId());
        PaymentResult paymentResult = new PaymentResult();
        Optional<UserBankCard> byCardId = repository.findByCardId(paymentRequest.getCardId());
        if (byCardId.isPresent()) {
            UserBankCard userBankCard = byCardId.get();
            if (!paymentRequest.getUserId().equals(userBankCard.getUser().getUserId())) {
                logger.error("The card {} in the request does not belong to this user {} ", paymentRequest.getCardId(), paymentRequest.getUserId());
                paymentResult.setSuccess(false);
                paymentResult.setErrorCode(Errors.USER_WITH_NOT_VALID_CARD.getErrorCode());
                paymentResult.setErrorMessage(Errors.USER_WITH_NOT_VALID_CARD.getErrorMessage());
            } else {
                if (paymentRequest.getAmount().longValue() > userBankCard.getAmount()) {
                    logger.error("The user {} do not have enough amount", paymentRequest.getUserId());
                    paymentResult.setSuccess(false);
                    paymentResult.setErrorCode(Errors.AMOUNT_NOT_ENOUGH.getErrorCode());
                    paymentResult.setErrorMessage(Errors.AMOUNT_NOT_ENOUGH.getErrorMessage());
                } else {
                    userBankCard.setAmount(userBankCard.getAmount() - paymentRequest.getAmount());
                    repository.save(userBankCard);
                    paymentResult.setSuccess(true);
                }
            }
        }

        return paymentResult;
    }

}
