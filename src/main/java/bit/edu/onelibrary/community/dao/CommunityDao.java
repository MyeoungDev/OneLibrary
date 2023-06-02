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
        String sql = "insert into community(user_no, community_title, community_content) value(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, request.getUserNo());
        preparedStatement.setString(2, request.getCommunityTitle());
        preparedStatement.setString(3, request.getCommunityContent());
        preparedStatement.executeUpdate();

        ConnectionManager.closeConnection(connection, preparedStatement, null);


    }

    public List<CommunityDto> findAllCommunities() throws IOException, SQLException {
        List<CommunityDto> communityDtoList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        String sql = "SELECT * FROM community";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            communityDtoList.add(new CommunityDto(
                        resultSet.getLong("community_no"),
                        resultSet.getString("community_title"),
                        resultSet.getString("community_content"),
                        resultSet.getTimestamp("crate_at").toLocalDateTime(),
                        resultSet.getLong("user_no")
            ));
        }

        ConnectionManager.closeConnection(connection, preparedStatement, resultSet);

        return communityDtoList;
    }

    public List<MyCommunity> findCommunitiesByMemberNo(long userNo) throws IOException, SQLException {
        List<MyCommunity> myCommunityDtos = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        String sql = "SELECT * FROM community WHERE user_no = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, userNo);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            myCommunityDtos.add(new MyCommunity(
                    resultSet.getLong("community_no"),
                    resultSet.getString("community_title"),
                    resultSet.getString("community_content"),
                    resultSet.getTimestamp("crate_at").toLocalDateTime(),
                    resultSet.getLong("user_no")
                    )
            );
        }
//        preparedStatement.executeUpdate();

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
                    resultSet.getLong("community_no"),
                    resultSet.getString("community_title"),
                    resultSet.getString("community_content"),
                    resultSet.getTimestamp("crate_at").toLocalDateTime(),
                    resultSet.getLong("user_no")
            );
        }

        ConnectionManager.closeConnection(connection, preparedStatement, resultSet);

        return communityDto;
    }

    public void updateCommunity(CommunityModifyDTO modifyDTO) throws IOException, SQLException {
        Connection connection = ConnectionManager.getConnection();
        CommunityDto communityDto = findCommunityByCommunityNo(modifyDTO.getCommunityNo());

        if(modifyDTO.getTitle() == null){
            modifyDTO.setTitle(communityDto.getTitle());
        }
        if (modifyDTO.getCommunityContent() == null){
            modifyDTO.setCommunityContent(communityDto.getCommunityContent());
        }

        String sql = "UPDATE community SET community_title = ?, community_content = ? WHERE community_no = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, modifyDTO.getTitle());
        preparedStatement.setString(2, modifyDTO.getCommunityContent());
        preparedStatement.setLong(3, modifyDTO.getCommunityNo());
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
