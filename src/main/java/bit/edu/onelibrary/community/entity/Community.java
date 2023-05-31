package bit.edu.onelibrary.community.entity;

import java.time.LocalDateTime;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Community {

    private long communityNo;
    private long userNo;
    private String communityTitle;
    private String communityContent;
    private LocalDateTime createAt;
    private boolean isDeleted;

    public Community(long communityNo, long userNo, String communityTitle, String communityContent) {
        this.communityNo = communityNo;
        this.userNo = userNo;
        this.communityTitle = communityTitle;
        this.communityContent = communityContent;
        this.createAt = LocalDateTime.now();
        this.isDeleted = false;
    }
}
