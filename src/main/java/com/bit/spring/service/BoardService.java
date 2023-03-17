package com.bit.spring.service;


import com.bit.spring.model.BoardDTO;
import com.bit.spring.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private final String NAMESPACE = "mapper.boardMapper";
    private SqlSession session;
    private final int PAGE_SIZE = 10;
    @Autowired
    public BoardService(SqlSession session) {
        this.session = session;
    }
    public List<BoardDTO> selectAll(int pageNo){
        HashMap<String, Integer> params = new HashMap<>();
        params.put("start", (pageNo-1)*PAGE_SIZE);
        params.put("size",PAGE_SIZE);

        return session.selectList(NAMESPACE+".selectAll",params);
    }

    public BoardDTO selectOne(int id){
        return session.selectOne(NAMESPACE+".selectOne",id);
    }

    public void insert(BoardDTO boardDTO){
        session.insert(NAMESPACE+".insert",boardDTO);
    }

    public void update(BoardDTO attempt){
        session.update(NAMESPACE+".update", attempt);
    }
    public void delete(int id){
        session.delete(NAMESPACE+".delete",id);
    }
    public int selectLastPage(){
        int count = session.selectOne(NAMESPACE+".count");
        int total = count/PAGE_SIZE;
        if(count % PAGE_SIZE !=0){
            total++;
        }

        return total;
    }

    public Map<String, Object> selectByKeyword(String keyword, int pageNo){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("start",(pageNo -1)*PAGE_SIZE);
        params.put("size", PAGE_SIZE);
        params.put("keyword",keyword);
        result.put("list",session.selectList(NAMESPACE+".selectByKeyword",params));
        result.put("totalPage", countSearchResult(keyword));
        return result;
    }

    public int countSearchResult(String keyword){
        int temp = session.selectOne(NAMESPACE+".countSearchResult",keyword);
        int totalPage = temp/PAGE_SIZE;
        if (temp % PAGE_SIZE != 0) {
            totalPage ++;
        }
        return totalPage;
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
