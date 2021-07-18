package com.thewalking.shop.dto;

import lombok.Data;

public class ProductStoreTotalReportDto {

    private Integer totalCount;
    private Long productId;
    private Long productDescriptionId;
    private String SKU;
    private String size;
    private String title;
    private String brand;
    private String image;
    private Double rating;

    public ProductStoreTotalReportDto(Integer totalCount, Long productId, Long productDescriptionId, String SKU, String size, String title, String brand, String image, Double rating) {
        this.totalCount = totalCount;
        this.productId = productId;
        this.productDescriptionId = productDescriptionId;
        this.SKU = SKU;
        this.size = size;
        this.title = title;
        this.brand = brand;
        this.image = image;
        this.rating = rating;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductDescriptionId() {
        return productDescriptionId;
    }

    public void setProductDescriptionId(Long productDescriptionId) {
        this.productDescriptionId = productDescriptionId;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProductStoreTotalReportDto{" +
                "totalCount=" + totalCount +
                ", productId=" + productId +
                ", productDescriptionId=" + productDescriptionId +
                ", SKU='" + SKU + '\'' +
                ", size='" + size + '\'' +
                ", title='" + title + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                '}';
    }
    //0: 300, 1: 10, 2: 9, 3: T-shirt-wht-XL, 4: XL, 5: shirt v-x, 6: brand, 7: image link, 8: 4.0
}
