package com.zzh.shortvideohub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zzh
 * @version 1.0
 * @date 2020/4/20 10:19
 */
@Data
public class PageBase {
    private int pageSize = 10;
    private int currentPage = 1;
}
