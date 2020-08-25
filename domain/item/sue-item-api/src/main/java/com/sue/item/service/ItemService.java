package com.sue.item.service;


import com.sue.item.pojo.Items;
import com.sue.item.pojo.ItemsImg;
import com.sue.item.pojo.ItemsParam;
import com.sue.item.pojo.ItemsSpec;
import com.sue.item.pojo.vo.CommentLevelCountsVO;
import com.sue.item.pojo.vo.ShopCartVO;
import com.sue.pojo.PagedGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author sue
 * @date 2020/8/1 17:36
 */
@FeignClient("sue-item-service")
@RequestMapping("item-api")
public interface ItemService {

    /**
     * 根据商品Id查询详情
     *
     * @param itemId
     * @return
     */
    @GetMapping("/item")
    public Items queryItemById(@RequestParam("itemId") String itemId);

    /**
     * 根据商品Id查询商品图片列表
     *
     * @param itemId
     * @return
     */
    @GetMapping("/itemImages")
    public List<ItemsImg> queryItemImgList(@RequestParam("itemId")String itemId);


    /**
     * 根据商品Id查询商品规格
     *
     * @param itemId
     * @return
     */
    @GetMapping("/itemSpecs")
    public List<ItemsSpec> queryItemSpecList(@RequestParam("itemId")String itemId);


    /**
     * 根据商品id查询商品参数
     *
     * @param itemId
     * @return
     */
    @GetMapping("/itemParam")
    public ItemsParam queryItemParam(@RequestParam("itemId")String itemId);


    /**
     * 根据商品Id查询商品评价等级数量
     *
     * @param itemId
     */
    @GetMapping("/countComments")
    public CommentLevelCountsVO queryCommentCounts(@RequestParam("itemId")String itemId);


    /**
     * 根据商品id查询商品评价内容
     *
     * @param itemId
     * @param level
     * @return
     */
    @GetMapping("/pagedComment")
    public PagedGridResult queryPageComments(@RequestParam("itemId")String itemId,
                                             @RequestParam(value = "level",required = false)Integer level,
                                             @RequestParam(value = "page",required = false)Integer page,
                                             @RequestParam(value = "pageSize",required = false)Integer pageSize);


    /**
     * 搜索商品列表
     *
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
//    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);


    /**
     * 根据分类id搜索列表
     *
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
//    public PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);


    /**
     * 根据规格ids查询最新的购物车中商品数据 （用于刷新渲染购物车商品数据）
     *
     * @param specIds
     * @return
     */
    @GetMapping("/getCartBySpecIds")
    public List<ShopCartVO> queryItemsBySpecIds(@RequestParam("specIds")String specIds);


    /**
     * 根据商品规格Id获取规格对象的具体信息
     *
     * @param specId
     * @return
     */
    @GetMapping("/itemSpec")
    public ItemsSpec queryItemSpecById(@RequestParam("specId")String specId);


    /**
     * 根据商品id获取商品图片
     *
     * @param itemId
     * @return
     */
    @GetMapping("/primaryImage")
    public String queryItemMainImgById(@RequestParam("itemId")String itemId);


    /**
     * 扣减商品库存
     *
     * @param specId
     * @param buyCounts
     */
    @PostMapping("/decreaseStock")
    public void decreaseItemSpecStock(@RequestParam("specId")String specId,
                                      @RequestParam("buyCounts")int buyCounts);

}
