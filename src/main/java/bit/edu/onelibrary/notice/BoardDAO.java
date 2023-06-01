package bit.edu.onelibrary.notice;


import bit.edu.onelibrary.util.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {

    public boolean insert(BoardDTO item) throws SQLException, IOException {
        // 하고자하는 일을 하나 처리했다면 커넥션 끊어야함(최소가 되도록)
        boolean flag = false;
        Connection con = ConnectionManager.getConnection();

        String sql = "insert into notice(notice_title,notice_content,notice_user_no) values(?,?,?) ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, item.getBtitle());
        pstmt.setString(2, item.getBcontent());
        pstmt.setString(3, item.getBwriter());

        int affectedCount = pstmt.executeUpdate(); // 몇개의 record 변화시켰는지
        if(affectedCount>0){
            flag=true;
        }

        ConnectionManager.closeConnection(con, pstmt, null);

        return flag;
    }

    public BoardDTO select(int bno) throws SQLException, IOException {
        BoardDTO item = null;

        Connection con = ConnectionManager.getConnection();
        String sql = "select * from notice where notice_no = ? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, bno);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            // 로직이 들어오지 않는다 !
            // DB의 내용을 로컬데이터셋(java beans)으로 저장하는 것이 주 목적
            item = new BoardDTO(bno, rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getDate(5));
        }

        ConnectionManager.closeConnection(con, pstmt, rs);

        return item;
    }

    public boolean delete(String sql) throws SQLException, IOException {
        // 게시글삭제(개별, 전체 모두처리)
        boolean flag = false;
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        int affectedCount = pstmt.executeUpdate();
        if (affectedCount>0){
            flag = true;
        }

        ConnectionManager.closeConnection(con, pstmt, null);
        return flag;
    }

    public ArrayList<BoardDTO> selectAll() throws SQLException, IOException {
        ArrayList<BoardDTO> list = new ArrayList<>();

        Connection con = ConnectionManager.getConnection();
        String sql = "select * from notice ";
        PreparedStatement pstmt = con.prepareStatement(sql); // statement 상속하므로 써도 문제없음
        // prepared statement를 사용하는 세가지 이유
        // 1. 데이터 1000개라도 반복하지 않고 한번만 써주면됨(?)
        // 2. 오브젝트를 저장하기 위함(setObjcet)
        // 3. 쿼리를 저장할 때 +나 append 사용하면 에러날 수 있으므로 올바르게 집어넣기 위함

        ResultSet rs = pstmt.executeQuery();
        BoardDTO item;

        while(rs.next()){
            // 로직이 들어오지 않는다 !
            // DB의 내용을 로컬데이터셋(java beans)으로 저장하는 것이 주 목적
            item = new BoardDTO(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getDate(5));
            list.add(item);
        }

        ConnectionManager.closeConnection(con, pstmt, rs);

        return list;
    }

    public boolean update(BoardDTO item) throws SQLException, IOException {
        boolean flag = false;
        Connection con = ConnectionManager.getConnection();
        String sql = "update notice set notice_title = ?, notice_content = ?, notice_user_no = ? where notice_no = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, item.getBtitle());
        pstmt.setString(2, item.getBcontent());
        pstmt.setString(3, item.getBwriter());
        pstmt.setInt(4, item.getBno());
        int affectedCount = pstmt.executeUpdate();
        if(affectedCount>0){
            flag = true;
        }

        ConnectionManager.closeConnection(con, pstmt, null);
        return flag;
    }
}
