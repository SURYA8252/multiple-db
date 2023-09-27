package com.multiple.db.postgres2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.db.postgres2.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
