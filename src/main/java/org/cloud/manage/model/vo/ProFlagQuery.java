package org.cloud.manage.model.vo;

public class ProFlagQuery {
	//主键ID
	private Long id;
	
	//物理服务器id
	private Long tagId;
	
	//类型
	private Long parentId;

	public Long getId() {
		return id;
	}

	public Long getTagId() {
		return tagId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "ProFlagQuery [" + (id != null ? "id=" + id + ", " : "") + (tagId != null ? "tagId=" + tagId + ", " : "")
				+ (parentId != null ? "parentId=" + parentId : "") + "]";
	}

}
