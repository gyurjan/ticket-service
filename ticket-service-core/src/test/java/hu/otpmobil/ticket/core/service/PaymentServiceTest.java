package hu.otpmobil.ticket.core.service;

import hu.otpmobil.common.dto.PaymentRequest;
import hu.otpmobil.common.dto.PaymentResult;
import hu.otpmobil.ticket.core.model.UserBankCard;
import hu.otpmobil.ticket.core.model.Users;
import hu.otpmobil.ticket.core.repository.UserBankCardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @Mock
    private UserBankCardRepository repository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void paymentSuccessTest() {
        PaymentRequest request = new PaymentRequest();
        request.setCardId("card1");
        request.setAmount(2000);
        request.setUserId(1L);

        Users user = new Users();
        user.setUserId(1L);

        UserBankCard userBankCard = new UserBankCard();
        userBankCard.setAmount(3000L);
        userBankCard.setCardId("card1");
        userBankCard.setUser(user);

        Mockito.when(repository.findByCardId(request.getCardId())).thenReturn(Optional.of(userBankCard));

        PaymentResult pay = paymentService.pay(request);
        Assert.assertTrue(pay.isSuccess());
    }

    @Test
    public void paymentFailedTest() {
        PaymentRequest request = new PaymentRequest();
        request.setCardId("card1");
        request.setAmount(4000);
        request.setUserId(1L);

        Users user = new Users();
        user.setUserId(1L);

        UserBankCard userBankCard = new UserBankCard();
        userBankCard.setAmount(3000L);
        userBankCard.setCardId("card1");
        userBankCard.setUser(user);

        Mockito.when(repository.findByCardId(request.getCardId())).thenReturn(Optional.of(userBankCard));
        PaymentResult pay = paymentService.pay(request);
        Assert.assertFalse(pay.isSuccess());
        Assert.assertEquals(10101L, pay.getErrorCode().longValue());
    }

}
