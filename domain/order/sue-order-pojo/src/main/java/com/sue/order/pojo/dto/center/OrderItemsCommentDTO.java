package com.sue.order.pojo.dto.center;

import lombok.Data;

@Data
public class OrderItemsCommentDTO {
	private String commentId;
	private String itemId;
	private String itemName;
	private String itemSpecId;
	private String itemSpecName;
	private Integer commentLevel;
	private String content;
}
