package com.sue.order.pojo.vo;



import lombok.Data;
import com.sue.pojo.ShopcartDTO;

import java.util.List;

@Data
public class OrderVO {
	private String orderId;
	private MerchantOrdersVO merchantOrdersVO;
	private List<ShopcartDTO> toBeRemovedShopcatdList;
}
