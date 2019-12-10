package com.wd.common.bean;

/**
 * @describe(描述)：com.wd.common.bean
 * @data（日期）: 19:2019/12/5
 * @time（时间）: 19:05
 * @author（作者）: 盖磊
 **/
public class VideoVo {
    //id	int	健康视频id
    //categoryId	int	健康视频类目id
    //title	string	标题
    //shearUrl	string	剪切视频url(试看)
    //abstracts	string	摘要
    //originalUrl	string	原始视频url
    //price	int	价格（H币）
    //duration	int	时长（单位/秒）
    //whetherCollection	int	是否收藏
    //whetherBuy	int	是否购买 1= 是2 =否
    //buyNum	int	购买数

    public int id;
    public int categoryId;
    public String title;
    public String shearUrl;
    public String abstracts;
    public String originalUrl;
    public int price;
    public int duration;
    public int whetherCollection;
    public int whetherBuy;
    public int buyNum;
}
