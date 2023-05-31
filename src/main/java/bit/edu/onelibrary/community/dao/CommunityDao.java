package bit.edu.onelibrary.community.dao;

import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.util.ConnectionManager;
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

    public List<CommunityDto> findAllCommunties() {
        List<CommunityDto> communityDtoList = new ArrayList<>();

        try {
            Connection connection = ConnectionManager.getConnection();
            String sql = "SELECT title FROM community";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                communityDtoList.add(new CommunityDto(resultSet.getString(1)));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return communityDtoList;
    }
}
