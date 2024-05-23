package com.springboot.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.consumer.entity.WikimediaEntity;

public interface WikimediaRepository extends JpaRepository<WikimediaEntity, Long> {

}
