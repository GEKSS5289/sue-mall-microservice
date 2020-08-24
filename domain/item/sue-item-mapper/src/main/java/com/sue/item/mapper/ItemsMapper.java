package com.sue.item.mapper;



import com.sue.item.pojo.Items;
import com.sue.item.pojo.vo.ItemCommentVO;
import com.sue.item.pojo.vo.ShopCartVO;
import com.sue.my.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapper extends MyMapper<Items> {
    public List<ItemCommentVO> queryItemComments(@Param("paramsMap")Map<String,Object> map);
//    public List<SearchItemsVO> searchItems(@Param("paramsMap")Map<String,Object> map);
//    public List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap")Map<String,Object> map);
    public List<ShopCartVO> queryItemsBySpecIds(@Param("paramsList")List specIdsList);
    public int decreaseItemSpecStock(@Param("specId")String specId,@Param("pendingCounts")int pendingCounts);
}