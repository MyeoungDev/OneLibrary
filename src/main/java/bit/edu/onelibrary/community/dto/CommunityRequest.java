package bit.edu.onelibrary.community.dto;

import java.time.LocalDateTime;

public class CommunityRequest {
    private long userNo;
    private String communityTitle;
    private String communityContent;
    private LocalDateTime createAt;

    private String userName;

    private boolean isDeleted;

    public CommunityRequest(long userNo, String communityTitle, String communityContent,String userName) {
        this.userNo = userNo;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.userName = userName;
        this.createAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    public long getUserNo() {
        return userNo;
    }

    public String getCommunityTitle() {
        return communityTitle;
    }

    public String getCommunityContent() {
        return communityContent;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getUserName() {
        return userName;
    }

}
