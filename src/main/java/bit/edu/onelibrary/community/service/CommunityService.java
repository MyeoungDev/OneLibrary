package bit.edu.onelibrary.community.service;

import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.community.dto.CommunityModifyDTO;
import bit.edu.onelibrary.community.dto.CommunityRequest;
import bit.edu.onelibrary.community.dto.MyCommunity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public interface CommunityService {

    void createCommunity(CommunityRequest request) throws SQLException, IOException;

    List<CommunityDto> getAllCommunities() throws SQLException, IOException;

    List<MyCommunity> getMyCommunities(long userNo) throws SQLException, IOException;

    CommunityDto getCommunityByCommunityNo(long communityNo) throws SQLException, IOException;

    void updateCommunity(CommunityModifyDTO communityModifyDTO) throws SQLException, IOException;

    void deleteCommunity(long communityNo) throws SQLException, IOException;
}
