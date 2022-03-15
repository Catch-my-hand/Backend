package com.catch_my_hand.backend.home_sale.Repository;

import com.catch_my_hand.backend.home_sale.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}


//성공