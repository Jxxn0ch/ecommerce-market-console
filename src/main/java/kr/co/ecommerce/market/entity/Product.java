package kr.co.ecommerce.market.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Entity(name = "product")
public class Product {
    // 상품 ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 상품 코드
    @Column(name = "code")
    private int code;

    // 상품명
    @Column(name = "name")
    private String name;

    // 상품 가격
    @Column(name = "price")
    private int price;

    // 상품 수량
    @Column(name = "quantity")
    private int quantity;

    // 삭제 여부
    @Column(name = "delete_yn", columnDefinition = "char(1) default 'N'")
    private String deleteYn;

    // 등록일
    @Column(name = "created_date", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    // 수정일
    @Column(name = "last_modified_date", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime lastModifiedDate;

    @PrePersist
    public void prePersist() {
        this.createdDate= LocalDateTime.now();
        this.preUpdate();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
