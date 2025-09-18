package com.sweetshop.server.repository;

import com.sweetshop.server.entity.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface SweetRepository extends JpaRepository<Sweet,Long> {

    @Query("UPDATE Sweet SET stockCount=:stock WHERE id=:id")
    void updateStockCount(@RequestParam Long id, @RequestParam Integer stock);

}
