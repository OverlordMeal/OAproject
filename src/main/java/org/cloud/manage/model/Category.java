package org.cloud.manage.model;

import java.io.Serializable;

/**
 * @Description:类目 
 * @ClassName: Category
 * @author: lnj
 * @date: 2017年8月8日 下午3:57:06
 */
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9104812998507065837L;

	/**
	 * 主键 
	 */
	private long           id;
	
	/**
	 * 名称 
	 */
	private String         name;
	
	/**
	 * 层级
	 */
	private int            level;
	
	/**
	 * 父节点id
	 */
	private long           parentId;
	
	/**
	 * 是否是叶子节点
	 */
	private int            isLeaf;

	/**
	 * 获取主键
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置主键
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取层级
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * 设置层级
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * 获取父节点id
	 * @return
	 */
	public long getParentId() {
		return parentId;
	}

	/**
	 * 设置父节点id
	 * @param parentId
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取是否是子节点
	 * @return
	 */
	public int getIsLeaf() {
		return isLeaf;
	}

	/**
	 * 设置是否是子节点
	 * @param isLeaf
	 */
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
	
}
