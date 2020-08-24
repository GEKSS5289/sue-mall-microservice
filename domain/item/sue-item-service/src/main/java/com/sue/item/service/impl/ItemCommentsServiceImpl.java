package com.sue.item.service.impl;


import com.github.pagehelper.PageHelper;
import com.sue.item.mapper.ItemsCommentsMapper;
import com.sue.item.pojo.vo.MyCommentVO;
import com.sue.item.service.ItemCommentsService;
import com.sue.pojo.PagedGridResult;
import com.sue.service.PagedGridResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemCommentsServiceImpl  implements ItemCommentsService {

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapperCustom;



    @Override
    public PagedGridResult queryMyComments(
    @RequestParam("userId") String userId,
    @RequestParam(value = "page",required = false) Integer page,
    @RequestParam(value = "pageSize",required = false) Integer pageSize
    ) {

            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            PageHelper.startPage(page,pageSize);
            List<MyCommentVO> myCommentVOS = itemsCommentsMapperCustom.queryMyComments(map);

            return PagedGridResultUtils.setterPagedGrid(myCommentVOS,page);

    }

    @Override
    public void saveComments(@RequestBody Map<String, Object> map) {
        itemsCommentsMapperCustom.saveComments(map);
    }
}
