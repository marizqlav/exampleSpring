package com.app.prueba.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.prueba.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//aqui se ponen las querys que se quieran hacer a la base de datos y no esten en el crud repository (JpaRepository)

    // quiero hacer una query que me  todas las cartas q tiene un usuario
    // select * from carta where user_id = ?

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    public User findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE email = :email AND password = :password", nativeQuery = true)
    public User findUserByEmailAndPassword(String email, String password);


    @Query(value = "SELECT * FROM users WHERE email = :email OR username = :username", nativeQuery = true)
    public User findUserByUsernameOrEmail(String username, String email);

}
