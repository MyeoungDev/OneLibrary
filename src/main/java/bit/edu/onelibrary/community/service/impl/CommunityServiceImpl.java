package bit.edu.onelibrary.community.service.impl;

import bit.edu.onelibrary.community.dao.CommunityDao;
import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.community.dto.CommunityModifyDTO;
import bit.edu.onelibrary.community.dto.CommunityRequest;
import bit.edu.onelibrary.community.service.CommunityService;
import bit.edu.onelibrary.user.dao.UserDao;
import bit.edu.onelibrary.util.AuthenticationStorage;

import java.io.IOException;
import java.io.ObjectInput;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class CommunityServiceImpl implements CommunityService {

    private final CommunityDao communityDao;
    private final UserDao userDao;

    public CommunityServiceImpl(CommunityDao communityDao, UserDao userDao) {
        this.communityDao = communityDao;
        this.userDao = userDao;
    }

    @Override
    public void createCommunity(CommunityRequest request) throws SQLException, IOException {
//        String userName = userDao.findUserNameByUserNo(long userNo);
//        request.setUserName(userName);
//        communityDao.insertCommunity(request);
    }

    @Override
    public List<CommunityDto> getAllCommunities() throws SQLException, IOException {
        return communityDao.findAllCommunities();
    }

    @Override
    public List<String> getMyCommunities(long userNo) throws SQLException, IOException {
        return communityDao.findCommunitiesByMemberNo(userNo);
    }

    @Override
    public CommunityDto getCommunityByCommunityNo(long communityNo) throws SQLException, IOException {
        return communityDao.findCommunityByCommunityNo(communityNo);
    }

    @Override
    public void updateCommunity(CommunityModifyDTO communityModifyDTO) throws SQLException, IOException {
        if(Objects.equals(communityModifyDTO.getUserNo(),AuthenticationStorage.getAuthentication().getUserNo())) {
            communityDao.updateCommunity(communityModifyDTO);
        }
        else {
            System.out.println("본인이 작성한 독후감만 수정할 수 있습니다.");
        }

    }

    @Override
    public void deleteCommunity(long communityNo) throws SQLException, IOException {
        if(Objects.equals(communityNo,AuthenticationStorage.getAuthentication().getUserNo())) {
            communityDao.deleteCommunity(communityNo);
        }
        else {
            System.out.println("본인이 작성한 독후감만 삭제할 수 있습니다.");
        }
    }

    // 수정 로직
    // 1. 이 게시글의 userNo 랑 지금 로그인되어 있는 내 userNo가 같은지 확인
    // 2. 만약 같다면 그때 수정 시켜주기
    // 3. 만약 틀리다면 수정 불가능


}
