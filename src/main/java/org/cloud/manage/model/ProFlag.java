package org.cloud.manage.model;

public class ProFlag {
	
	
	//根id
	public static Long ROOT_ID = -1L;
	//主键ID
	private Long id;
	
	//物理服务器id
	private Long tagId;
	
	//类型
	private Long parentId;
	
	
	private boolean hasChild = false;
	
	
	private String name;
	
	public boolean isHasChild() {
		return hasChild;
	}

	/**
	 * 设置是否有子菜单
	 * @param hasChild
	 * 			是否有子菜单
	 */
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

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
		return "ProFlag [" + (id != null ? "id=" + id + ", " : "") + (tagId != null ? "tagId=" + tagId + ", " : "")
				+ (parentId != null ? "parentId=" + parentId : "") + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
