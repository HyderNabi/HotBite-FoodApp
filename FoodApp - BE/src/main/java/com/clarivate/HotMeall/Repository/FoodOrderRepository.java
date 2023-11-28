package com.clarivate.HotMeall.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clarivate.HotMeall.DTO.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {

}
