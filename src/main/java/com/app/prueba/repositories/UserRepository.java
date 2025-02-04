package com.app.prueba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.prueba.models.Cards;
import com.app.prueba.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//aqui se ponen las querys que se quieran hacer a la base de datos y no esten en el crud repository (JpaRepository)

    // quiero hacer una query que me  todas las cartas q tiene un usuario
    // select * from carta where user_id = ?


    @Query(value = "SELECT * FROM cards WHERE user_id = :userId", nativeQuery = true)
    public List<Cards> findCardsByUserId(Integer userId);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    public User findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE email = :email AND password = :password", nativeQuery = true)
    public User findUserByEmailAndPassword(String email, String password);
}
