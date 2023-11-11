package com.example.Bver.dto.store.res;

import com.example.Bver.entity.Bread;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
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
    private String openTime;
    private String location;
    private Boolean isLike;
    private List<MenuBriefRes> menuList;

    @Getter @Setter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class MenuBriefRes {
        private Long menuId;
        private String menuName;
        private String menuImg;
        private int quantity;
        private int price;
        private int discountedPrice;

        static public MenuBriefRes toMenuBriefRes(Bread bread) {
            return new MenuBriefRes(
                    bread.getBreadId(),
                    bread.getName(),
                    bread.getImage(),
                    bread.getQuantity(),
                    bread.getPrice(),
                    bread.getDiscountedPrice()
            );
        }
    }
}
