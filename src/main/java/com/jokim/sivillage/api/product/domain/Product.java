package com.jokim.sivillage.api.product.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 36, unique = true)
    private String productCode;
    @Column(nullable = false, length = 255)
    private String productName;
    @Column(nullable = true, columnDefinition = "boolean default true")
    private boolean isOnSale;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String detail;
    @Column(nullable = false)
    private Double standardPrice;
    @Column(nullable = false)
    private Double discountPrice;

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }


    public static class ProductBuilder {

        private Long id;
        private String productCode;
        private String productName;
        private boolean isOnSale;
        private String detail;
        private Double standardPrice;
        private Double discountPrice;

        ProductBuilder() {
        }

        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder productCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public ProductBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductBuilder isOnSale(boolean isOnSale) {
            this.isOnSale = isOnSale;
            return this;
        }

        public ProductBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ProductBuilder standardPrice(Double standardPrice) {
            this.standardPrice = standardPrice;
            return this;
        }

        public ProductBuilder discountPrice(Double discountPrice) {
            this.discountPrice = discountPrice;
            return this;
        }

        public Product build() {
            return new Product(this.id, this.productCode, this.productName, this.isOnSale,
                this.detail, this.standardPrice, this.discountPrice);
        }

        public String toString() {
            return "Product.ProductBuilder(id=" + this.id + ", productCode=" + this.productCode
                + ", productName=" + this.productName + ", isOnSale=" + this.isOnSale + ", detail="
                + this.detail + ", standardPrice=" + this.standardPrice + ", discountPrice="
                + this.discountPrice + ")";
        }
    }
}
