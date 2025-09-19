package com.sweetshop.server.repository;

import com.sweetshop.server.entity.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface SweetRepository extends JpaRepository<Sweet,Long> {

    @Query("UPDATE Sweet SET stockCount=:stock WHERE id=:id")
    void updateStockCount(@Param("id") Long id, @Param("stock") Integer stock);

    List<Sweet> findByName(String name);
    List<Sweet> findByCategory(String name);

    @Query("SELECT s FROM Sweet s WHERE " +
            "(:name IS NULL OR LOWER(s.name) LIKE LOWER(%:name%)) AND " +
            "(:category IS NULL OR LOWER(s.category) = LOWER(:category)) AND " +
            "(:minPrice IS NULL OR s.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR s.price <= :maxPrice)")
    List<Sweet> search(@Param("name") String name,
                       @Param("category") String category,
                       @Param("minPrice") Double minPrice,
                       @Param("maxPrice") Double maxPrice);
}
