package com.brainwired.task.repository;

import com.brainwired.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "UPDATE User u SET u.enable= ?2 WHERE u.id= ?1")
    public Integer updateEnableById(@Param("idd") Long idd,@Param("status") Boolean status);

}
