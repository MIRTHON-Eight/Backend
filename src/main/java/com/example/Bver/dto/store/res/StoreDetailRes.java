package com.example.Bver.dto.store.res;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class StoreDetailRes {
    private String storeImg;
    private String storeLogo;
    private String storeName;
    private String location;
    private Boolean isLike;
    private List<MenuBriefRes> menuList;

    @Getter @Setter
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class MenuBriefRes {
        private String menuName;
        private String menuImg;
        private int quantity;
    }
}
