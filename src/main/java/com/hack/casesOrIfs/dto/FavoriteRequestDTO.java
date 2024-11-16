package com.hack.casesOrIfs.dto;

public class FavoriteRequestDTO {
    private Long userId;
    private Long contentId;
    
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getContentId() {
		return contentId;
	}
	
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	
}
