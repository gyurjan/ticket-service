package hu.otpmobil.common.dto;

public class UserValidationResponseDto {

    private Long userId;
    private Boolean success;
    private String event;
    private Integer errorCode;
    private String errorMessage;

    public UserValidationResponseDto(Long userId, Boolean success, String event, Integer errorCode, String errorMessage) {
        this.userId = userId;
        this.success = success;
        this.event = event;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
