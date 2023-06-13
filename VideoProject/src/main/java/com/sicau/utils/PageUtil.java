package com.sicau.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sicau.entity.vo.PageVo;

/**
 * @author jay
 * @description Page对象转换工具类
 * @create 2023/6/12 16:26
 */
public class PageUtil {

    public static <T> PageVo<T> convert(Page<T> resultList) {
        PageVo<T> tPageVo = new PageVo<>();
        if (resultList != null) {
            tPageVo.setRecords(resultList.getRecords());
            tPageVo.setTotal((int) resultList.getTotal());
            tPageVo.setCurrentPage((int) resultList.getCurrent());
            tPageVo.setPageSize((int) resultList.getSize());
            tPageVo.setPages((int) resultList.getPages());
        }
        return tPageVo;
    }
}