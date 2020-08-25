package com.sue.item.service;


import com.sue.pojo.PagedGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@FeignClient("sue-item-service")
@RequestMapping("item-comments-api")
public interface ItemCommentsService {
    /**
     * 我的评价查询分页
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/myComments")
    PagedGridResult queryMyComments(
            @RequestParam("userId") String userId,
            @RequestParam(value = "page",required = false) Integer page,
            @RequestParam(value = "pageSize",required = false) Integer pageSize
    );

    @PostMapping("/saveComments")
    public void saveComments(Map<String,Object> map);
}
