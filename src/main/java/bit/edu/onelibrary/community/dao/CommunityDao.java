package bit.edu.onelibrary.community.dao;

import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.community.dto.CommunityModifyDTO;
import bit.edu.onelibrary.community.dto.CommunityRequest;
import bit.edu.onelibrary.community.dto.MyCommunity;
import bit.edu.onelibrary.community.entity.Community;
import bit.edu.onelibrary.util.ConnectionManager;
import com.sun.jdi.VMOutOfMemoryException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class CommunityDao {

    public void insertCommunity(CommunityRequest request) throws IOException, SQLException {
        Connection connection = ConnectionManager.getConnection();
        String sql = "insert into community value(?,?,?,now())";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, request.getCommunityTitle());
        preparedStatement.setString(2, request.getCommunityContent());
        preparedStatement.setString(3, request.getUserName());
        preparedStatement.executeUpdate();

        ConnectionManager.closeConnection(connection, preparedStatement, null);


    }

    public List<CommunityDto> findAllCommunities() throws IOException, SQLException {
        List<CommunityDto> communityDtoList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        String sql = "SELECT title FROM community";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            communityDtoList.add(new CommunityDto(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getLong(3),
                    resultSet.getTimestamp(4).toLocalDateTime()));
        }

        ConnectionManager.closeConnection(connection, preparedStatement, resultSet);

        return communityDtoList;
    }

    public List<MyCommunity> findCommunitiesByMemberNo(long userNo) throws IOException, SQLException {
        List<MyCommunity> myCommunityDtos = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        String sql = "SELECT * FROM community where member_no = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, userNo);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            myCommunityDtos.add(new MyCommunity(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getLong(3),
                    resultSet.getTimestamp(4).toLocalDateTime()));
        }
        preparedStatement.executeUpdate();

        ConnectionManager.closeConnection(connection, preparedStatement, resultSet);

        return myCommunityDtos;

    }

    public CommunityDto findCommunityByCommunityNo(long communityNo) throws IOException, SQLException {
        CommunityDto communityDto = null;
        Connection connection = ConnectionManager.getConnection();
        String sql = "SELECT * FROM community where community_no = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, communityNo);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            communityDto = new CommunityDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getLong(3),
                    resultSet.getTimestamp(4).toLocalDateTime()
            );
        }

        ConnectionManager.closeConnection(connection, preparedStatement, resultSet);

        return communityDto;
    }

    public void updateCommunity(CommunityModifyDTO modifyDTO) throws IOException, SQLException {
        Connection connection = ConnectionManager.getConnection();
        CommunityDto communityDto = findCommunityByCommunityNo(modifyDTO.getCommunityNo());




        // 만약 title이 null이면 문제가 있겠져?
        // 그렇다면 기존에 쓰였던 제목을 그대로 넣어주면 문제가 없겟죠?

        if(modifyDTO.getTitle() == null){
            modifyDTO.setTitle(communityDto.getTitle());
        }
        if (modifyDTO.getCommunityContent() == null){
            modifyDTO.setCommunityContent(communityDto.getCommunityContent());
        }

        // 제목이랑 똑같이 내용도 해주면 문제가 없겠죠?
        // 이렇게 되면 제목만 수정하거나, 내용 만 수정하는 것이 문제가 없겠죠?
        String sql = "update community set title = ?, content = ? where community_no = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, communityDto.getTitle());
        preparedStatement.setString(2, modifyDTO.getCommunityContent());
        preparedStatement.setLong(3, communityDto.getUserNo());
        preparedStatement.executeUpdate();

        ConnectionManager.closeConnection(connection, preparedStatement, null);

    }


    public void deleteCommunity(long communityNo) throws IOException, SQLException {
        Connection connection = ConnectionManager.getConnection();
        String sql = "delete from community where community_no = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, communityNo);
        preparedStatement.executeUpdate();

        ConnectionManager.closeConnection(connection, preparedStatement, null);


    }


}
