package hu.otpmobil.common.error;

public enum Errors {

    EVENT_NOT_FOUND(null, 90001, "Nem létezik ilyen esemény!"),
    SEAT_NOT_FOUND(null, 90002, "Nem létezik ilyen szék!"),
    SEAT_ALREADY_RESERVED(null, 90010, "Már lefoglalt székre nem lehet jegyet eladni!"),
    AMOUNT_NOT_ENOUGH("A beérkezett kérésben szereplő bankkártyán nem áll rendelkezésre a megfelelő összeg",
            10101, "A felhasználónak nincs elegendő pénze hogy megvásárolja a jegyet!"),
    TOKEN_NOT_FOUND("A beérkezett kérésben a felhasználó token nem szerepel",
            10050, "A felhasználói token nem szerepel"),
    TOKEN_EXPIRED("A beérkezett kérésben szereplő felhasználó token lejárt vagy nem értelmezhető",
            10051, "A felhasználói token lejárt vagy nem értelmezhető"),
    USER_WITH_NOT_VALID_CARD("A beérkezett kérésben szereplő bankkártya nem az adott felhasználóhoz tartozik", 10100,
            "Ez a bankkártya nem ehhez a felhasználóhoz tartozik"),

    START_TIME_IN_THE_PAST(null, 20011, "Olyan eseményre ami már elkezdődött nem lehet jegyet eladni!"),

    PARTNER_SERVICE_NOT_AVAILABLE(null, 20404, "A külső rendszer nem elérhető!");

    private String event;
    private Integer errorCode;
    private String errorMessage;

    Errors(String event, Integer errorCode, String errorMessage) {
        this.event = event;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getEvent() {
        return event;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
