USE `chunzhiyu`; //换成自己的书据库
--------系统管理员表，密码为加密后数据，加密前数据为123456
insert into `sys_user` (`uid`, `username`, `password`, `salt`, `state`, `name`) values('1','admin','7085ab7c91470f35517638d67dc284da','5d114997f3dab23a7220c6b5d7db7a8b','1','管理员');


------------系统角色表
insert into `sys_role` (`id`, `available`, `description`, `role`) values('1','1','超级管理员','superadmin');
insert into `sys_role` (`id`, `available`, `description`, `role`) values('2','1','管理员','admin');
insert into `sys_role` (`id`, `available`, `description`, `role`) values('3','1','test','test');
insert into `sys_role` (`id`, `available`, `description`, `role`) values('5','1','对会员有操作权限','role2');
insert into `sys_role` (`id`, `available`, `description`, `role`) values('7','1','role5','role5');
insert into `sys_role` (`id`, `available`, `description`, `role`) values('8','1','role3','role3');
insert into `sys_role` (`id`, `available`, `description`, `role`) values('9','1','user4','user4');
insert into `sys_role` (`id`, `available`, `description`, `role`) values('10','1','user7','user7');
insert into `sys_role` (`id`, `available`, `description`, `role`) values('11','1','user6','user6');


----------系统权限表
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('1','系统管理','/system','0','1','0','/system','1');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('2','用户管理','/sysUser','1','1','1','/sysUser','2');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('3','角色管理','/role','1','1','1','/role','3');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('4','权限管理','/permission','1','1','1','/permission','4');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('5','会员管理','/customer','1','1','1','/customer','5');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('6','商品管理','/goods','1','1','1','/goods','6');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('7','商品类别管理','/category','1','1','1','/category','7');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('8','评价管理','/comments','1','1','1','/comments','8');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('9','公告管理','/notice','1','1','1','/notice','9');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('10','商品库存管理','/Input','1','1','1','/Input','10');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('11','供应商管理','/supplier','1','1','1','/supplier','11');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('12','统计模块','/statics','1','1','1','/statics','12');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('13','用户查询','/sysUser/users','2','1','2','/sysUser/add','13');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('14','用户添加','/sysUser/add','2','1','2','/sysUser/edit','14');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('15','用户修改','/sysUser/edit','2','1','2','/sysUser/delete','15');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('16','用户删除','/sysUser/delete','2','1','2','/sysUser/users','16');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('17','用户个人信息维护','/sysUser/loadUser','2','1','2','/sysUser/loadUser','17');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('18','角色查询','/role/roles','3','1','2','/role/roles','18');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('19','角色添加','/role/edit','3','1','2','/role/edit','19');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('20','角色修改','/role/edit','3','1','2','/role/edit','20');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('21','分配权限','/rolesavePermission','3','1','2','/rolesavePermission','22');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('22','角色删除','/role/delete','3','1','2','/role/delete','21');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('23','权限添加','/permission/add','4','0','2','/permission/add','23');
insert into `sys_permission` (`id`, `name`, `permission`, `parent_id`, `available`, `resource_type`, `url`, `sort`) values('24','权限修改','/permission/edit','4','1','2','/permission/edit','24');



------------用户-角色关系表
insert into `sys_user_role` (`uid`, `role_id`) values('1','1');



