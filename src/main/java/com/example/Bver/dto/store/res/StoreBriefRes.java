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
public class StoreBriefRes {
    private Long storeId;
    private String storeImg;
    private String storeLogo;
    private String storeName;
    private String location;

    public static StoreBriefRes toStoreBriefRes(Store store) {
        return new StoreBriefRes(
                store.getStoreId(),
                store.getStorePhoto(),
                store.getStoreLogo(),
                store.getStoreName(),
                store.getLocation()
        );
    }

    public StoreBriefRes(Long storeId, String storeImg, String storeLogo, String storeName, String location) {
        this.storeId = storeId;
        this.storeImg = storeImg;
        this.storeLogo = storeLogo;
        this.storeName = storeName;
        this.location = location;
    }
}
