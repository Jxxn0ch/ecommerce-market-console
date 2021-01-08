package kr.co.ecommerce.market.repository;

import kr.co.ecommerce.market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * 상품 목록 호출
     *
     * @param deleteYn
     */
    List<Product> findAllByDeleteYnOrderByCodeDesc(String deleteYn);

    /**
     * 선택한 상품 코드 목록 호출
     *
     * @param codes
     * @param deleteYn
     * @return List<Product>
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Product> findAllByCodeInAndDeleteYn(List<Integer> codes, String deleteYn);
}
