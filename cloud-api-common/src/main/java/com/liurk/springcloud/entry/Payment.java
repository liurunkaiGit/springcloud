package com.liurk.springcloud.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Payment {
    private Long id;
    private String serial;
}
