package com.anulengure5.websocialapp.Repository;

import com.anulengure5.websocialapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {

public User findByEmail(String email);
//JPQL , this is custom implementation cause it was advanced query , basic methods dont even need to implement ,
//   JPA does auto implemetation with name
@Query("select u from  User u where u.firstName LIKE %:query% OR  u.lastName LIKE %:query% OR u.email LIKE %:query%")
public List<User> searchUser(@Param("query") String query);


}
