package com.sweetshop.server.repository;

import com.sweetshop.server.entity.Sweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SweetRepository extends CrudRepository<Sweet,Long> {

}
