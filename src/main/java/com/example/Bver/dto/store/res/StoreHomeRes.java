package com.example.Bver.dto.store.res;

import com.example.Bver.entity.Store;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class StoreHomeRes {
    private Long storeId;
    private String storeImg;
    private String storeLogo;
    private String storeName;

    public static StoreHomeRes toStoreHomeRes(Store store) {
        return new StoreHomeRes(
                store.getStoreId(),
                store.getStorePhoto(),
                store.getStoreLogo(),
                store.getStoreName()
        );
    }

    public StoreHomeRes(Long storeId, String storeImg, String storeLogo, String storeName) {
        this.storeId = storeId;
        this.storeImg = storeImg;
        this.storeLogo = storeLogo;
        this.storeName = storeName;
    }
}
