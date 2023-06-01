package bit.edu.onelibrary.community.service.impl;

import bit.edu.onelibrary.community.dao.CommunityDao;
import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.community.dto.CommunityModifyDTO;
import bit.edu.onelibrary.community.dto.CommunityRequest;
import bit.edu.onelibrary.community.service.CommunityService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class CommunityServiceImpl implements CommunityService {

    private final CommunityDao communityDao;

    public CommunityServiceImpl(CommunityDao communityDao) {
        this.communityDao = communityDao;
    }

    @Override
    public void createCommunity(CommunityRequest request) throws SQLException, IOException {
        communityDao.insertCommunity(request);
    }

    @Override
    public List<CommunityDto> getAllCommunities() throws SQLException, IOException {
        return communityDao.findAllCommunities();
    }

    @Override
    public List<String> getMyCommunities(int memberNo) throws SQLException, IOException {
        return communityDao.findCommunitiesByMemberNo(memberNo);
    }

    @Override
    public CommunityDto getCommunityByCommunityNo(int communityNo) throws SQLException, IOException {
        return communityDao.findCommunityByCommunityNo(communityNo);
    }

    @Override
    public void updateCommunity(CommunityModifyDTO communityModifyDTO) throws SQLException, IOException {
        communityDao.updateCommunity(communityModifyDTO);

    }

    @Override
    public void deleteCommunity(long communityNo) throws SQLException, IOException {
        communityDao.deleteCommunity(communityNo);
    }

    // 수정 로직
    // 1. 이 게시글의 userNo 랑 지금 로그인되어 있는 내 userNo가 같은지 확인
    // 2. 만약 같다면 그때 수정 시켜주기
    // 3. 만약 틀리다면 수정 불가능


}
