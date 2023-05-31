package bit.edu.onelibrary.community.service.impl;

import bit.edu.onelibrary.community.dao.CommunityDao;
import bit.edu.onelibrary.community.dto.CommunityDto;
import bit.edu.onelibrary.community.service.CommunityService;
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
    public List<CommunityDto> getAllCommunities() {
        return communityDao.findAllCommunties();
    }

}
