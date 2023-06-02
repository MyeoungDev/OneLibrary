package bit.edu.onelibrary.community.dto;

public class CommunityModifyDTO {
    private long communityNo;
    private String title;
    private String communityContent;
    private long userNo;

    public CommunityModifyDTO(long communityNo, String title, String communityContent, long userNo) {
        this.communityNo = communityNo;
        this.title = title;
        this.communityContent = communityContent;
        this.userNo = userNo;
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

    public long getUserNo() {
        return userNo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCommunityContent(String communityContent) {
        this.communityContent = communityContent;
    }
}
