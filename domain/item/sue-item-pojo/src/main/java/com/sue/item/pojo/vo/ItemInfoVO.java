package com.sue.item.pojo.vo;


import com.sue.item.pojo.Items;
import com.sue.item.pojo.ItemsImg;
import com.sue.item.pojo.ItemsParam;
import com.sue.item.pojo.ItemsSpec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sue
 * @date 2020/8/1 17:56
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
