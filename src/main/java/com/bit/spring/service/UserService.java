package com.bit.spring.service;

import com.bit.spring.connector.MySqlConnector;
import com.bit.spring.model.BoardDTO;
import com.bit.spring.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {

    private final String NAMESPACE = "mapper.userMapper";
    private SqlSession session;
    @Autowired
    public UserService(SqlSession session) {
        this.session = session;
    }

    public UserDTO auth(UserDTO attempt) {

        return session.selectOne(NAMESPACE+".auth", attempt);
    }

// //mybatis 이전코드
//    private MySqlConnector connector;
//    private Connection connection;
//
//    @Autowired
//    public UserService(MySqlConnector connector) {
//        this.connector = connector;
//        connection = connector.makeConnection();
//    }
//
//    public UserDTO selectOne(int id) {
//        String query = "SELECT * FROM `user` where id=?";
//        UserDTO userDTO = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                userDTO = new UserDTO();
//                userDTO.setId(resultSet.getInt("id"));
//                userDTO.setUsername(resultSet.getString("username"));
//                userDTO.setNickname(resultSet.getString("nickname"));
//
//
//            }
//            resultSet.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return userDTO;
//    }
//    public UserDTO auth(UserDTO attempt) {
//        String query = "SELECT * FROM `user` where `username`=? and `password`=?";
//        UserDTO userDTO = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, attempt.getUsername());
//            preparedStatement.setString(2, attempt.getPassword());
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                userDTO = new UserDTO();
//                userDTO.setId(resultSet.getInt("id"));
//                userDTO.setUsername(resultSet.getString("username"));
//                userDTO.setNickname(resultSet.getString("nickname"));
//
//            }
//            resultSet.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return userDTO;
//    }
//
//    public void update(UserDTO userDTO) {
//
//        String query = "update  `user` set `nickname`=?  where `id`=? ";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, userDTO.getNickname());
//            preparedStatement.setInt(2, userDTO.getId());
//
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void insert(UserDTO userDTO) {
//        String query = "INSERT INTO `user`(`username`, `password`, `nickname`) VALUES(?, ?, ?)";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, userDTO.getUsername());
//            preparedStatement.setString(2, userDTO.getPassword());
//            preparedStatement.setString(3,userDTO.getNickname());
//
//
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete(int id) {
//        String query = "delete  from `user`  where `id`=? ";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//
//
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
