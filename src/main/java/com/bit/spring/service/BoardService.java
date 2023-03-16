package com.bit.spring.service;

import com.bit.spring.connector.MySqlConnector;
import com.bit.spring.model.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final String NAMESPACE = "mapper.boardMapper";
    private SqlSession session;
    private final int PAGE_SIZE = 15;
    @Autowired
    public BoardService(SqlSession session) {
        this.session = session;
    }
    public List<BoardDTO> selectAll(int pageNo){
        return session.selectList(NAMESPACE+".selectAll");
    }

//  //mubatis 이전코드
//    private Connection connection;
//    private MySqlConnector mySqlConnector;
//
//    private final int PAGE_SIZE = 10;
//
//    @Autowired
//    public BoardService(MySqlConnector mySqlConnector) {
//        connection = mySqlConnector.makeConnection();
//    }
//
//    //모든 글 출력하기
//    public ArrayList<BoardDTO> selectAll(int pageNo) {
//        ArrayList<BoardDTO> list = new ArrayList<>();
//        String query = "select * from `board` LIMIT ?,?";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, (pageNo - 1) * PAGE_SIZE);
//            preparedStatement.setInt(2, PAGE_SIZE);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                BoardDTO boardDTO = new BoardDTO();
//                boardDTO.setId(resultSet.getInt("id"));
//                boardDTO.setWriterId(resultSet.getInt("writerId"));
//                boardDTO.setContent(resultSet.getString("content"));
//                boardDTO.setTitle(resultSet.getString("title"));
//                boardDTO.setEntrydate(new Date(resultSet.getTimestamp("entrydate").getTime()));
//                boardDTO.setModifydate(new Date(resultSet.getTimestamp("modifydate").getTime()));
//
//                list.add(boardDTO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return list;
//    }
//
//    public BoardDTO selectOne(int id) {
//        BoardDTO b = null;
//        String query = "select * from `board`where `id`=? ";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                b = new BoardDTO();
//                b.setId(resultSet.getInt("id"));
//                b.setWriterId(resultSet.getInt("writerId"));
//                b.setContent(resultSet.getString("content"));
//                b.setTitle(resultSet.getString("title"));
//                b.setEntrydate(new Date(resultSet.getTimestamp("entrydate").getTime()));
//                b.setModifydate(new Date(resultSet.getTimestamp("modifydate").getTime()));
//
//
//            }
//            resultSet.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return b;
//    }
//
//    public void update(BoardDTO boardDTO) {
//        String query = "update  `board` set `title`=?, `content` =?,`modifydate` = NOW() where `id`=? ";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, boardDTO.getTitle());
//            preparedStatement.setString(2, boardDTO.getContent());
//            preparedStatement.setInt(3, boardDTO.getId());
//
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void insert(BoardDTO boardDTO) {
//        String query = "INSERT INTO `board`(`title`, `content`, `writerId`, `entrydate`, `modifydate`) VALUES(?, ?, ?, NOW(), NOW())";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, boardDTO.getTitle());
//            preparedStatement.setString(2, boardDTO.getContent());
//            preparedStatement.setInt(3,boardDTO.getWriterId());
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
//        String query = "delete  from `board`  where `id`=? ";
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
