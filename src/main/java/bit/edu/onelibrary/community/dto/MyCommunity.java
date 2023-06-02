package bit.edu.onelibrary.community.dto;

import java.time.LocalDateTime;

public class MyCommunity {
    private long communityNo;
    private String title;
    private String communityContent;
    private LocalDateTime createAt;
    private boolean isDeleted;
    private long userNo;

    public MyCommunity(long communityNo, String title, String communityContent, LocalDateTime createAt, boolean isDeleted, long userNo) {
        this.communityNo = communityNo;
        this.title = title;
        this.communityContent = communityContent;
        this.createAt = createAt;
        this.isDeleted = isDeleted;
        this.userNo = userNo;
    }
    public MyCommunity(String title, String communityContent, long userNo, LocalDateTime createAt){
        this.title = title;
        this.communityContent = communityContent;
        this.userNo = userNo;
        this.createAt = createAt;
    }

    public long getCommunityNo() {
        return communityNo;
    }

    public String getTitle() {
        return title;
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

    public long getUserNo() {
        return userNo;
    }
}

