package org.jelly.frame.minio.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件存储记录实体
 *
 * @author zhangxb
 */
@Data
public class FileStore implements Serializable {

    /**
     * 记录标识
     */
    private Long storeId;
    /**
     * 文件检索名称
     */
    private String fileName;
    /**
     * 文件真实存储名称
     */
    private String fileRealName;
    /**
     * 文件分享链接
     */
    private String shareLink;
    /**
     * 文件分享时长  -1：永久 1：7天   2：14天
     */
    private String shareTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
