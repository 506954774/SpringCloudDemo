package com.meettingfilm.backend_common;
import java.util.Date;

import com.meettingfilm.backend_common.dao.entity.PtCommonParam;
import com.meettingfilm.backend_common.dao.mapper.PtCommonParamMapper;

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
		//parmsString="汉族,壮bai族,蒙古族,回族,藏族,维吾尔族du,苗族,彝族zhi,布依族,朝鲜族,满族,侗族,瑶族,白族,东乡族,锡伯族,土家族,哈尼族,哈萨克族,傣族,黎族,僳僳族,佤族,畲族,拉祜族,水族,纳西族,景颇族,柯尔克孜族,土族,高山族,达斡尔族,仫佬族,羌族,撒拉族,德昂族,仡佬族,阿昌族,普米族,布朗族,塔吉克族,怒族,乌孜别克族,俄罗斯族,鄂温克族,毛南族,保安族,裕固族,京族,塔塔尔族,独龙族,鄂伦春族,赫哲族,门巴族,珞巴族,基诺族";

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
