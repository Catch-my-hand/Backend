package com.catch_my_hand.backend.home_sale.Repository;

import com.catch_my_hand.backend.home_sale.Entity.Outpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutpostRepository extends JpaRepository<Outpost, Integer> {

}
