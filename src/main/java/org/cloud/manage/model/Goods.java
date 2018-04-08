package org.cloud.manage.model;

import java.util.List;
import java.util.Map;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.utils.JsonUtil;

/**
 * 商品bean类
 * @author chen
 *	//test
 */
public class Goods {
	//商品主键iD
	private Long id;
	
	//商品名称
	private String goodsName;
	
	//商品种类 
	private String goodsType;
	
	public String getGoodsTypeEdi(){
		if (BaseUtil.isEmpty(this.goodsType)) {
			return "无";
		}
		String str = "";
		List<Map<String, Object>> list = JsonUtil.jsonToList(this.goodsType);
		for (Map<String, Object> map : list) {
			str = "0".equals(String.valueOf(map.get("edibility"))) ? "可食用" : "不可食用";
		}
		return str;
	}
	
	
	public String getGoodsTypeIpt(){
		if (BaseUtil.isEmpty(this.goodsType)) {
			return "无";
		}
		String str = "";
		List<Map<String, Object>> list = JsonUtil.jsonToList(this.goodsType);
		for (Map<String, Object> map : list) {
			str = "0".equals(String.valueOf(map.get("import"))) ? "内地" : "进口";
		}
		return str;
	}
	
	public String getGoodsTypeExp(){
		if (BaseUtil.isEmpty(this.goodsType)) {
			return "无";
		}
		String str = "";
		List<Map<String, Object>> list = JsonUtil.jsonToList(this.goodsType);
		for (Map<String, Object> map : list) {
			str = String.valueOf(map.get("EXP"))+"天";
		}
		return str;
	}
	
	
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
		return "Goods [" + (id != null ? "id=" + id + ", " : "")
				+ (goodsName != null ? "goodsName=" + goodsName + ", " : "")
				+ (goodsType != null ? "goodsType=" + goodsType : "") + "]";
	}
	
	
	
	
	
}
