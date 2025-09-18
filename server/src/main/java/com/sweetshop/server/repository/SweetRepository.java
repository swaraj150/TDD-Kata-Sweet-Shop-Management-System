package com.sweetshop.server.repository;

import com.sweetshop.server.entity.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface SweetRepository extends JpaRepository<Sweet,Long> {

    @Query("UPDATE Sweet SET stockCount=:stock WHERE id=:id")
    void updateStockCount(@RequestParam Long id, @RequestParam Integer stock);

    List<Sweet> findByName(String name);
    List<Sweet> findByCategory(String name);

    @Query("SELECT s FROM Sweet s WHERE s.price>=:p1 AND s.price<=:p2")
    List<Sweet> findByPriceRange(@RequestParam Double p1,@RequestParam Double p2);
}