--------------角色-权限表
insert into `sys_role_permission` (`permission_id`, `role_id`) values('1','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('2','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('3','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('4','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('5','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('6','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('7','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('8','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('9','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('10','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('11','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('12','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('13','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('14','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('15','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('16','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('17','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('18','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('19','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('20','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('21','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('22','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('23','1');
insert into `sys_role_permission` (`permission_id`, `role_id`) values('24','1');



//商品分类
INSERT  INTO `category`(`category_id`,`catagory_desc`,`category_code`,`category_name`,`parsent_id`,`checked`) VALUES
(1,'花卉类别','C00000000','花卉类别',0,NULL),
(2,'价格','C00000001','价格',1,NULL),
(3,'价格-[0-50]','C00000001-1','0-50',2,NULL),
(4,'价格-[50-100]','C00000001-2','50-100',2,NULL),
(5,'价格-[100-200]','C00000001-3','100-200',2,NULL),
(6,'价格-[200-300]','C00000001-4','200-300',2,NULL),
(7,'价格[300-400]','C00000001-5','300-400',2,NULL),
(8,'价格-[400-500]','C00000001-5','400-500',2,NULL),
(9,'价格-[500-600]','C00000001-7','500-600',2,NULL),
(10,'价格-[600-700]','C00000001-8','600-700',2,NULL),
(11,'价格-[700-800]','C00000001-8','700-800',2,NULL),
(12,'价格-[800以上]','C00000001-9','800以上',2,NULL),
(13,'花卉用途','C00000002','用途',1,NULL),
(14,'用途-恋情','C00000002-1','恋情',13,NULL),
(15,'用途-生日','C00000002-2','生日',13,NULL),
(16,'用途-道歉','C00000002-3','道歉 ',13,NULL),
(17,'用途-友情','C00000002-4','友情',13,NULL),
(18,'用途-婚庆','C00000002-5','婚庆',13,NULL),
(19,'用途-商务','C00000002-6','商务',13,NULL),
(20,'用途-探望','C00000002-7','探望',13,NULL),
(21,'用途-家居','C00000002-8','家居',13,NULL),
(22,'用途-祝福','C00000002-9','祝福',13,NULL),
(23,'用途-办公装饰','C00000002-9','办公装饰',13,NULL),
(24,'用途-展会用品','C00000002-10','展会用品',13,NULL),
(25,'花材','C00000003','花材',1,NULL),
(26,'花材-玫瑰','C00000003-1','玫瑰',25,NULL),
(27,'花材-百合','C00000003-2','百合',25,NULL),
(28,'花材-绿植','C00000003-3','绿植',25,NULL),
(29,'花材-康乃馨','C00000003-4','康乃馨',25,NULL),
(30,'花材-郁金香','C00000003-5','郁金香',25,NULL),
(31,'花材-水果篮','C00000003-8','水果篮',25,NULL),
(32,'花材-巧克力','C00000003-9','巧克力',25,NULL),
(33,'花材-扶郎','C00000003-9','扶郎',25,NULL),
(34,'花材-花篮','C00000003-11','花篮',25,NULL),
(35,'对象','C00000004','对象',1,NULL),
(36,'对象-恋人','C00000004-1','恋人',35,NULL),
(37,'对象-朋友','C00000004-2','朋友',35,NULL),
(38,'对象-父母','C00000004-3','父母',35,NULL),
(39,'对象-病人','C00000004-4','病人',35,NULL),
(40,'对象-老师','C00000004-5','老师',35,NULL),
(41,'对象-客户','C00000004-6','客户',35,NULL),
(42,'节日','C00000005','节日',1,NULL),
(43,'节日-圣诞节','C00000005-1','圣诞节',42,NULL),
(44,'节日-中秋节','C00000005-2','中秋节',42,NULL),
(45,'节日-教师节','C00000005-3','教师节',42,NULL),
(46,'节日-情人节','C00000005-4','情人节',42,NULL),
(47,'其他','C00000005','其他',1,NULL),
(48,'其他-浪漫爱情','C00000005-1','浪漫爱情',47,NULL),
(49,'其他-精品礼盒','C00000005-2','精品礼盒',47,NULL);


//测试数据-商品
insert  into `goods`(`goods_id`,`goods_brand`,`goods_color`,`goods_desc`,`goods_discount`,`goods_material`,`goods_mean`,`goods_name`,`goods_pic`,`goods_price`,`goods_purchase_price`,`goods_shijia`,`goods_pack`,`goods_remark`) values
(1,'鲜花','红色','名师设计 浪漫唯美',1,'33枝红玫瑰，石竹梅围绕','只想和你忘掉一切烦恼，尽情沉醉在最浪漫的气氛中','忘情巴黎----33枝红玫瑰','f_1525151193647.jpg',355.00,300.00,441.00,'黑色条纹纸，红色缎带花结','促销中'),
(2,'鲜花','粉色','粉佳人玫瑰13枝，苏醒玫瑰16枝，粉色扶朗5枝，灯台9枝，尤加利叶',1,'粉佳人玫瑰13枝，苏醒玫瑰16枝，粉色扶朗5枝，灯台9枝，尤加利叶10枝','原来你是我最想留住的幸运，与你相遇好幸运！','好幸运----粉佳人玫瑰13枝，苏醒玫瑰16枝，粉色扶朗5枝，灯台9枝，尤加利叶','f_1526633089820.jpg',530.00,300.00,784.00,'浅紫香芋人造纸4张，粉色opp雾面纸2张，白粉双色缎带','精品鲜花'),
(3,'鲜花','红色','11枝香槟玫瑰，白百合2枝',1,'11枝香槟玫瑰，白百合2枝，栀子叶0.5扎','喜欢像是一阵风，而爱你是细水长流','恋恋情深----11枝香槟玫瑰，白百合2枝','f_1526633214950.jpg',198.00,200.00,237.00,'深浅绿双面人造纸2张，米白色缎带1.5米',''),
(4,'鲜花','粉','香槟玫瑰66枝',1,'香槟玫瑰66枝','你有没有发现，只要我们在一起，抬起头就是好天气。','天天天晴----香槟玫瑰66枝','f_1526633334020.jpg',130.00,496.00,590.00,'青灰色双面牛皮纸4张，米白色缎带2米','下单，即免费赠送精美贺卡！'),
(5,'鲜花','白','真爱如初----雪山玫瑰11枝、深紫色勿忘我0.3扎',1,'高档礼盒装鲜花:雪山玫瑰11枝、深紫色勿忘我0.3扎','用一袭白裙装扮你那无瑕的身姿，向一抹白云倾诉对你的思念，用一捧鲜花证明我不变的心。','真爱如初----雪山玫瑰11枝、深紫色勿忘我0.3扎','',206.00,100.00,245.00,'素染纸-浅灰2张，雪梨纸0.5张，银灰色logo缎带1米，魔力铁山灰盒(小)','下单填写留言，即免费赠送精美贺卡');


//测试数据-库存
insert into `stock` (`id`, `descri`, `is_alarm`, `stock_num`, `goods_id`) values('1',NULL,'0','0','1');
insert into `stock` (`id`, `descri`, `is_alarm`, `stock_num`, `goods_id`) values('2',NULL,'0','0','2');
insert into `stock` (`id`, `descri`, `is_alarm`, `stock_num`, `goods_id`) values('3',NULL,'0','0','3');
insert into `stock` (`id`, `descri`, `is_alarm`, `stock_num`, `goods_id`) values('4',NULL,'0','0','4');
insert into `stock` (`id`, `descri`, `is_alarm`, `stock_num`, `goods_id`) values('5',NULL,'0','0','5');
