package com.yi.weal.utils;

import com.yi.weal.model.Weal;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合去重
 * @author YI
 * @date 2018-12-8 15:17:46
 */
public class SetHeavyUtil {
    /**
     * 视频去重
     * @param weals
     * @return
     */
    public static List<Weal> IterableHeavy(Iterable<Weal> weals){
        List<Weal> lists = new ArrayList<>();

        // 去重
        weals.forEach(weal -> {
            if (lists.stream().noneMatch(e -> e.getTitle().equals(weal.getTitle())))
                lists.add(weal);
        });

        return lists;
    }
}
