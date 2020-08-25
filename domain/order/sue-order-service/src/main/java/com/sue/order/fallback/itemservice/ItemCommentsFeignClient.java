package com.sue.order.fallback.itemservice;

import com.sue.item.service.ItemCommentsService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author sue
 * @date 2020/8/25 14:41
 */

//@FeignClient(value = "sue-item-service",fallback = ItemCommentsFallback.class)
@FeignClient(value = "sue-item-service",fallbackFactory = itemCommentFallbackFactory.class)
public interface ItemCommentsFeignClient extends ItemCommentsService {

}
