package com.sue.item.mapper;


import com.sue.item.pojo.ItemsComments;
import com.sue.item.pojo.vo.MyCommentVO;
import com.sue.my.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapper extends MyMapper<ItemsComments> {

    public void saveComments(Map<String,Object> map);

    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String,Object> map);
}