package bit.edu.onelibrary.community.entity;

import java.time.LocalDateTime;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Community {  //DB테이블 그 자체. DB의 모든 컬럼을 속성으로 가지고 있어야함.

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
