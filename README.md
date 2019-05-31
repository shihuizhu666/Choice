# Choice API SDK
	选品库接口 SDK

## 接口配置
	参考 test.php 文件下设置申请到的 AppID 和 AppKey

	[http://www.shihuizhu.net/port](http://www.shihuizhu.net/port)

## 调用方法

### 商品分类
	Choice::cates()

### 商品详情
	Choice::detail( $gid, $detail )

	@param gid 淘宝商品id (必填)
	@param detail 是否显示商品文案（‘1’代表包含商品文案）(可选)


### 商品列表
	Choice::goods( $cate_id, $page )

	Choice::goods( $cate_id, $page, array('activity'=>$activity,'kw'=>$kw,'sorts'=>$sorts) )

	@param cate_id 分类id (必填)
	@param page 分页id (必填)
	@param activity 活动类型 (取值：'聚划算'/'淘抢购'，非必填)
	@param kw  查询关键字 (非必填)
	@param sorts  排序 (取值：'sales'/'ratio'/'price'/'stoptime' 分别对应：销量/佣金比例/价格/优惠券到期时间，非必填)
 

### 视频专区
	Choice::video( $cate_id, $page )

	@param cate_id 分类id (必填)
	@param page 分页id (必填)

### 品牌精选
	Choice::brand( $cate_id, $page )

	@param cate_id 分类id (必填)
	@param page 分页id (必填)
 

### 实时上新
	Choice::today( $cate_id, $page )

	@param cate_id 分类id (必填)
	@param page 分页id (必填)

### 今日疯抢榜
	Choice::rankings( $ranking )

	@param ranking 排序参数 (非必填)

