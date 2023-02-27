package com.yt.project.model.dto.userinterfaceinfo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;


@Data
public class UserInterfaceInfoAddRequest implements Serializable {

    /**
     * 调用用户接口id
     */
    private Long userId;

    /**
     * 接口 id
     */
    private Long interfaceInfoId;

    /**
     * 总调用此时
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
