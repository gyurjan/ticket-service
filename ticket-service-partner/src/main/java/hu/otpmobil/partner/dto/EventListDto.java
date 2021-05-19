package hu.otpmobil.partner.dto;

import hu.otpmobil.common.dto.EventDto;

import java.util.ArrayList;
import java.util.List;

public class EventListDto {

    private List<EventDto> data = new ArrayList<>();
    private Boolean success;

    public List<EventDto> getData() {
        return data;
    }

    public void setData(List<EventDto> data) {
        this.data = data;

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
