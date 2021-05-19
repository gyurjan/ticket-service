package hu.otpmobil.partner.dto;

import hu.otpmobil.common.dto.EventDetailsDto;

public class EventDetailsListDto {

    private EventDetailsDto data;
    private Boolean success;

    public EventDetailsDto getData() {
        return data;
    }

    public void setData(EventDetailsDto data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
