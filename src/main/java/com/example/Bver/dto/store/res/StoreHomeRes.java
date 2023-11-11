package com.example.Bver.dto.store.res;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class StoreHomeRes {
    private List<StoreBriefRes> discountList;
    private List<StoreBriefRes> nearbyList;

    public StoreHomeRes(List<StoreBriefRes> discountList, List<StoreBriefRes> nearbyList) {
        this.discountList = discountList;
        this.nearbyList = nearbyList;
    }
}
