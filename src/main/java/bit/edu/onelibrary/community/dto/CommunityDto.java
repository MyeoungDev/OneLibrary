package bit.edu.onelibrary.community.dto;

import java.time.LocalDateTime;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class CommunityDto {
    private long communityNo;
    private String title;
    private String communityContent;
    private LocalDateTime createAt;
    private boolean isDeleted;
    private long userNo;

    public CommunityDto(long communityNo, String title, String communityContent, LocalDateTime createAt, long userNo) {
        this.communityNo = communityNo;
        this.title = title;
        this.communityContent = communityContent;
        this.createAt = createAt;
        this.userNo = userNo;
    }
    public String getTitle(){
        return title;
    }

    public String getCommunityContent(){
        return communityContent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCommunityContent(String communityContent){
        this.communityContent = communityContent;
    }

    public long getCommunityNo() {
        return communityNo;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public long getUserNo(){
        return userNo;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    // 더 필요한건 아진님이 작성!
}
