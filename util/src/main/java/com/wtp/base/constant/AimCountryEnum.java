package com.wtp.base.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum AimCountryEnum {
    COUNTRY_US(0, "美国", "US", "UNITED STATES"),
    COUNTRY_DE(1, "德国", "DE", "GERMANY"),
    COUNTRY_UK(2, "英国", "UK", "UNITED KINGDOM"),
    COUNTRY_JP(3, "日本", "JP", "JAPAN"),
    COUNTRY_CA(4, "加拿大", "CA", "CANADA"),
    COUNTRY_FR(5, "法国", "FR", "FRANCE"),
    COUNTRY_IT(6, "意大利", "IT", "ITALY"),
    COUNTRY_ES(7, "西班牙", "ES", "SPAIN"),
    COUNTRY_SG(8, "新加坡", "SG", "SINGAPORE"),
    COUNTRY_AU(9, "澳大利亚", "AU", "AUSTRALIA"),
    COUNTRY_NL(10, "荷兰", "NL", "NETHERLANDS"),
    COUNTRY_SE(11, "瑞典", "SE", "SWEDEN"),
    COUNTRY_PL(12, "波兰", "PL", "POLAND"),
    COUNTRY_CZ(13, "捷克", "CZ", "CZECH REPUBIC"),
    COUNTRY_MX(14, "墨西哥", "MX", "MEXICO"),
    COUNTRY_TR(15, "土耳其", "TR", "TURKEY"),
    COUNTRY_OTHER(16, "其它", "OTHER", "OTHER"),
    COUNTRY_CHN(17, "中国", "CHN", "CHINA"),

    COUNTRY_AE(18,"阿联酋","AE","United Arab Emirates"),
    COUNTRY_AF(19,"阿富汗","AF","Afghanistan"),
    COUNTRY_AL(20,"阿尔巴尼亚","AL","Albania"),
    COUNTRY_AO(21,"安哥拉","AO","Angola"),
    COUNTRY_AR(22,"阿根廷","AR","Argentina"),
    COUNTRY_AT(23,"奥地利","AT","Austria"),
    COUNTRY_AZ(24,"阿塞拜疆","AZ","Azerbaijan"),
    COUNTRY_BD(25,"孟加拉","BD","Bangladesh"),
    COUNTRY_BE(26,"比利时","BE","Belgium"),
    COUNTRY_BG(27,"保加利亚","BG","Bulgaria"),
    COUNTRY_BH(28,"巴林","BH","Bahrain"),
    COUNTRY_BI(29,"布隆迪","BI","Burundi"),
    COUNTRY_BJ(30,"贝宁","BJ","Benin"),
    COUNTRY_BM(31,"百慕大","BM","Bermuda"),
    COUNTRY_BN(32,"文莱","BN","Brunei"),
    COUNTRY_BO(33,"玻利维亚","BO","Bolivia"),
    COUNTRY_BR(34,"巴西","BR","Brazil"),
    COUNTRY_BS(35,"巴哈马","BS","Bahamas"),
    COUNTRY_BT(36,"不丹","BT","Bhutan"),
    COUNTRY_BW(37,"博茨瓦纳","BW","Botswana"),
    COUNTRY_CF(38,"中非共和国","CF","Central Africa"),
    COUNTRY_CG(39,"刚果","CG","Congo"),
    COUNTRY_CH(40,"瑞士","CH","Switzerland"),
    COUNTRY_CK(41,"库克群岛","CK","Cook Is"),
    COUNTRY_CL(42,"智利","CL","Chile"),
    COUNTRY_CM(43,"喀麦隆","CM","Cameroon"),
    COUNTRY_CO(44,"哥伦比亚","CO","Colombia"),
    COUNTRY_CR(45,"哥斯达黎加Costa","CR","Rica"),
    COUNTRY_CU(46,"古巴","CU","Cuba"),
    COUNTRY_CV(47,"佛得角群岛","CV","Cape Verde Is."),
    COUNTRY_CY(48,"塞浦路斯","CY","Cyprus"),
    COUNTRY_DK(49,"丹麦","DK","Denmark"),
    COUNTRY_DZ(50,"阿尔及利亚","DZ","Algeria"),
    COUNTRY_EC(51,"厄瓜多尔","EC","Ecuador"),
    COUNTRY_EE(52,"爱沙尼亚","EE","Estonia"),
    COUNTRY_EG(53,"埃及","EG","Egypt"),
    COUNTRY_ET(54,"埃塞俄比亚","ET","Ethiopia"),
    COUNTRY_FI(55,"芬兰","FI","Finland"),
    COUNTRY_FJ(56,"斐济","FJ","Fiji"),
    COUNTRY_GA(57,"加蓬","GA","Gabon"),
    COUNTRY_GD(58,"格林纳达","GD","Grenada"),
    COUNTRY_GH(59,"加纳","GH","Ghana"),
    COUNTRY_GM(60,"冈比亚","GM","Gambia"),
    COUNTRY_GN(61,"几内亚","GN","Guinea"),
    COUNTRY_GQ(62,"赤道几内亚","GQ","Equatorial Guinea"),
    COUNTRY_GR(63,"希腊","GR","Greece"),
    COUNTRY_GT(64,"危地马拉","GT","Guatemala"),
    COUNTRY_GU(65,"关岛","GU","Guam"),
    COUNTRY_GY(66,"圭亚那","GY","Guyana"),
    COUNTRY_HK(67,"中国香港","HK","Hong kong"),
    COUNTRY_HN(68,"洪都拉斯","HN","Honduras"),
    COUNTRY_HR(69,"克罗地亚","HR","Croatia"),
    COUNTRY_HT(70,"海地","HT","Haiti"),
    COUNTRY_HU(71,"匈牙利","HU","Hungary"),
    COUNTRY_ID(72,"印度尼西亚","ID","Indonesia"),
    COUNTRY_IE(73,"爱尔兰","IE","Ireland"),
    COUNTRY_IL(74,"以色列","IL","Israel"),
    COUNTRY_IN(75,"印度","IN","India"),
    COUNTRY_IQ(76,"伊拉克","IQ","Iraq"),
    COUNTRY_IR(77,"伊朗","IR","Iran"),
    COUNTRY_IS(78,"冰岛","IS","Iceland"),
    COUNTRY_JM(79,"牙买加","JM","Jamaica"),
    COUNTRY_JO(80,"约旦","JO","Jordan"),
    COUNTRY_KE(81,"肯尼亚","KE","Kenya"),
    COUNTRY_KH(82,"柬埔寨","KH","Cambodia"),
    COUNTRY_KP(83,"韩国","KP","R.O.Korea"),
    COUNTRY_KR(84,"北朝鲜","KR","D.P.R.Korea"),
    COUNTRY_KW(85,"科威特","KW","Kuwait"),
    COUNTRY_KZ(86,"哈萨克斯坦","KZ","Kazakhstan"),
    COUNTRY_LA(87,"老挝","LA","Laos"),
    COUNTRY_LB(88,"黎巴嫩","LB","Lebanon"),
    COUNTRY_LT(89,"立陶宛","LT","Lithuania"),
    COUNTRY_LU(90,"卢森堡","LU","Luxembourg"),
    COUNTRY_LV(91,"拉托维亚","LV","Latvia"),
    COUNTRY_LY(92,"利比亚","LY","Libya"),
    COUNTRY_MA(93,"摩洛哥","MA","Morocco"),
    COUNTRY_MC(94,"摩纳哥","MC","Monaco"),
    COUNTRY_MD(95,"摩尔多瓦","MD","Moldova"),
    COUNTRY_MG(96,"马达加斯加","MG","Madagascar"),
    COUNTRY_ML(97,"马里","ML","Mali"),
    COUNTRY_MN(98,"蒙古","MN","Mongolia"),
    COUNTRY_MO(99,"中国澳门","MO","Macao"),

    UNKNOWN(100, "Unknown", "Unknown", "Unknown"),
    COUNTRY_MR(101,"毛里塔尼亚","MR","Mauritania"),
    COUNTRY_MT(102,"马耳他","MT","Malta"),
    COUNTRY_MU(103,"毛里求斯","MU","Mauritius"),
    COUNTRY_MV(104,"马尔代夫","MV","Maldives"),
    COUNTRY_MY(105,"马来西亚","MY","Malaysia"),
    COUNTRY_MZ(106,"莫桑比克","MZ","Mozambique"),
    COUNTRY_NA(107,"纳米比亚","NA","Namibia"),
    COUNTRY_NE(108,"尼日尔","NE","Niger"),
    COUNTRY_NG(109,"尼日利亚","NG","Nigeria"),
    COUNTRY_NI(110,"尼加拉瓜","NI","Nicaragua"),
    COUNTRY_NO(111,"挪威","NO","Norway"),
    COUNTRY_NP(112,"尼泊尔","NP","Nepal"),
    COUNTRY_NZ(113,"新西兰","NZ","New Zealand"),
    COUNTRY_OM(114,"阿曼","OM","Oman"),
    COUNTRY_PA(115,"巴拿马","PA","Panama"),
    COUNTRY_PE(116,"秘鲁","PE","Peru"),
    COUNTRY_PG(117,"巴布亚新几内亚","PG","Papua New Guinea"),
    COUNTRY_PH(118,"菲律宾","PH","Philippines"),
    COUNTRY_PK(119,"巴基斯坦","PK","Pakistan"),
    COUNTRY_PT(120,"葡萄牙","PT","Portugal"),
    COUNTRY_PY(121,"巴拉圭","PY","Paraguay"),
    COUNTRY_QA(122,"卡塔尔","QA","Qatar"),
    COUNTRY_RO(123,"罗马尼亚","RO","Romania"),
    COUNTRY_RU(124,"俄罗斯","RU","Russia"),
    COUNTRY_RW(125,"卢旺达","RW","Rwanda"),
    COUNTRY_SA(126,"沙特阿拉伯","SA","Saudi Arabia"),
    COUNTRY_SD(127,"苏丹","SD","Sudan"),
    COUNTRY_SK(128,"斯洛伐克","SK","Slovakia"),
    COUNTRY_SM(129,"圣马力诺","SM","San Marino"),
    COUNTRY_SN(130,"塞内加尔","SN","Senegal"),
    COUNTRY_SO(131,"索马里","SO","Somalia"),
    COUNTRY_SY(132,"叙利亚","SY","Syria"),
    COUNTRY_TH(133,"泰国","TH","Thailand"),
    COUNTRY_TJ(134,"塔吉克斯坦","TJ","Tadzhikistan"),
    COUNTRY_TM(135,"土库曼斯坦","TM","Turkmenistan"),
    COUNTRY_TN(136,"突尼斯","TN","Tunisia"),
    COUNTRY_TO(137,"汤加","TO","Tong"),
    COUNTRY_TW(138,"中国台湾","TW","Taiwan"),
    COUNTRY_TZ(139,"坦桑尼亚","TZ","Tanzania"),
    COUNTRY_UA(140,"乌克兰","UA","Ukraine"),
    COUNTRY_UG(141,"乌干达","UG","Uganda"),
    COUNTRY_UY(142,"乌拉圭","UY","Uruguay"),
    COUNTRY_UZ(143,"乌兹别克斯坦","UZ","Uzbekistan"),
    COUNTRY_VA(144,"梵蒂冈","VA","Vatican City"),
    COUNTRY_VE(145,"委内瑞拉","VE","Venezuela"),
    COUNTRY_VN(146,"越南","VN","Viet Nam"),
    COUNTRY_YE(147,"也门","YE","Yemen"),
    COUNTRY_YU(148,"南斯拉夫","YU","Yugoslavia"),
    ;

    private int id;
    private String cnName;
    private String marketIdentifier;
    private String fullName;

    public static Map<String,AimCountryEnum> markMapEnum(){
        Map<String, AimCountryEnum> collect = Arrays.stream(AimCountryEnum.values()).collect(Collectors.toMap(a -> a.getMarketIdentifier(), a -> a));
        return collect;
    }

    public static Map<String,AimCountryEnum> fullNameMapEnum(){
        Map<String, AimCountryEnum> collect = Arrays.stream(AimCountryEnum.values()).collect(Collectors.toMap(a -> a.getFullName(), a -> a));
        return collect;
    }
}