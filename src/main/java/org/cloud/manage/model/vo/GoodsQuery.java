package org.cloud.manage.model.vo;

/**
 * 商品类
 * @author chen
 *	//test
 */
public class GoodsQuery {
	//商品主键iD
	private Long id;
	
	//商品名称
	private String goodsName;
	
	//商品种类 
	private String goodsType;

	
	public Long getId() {
		return id;
	}

	public String getGoodsName() {
		return goodsName;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Override
	public String toString() {
		return "GoodsQuery [" + (id != null ? "id=" + id + ", " : "")
				+ (goodsName != null ? "goodsName=" + goodsName + ", " : "")
				+ (goodsType != null ? "goodsType=" + goodsType : "") + "]";
	}

	
	
}
