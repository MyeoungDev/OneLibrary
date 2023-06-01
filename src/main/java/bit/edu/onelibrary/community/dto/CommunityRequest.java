package bit.edu.onelibrary.community.dto;

import java.time.LocalDateTime;

public class CommunityRequest {
    private long userNo;
    private String communityTitle;
    private String communityContent;
    private LocalDateTime createAt;
    private boolean isDeleted;

    public CommunityRequest(long userNo, String communityTitle, String communityContent, LocalDateTime createAt, boolean isDeleted) {
        this.userNo = userNo;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.createAt = createAt;
        this.isDeleted = isDeleted;
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
}
