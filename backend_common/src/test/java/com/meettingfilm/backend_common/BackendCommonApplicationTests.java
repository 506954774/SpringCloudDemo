package com.meettingfilm.backend_common;
import java.util.Date;
import java.util.List;

import com.meettingfilm.backend_common.dao.entity.PtAccountHasPermission;
import com.meettingfilm.backend_common.dao.entity.PtCommonParam;
import com.meettingfilm.backend_common.dao.entity.PtPermission;
import com.meettingfilm.backend_common.dao.mapper.PtAccountHasPermissionMapper;
import com.meettingfilm.backend_common.dao.mapper.PtCommonParamMapper;
import com.meettingfilm.backend_common.dao.mapper.PtPermissionMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes= BackendCommonApplication .class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class BackendCommonApplicationTests {

	@Resource
	private PtCommonParamMapper ptCommonParamMapper;

	@Resource
	private PtAccountHasPermissionMapper ptAccountHasPermissionMapper;
	@Resource
	private PtPermissionMapper ptPermissionMapper;

	@Test
	//添加管理员权限:全部权限
	void insertAdminPermissions() {
		List<PtPermission> ptCommonParams = ptPermissionMapper.selectList(null);
		if(ptCommonParams!=null&&ptCommonParams.size()>0){
			for (int i = 0; i < ptCommonParams.size(); i++) {
				PtAccountHasPermission entity=new PtAccountHasPermission();
				entity.setAccId("b5b476bdee704495902303d443d18996");
				entity.setPermId(ptCommonParams.get(i).getPermId());
				entity.setCTime(new Date());
				ptAccountHasPermissionMapper.insert(entity);
			}
		}
	}

	@Test
	void insertCommonParsms() {
		int type=7;
		String desc="职务(技术)等级";
		String parmsString="正师职,副师职,正团职,副团职,正营职,副营职,正连职,副连职,正排职,副排职,正局级,副局级,正处级,副处级,正科级,副科级,一级科员,二级科员,办事员,技术6级,技术7级,技术8级,技术9级,技术10级,技术11级,技术12级,技术13级,技术14级";

		type=8;
		desc="军衔文职级";
		parmsString="大校,上校,中校,少校,上尉,中尉,少尉,行政12级,行政13级,行政14级,行政15级,行政16级,行政17级,行政18级,行政19级,行政20级,行政21级,行政22级,行政23级,3级,4级,5级,6级,7级,8级,9级,其他";

		type=9;
		desc="伤残情况";
		parmsString="特等残废,一等残废,二等甲残废,二等乙残废,三等甲残废,三等乙残废";


		type=10;
		desc="奖励情况";
		parmsString="授予荣誉称号,一等功,二等功,三等功,其他";

		type=11;
		desc="处分情况";
		parmsString="警告,严重警告,记过,记大过,降一级,降二级,降职,撤职,开除军籍,撤销党内职务,留党察看,开除党籍";


		type=12;
		desc="六类人员";
		parmsString="在边防工作,在海岛工作,在高寒地区工作,在沙漠地区工作,飞行工作,舰艇工作";

		type=13;
		desc="民族";
		parmsString="汉族,壮族,蒙古族,回族,藏族,维吾尔族,苗族,彝族,布依族,朝鲜族,满族,侗族,瑶族,白族,东乡族,锡伯族,土家族,哈尼族,哈萨克族,傣族,黎族,僳僳族,佤族,畲族,拉祜族,水族,纳西族,景颇族,柯尔克孜族,土族,高山族,达斡尔族,仫佬族,羌族,撒拉族,德昂族,仡佬族,阿昌族,普米族,布朗族,塔吉克族,怒族,乌孜别克族,俄罗斯族,鄂温克族,毛南族,保安族,裕固族,京族,塔塔尔族,独龙族,鄂伦春族,赫哲族,门巴族,珞巴族,基诺族";

		type=14;
		desc="伤残等级";
		parmsString="十级,九级,八级,七级,六级,五级,四级,三级,二级,一级";

		type=15;
		desc="立功等级";
		parmsString="中央军委授予荣誉称号,国家级荣誉称号,大单位授予荣誉称号,省部级荣誉称号,一等功,二等功,三等功,先进工作者,嘉奖,军级单位授予的荣誉称号,军以上单位授予的战斗英雄,全国劳动英雄,全国劳动模范,全国先进工作者,省级劳动模范,省级先进工作者";

		type=16;
		desc="享受待遇级别";
		parmsString="军区领导,正大区职,副大区职,正军职,副军职,正师职,副师职,正团职,副团职,正营职,副营职,正连职,副连职,正排职,一级军士长,二级军士长,三级军士长,四级军士长,上士,中士,下士";

		type=17;
		desc="职员-职务(职称)";
		parmsString="正局,副局,正科,副科,科办员,办事员";

		type=2;
		desc="性别";
		parmsString="男,女";

		type=19;
		desc="政治面貌";
		parmsString="中共党员,共青团员,群众,其他";

		type=20;
		desc="户口类型";
		parmsString="农村户口,城镇户口";

		type=21;
		desc="现就业状态";
		parmsString="就业,创业,待业";

		type=22;
		desc="现单位性质（含离退休前单位）";
		parmsString="国有企业,国有单位";

		type=1;
		desc="是否";
		parmsString="是,否";

		type=24;
		desc="医疗保险";
		parmsString="无,公费医疗,职工基本,城乡居民";

		type=25;
		desc="养老保险";
		parmsString="无,离退休金,职工基本,城乡居民";

		type=26;
		desc="文化程度";
		parmsString="小学及以下,初中,高中,职高,中专,技校,专科,本科,硕士,博士,其他";

		String[] params=parmsString.split(",");
		for (int i = 0; i < params.length; i++) {
			PtCommonParam entity=new PtCommonParam();
			entity.setParamValue(params[i]);
			entity.setParamType(type);
			entity.setParamDesc(desc);
			entity.setParamOpen(1);
			entity.setParamRemove(0);
			entity.setCTime(new Date());
			ptCommonParamMapper.insert(entity);
		}
	}


	@Test
	void contextLoads() {
	}

}
