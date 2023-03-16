package com.bit.spring.service;

import com.bit.spring.connector.MySqlConnector;
import com.bit.spring.model.BoardDTO;
import com.bit.spring.model.ReplyDTO;
import com.bit.spring.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class ReplyService {
    private Connection connection;
    private MySqlConnector connector;

    @Autowired
    public ReplyService(MySqlConnector connector) {
        this.connector = connector;
        connection = connector.makeConnection();
    }


    public ArrayList<ReplyDTO> selectAll(int boardId) {
        ArrayList<ReplyDTO> list = new ArrayList<>();
        String query = "select * from `reply` where `boardId`=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,boardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ReplyDTO replyDTO = new ReplyDTO();
                replyDTO.setId(resultSet.getInt("id"));
                replyDTO.setWriterId(resultSet.getInt("writerId"));
                replyDTO.setBoardId(resultSet.getInt("boardId"));
                replyDTO.setContent(resultSet.getString("content"));
                replyDTO.setEntrydate(new Date(resultSet.getTimestamp("entrydate").getTime()));
                replyDTO.setModifydate(new Date(resultSet.getTimestamp("modifydate").getTime()));

                list.add(replyDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public void update(ReplyDTO replyDTO) {
        String query = "update  `reply` set  `content` =?,`modifydate` = NOW() where `id`=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, replyDTO.getContent());
            preparedStatement.setInt(2, replyDTO.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insert(ReplyDTO replyDTO) {
        String query = "INSERT INTO `reply`(`content`, `writerId`,`boardId`, `entrydate`, `modifydate`) VALUES(?, ?, ?,NOW(), NOW())";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, replyDTO.getContent());
            preparedStatement.setInt(2, replyDTO.getWriterId());
            preparedStatement.setInt(3,replyDTO.getBoardId());



            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "delete  from `board`  where `id`=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);


            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


