package hu.otpmobile.ticket.core.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
public class UserBankCard {

    @Id
    private String cardId;
    private String cardnumber;
    private String cvc;
    private String name;
    private Long amount;
    private Currency currency;

    @OneToOne
    @JoinColumn(name="user_id")
    private Users user;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
