package com.meettingfilm.backend_common;
import java.util.Date;
import java.util.List;

import com.meettingfilm.backend_common.dao.entity.PtAccountHasPermission;
import com.meettingfilm.backend_common.dao.entity.PtCommonParam;
import com.meettingfilm.backend_common.dao.entity.PtPermission;
import com.meettingfilm.backend_common.dao.mapper.PtAccountHasPermissionMapper;
import com.meettingfilm.backend_common.dao.mapper.PtCommonParamMapper;
import com.meettingfilm.backend_common.dao.mapper.PtPermissionMapper;

import org.junit.Test;
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
public  class BackendCommonApplicationTests {

	@Resource
	private PtCommonParamMapper ptCommonParamMapper;

	@Resource
	private PtAccountHasPermissionMapper ptAccountHasPermissionMapper;
	@Resource
	private PtPermissionMapper ptPermissionMapper;

	@Test
	//添加管理员权限:全部权限
	public void insertAdminPermissions() {
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
	public void insertCommonParsms() {
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

		type=27;
		desc="婚姻状况";
		parmsString="已婚,未婚";

		type=28;
		desc="人员类别";
		parmsString="退役士兵,复原军人,企业军转干部,机关事业军转干部,自主择业军转干部,军队离退休干部和退休士官,军队无军籍离退休退职职工,残疾军人";



		type=30;
		desc="安置方式";
		parmsString="自主择业,自主就业,安排工作,退休,供养";


		type=31;
		desc="所读专业";
		parmsString="建筑类,市政类,电气类,水利类,园林类,环保类,化工类";


		type=32;
		desc="军休类别";
		parmsString="离休,退休,退修改离休";


		type=33;
		desc="干部类别";
		parmsString="军官,技术军官,文职,技术文职,士官";

		type=34;
		desc="军休干部-安置方式";
		parmsString="就地,易地";

		type=35;
		desc="军休干部-伤残属别";
		parmsString="因战,因公,因病";

		type=36;
		desc="军休干部-退休前身份";
		parmsString="职员,技术人员,技术工人,普通工人";

		type=37;
		desc="军休干部-职务（职称）-职员";
		parmsString="职员,技术人员,技术工人,普通工人";

		type=38;
		desc="军休干部-职务（职称）-技术人员";
		parmsString="正高四级,副高七级,中级,助理,原级";


		type=39;
		desc="军休干部-职务（职称）-技术工人";
		parmsString="高级技师,技师,高级工,中级工,初级工";

		type=40;
		desc="军休干部-人员类型";
		parmsString="军工,军休干部";

		type=41;
		desc="军休干部-职工分类";
		parmsString="建国前老工人,退休,退职,离休";

		type=42;
		desc="政策权益-人员类别";
		parmsString="在岗,退休,下岗,失业,离休,其他,已故";

		/************************************************************************************************************
		 * ***********************************************************************************************************
		 * ***********************************************************************************************************
		           20201016,修改字典
		 * ***********************************************************************************************************
		 ************************************************************************************************************/
		type=22;
		desc="现单位性质（含离退休前单位）";
		parmsString="国家机关,事业单位,国有企业,集体企业,外资企业,私营企业,公益性岗位,自由职业,创业";

		type=21;
		desc="现就业状态";
		parmsString="在职,下岗,失业,无业,离休,退休,创业,务农";

		type=27;
		desc="婚姻状况";
		//parmsString="已婚,未婚,离异,丧偶";
		parmsString="离异,丧偶";

		type=43;
		desc="住房状况";
		parmsString="自建房,公有住房,经济适用房,廉租房,公租房,商品房,无房";

		type=44;
		desc="享受何种国家抚恤补助金";
		parmsString="残疾军人,在乡复员军人,带病回乡,参战退役,参试退役,60周岁以上农村退役士兵,无";

		type=45;
		desc="社会救助状况";
		parmsString="低保,五保,建档立卡贫困户,无";

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
	public void insertDic46(){
		int type=46;
		String desc="复员军人/残疾军人";
		String parmsString="复员军人,残疾军人";
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
	public void insertDic47(){
		int type=47;
		String desc="入伍时间节点(1954年10月31日前入伍)";
		String parmsString="1954年10月31日前入伍,1954年11月1日后入伍";
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
	public void insertDic48(){
		int type=48;
		String desc="待遇情况";
		String parmsString="在乡老复员军人定期补助,离休退休,无";
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
	public void insertDic49(){
		int type=49;
		String desc="身体残疾情况";
		String parmsString="残疾军人,社会残疾人";
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
	public void insertDic50(){
		int type=50;
		String desc="优抚-伤残性质";
		String parmsString="因战,因公,因病";
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
	public void insertDic51(){
		int type=51;
		String desc="优抚-伤残10个等级";
		String parmsString="一级,二级,三级,四级,五级,六级,七级,八级,九级,十级";
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
	public void insertDic52(){
		int type=52;
		String desc="优抚-伤残6个等级";
		String parmsString="一级,二级,三级,四级,五级,六级";
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
	public void insertDic53(){
		int type=53;
		String desc="伤残证件名称";
		String parmsString="中华人民共和国残疾军人证,中华人民共和国伤残民兵民工证,中华人民共和国因战因公伤残人员证";
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
	public void insertDic54(){
		int type=54;
		String desc="立功受奖情况";
		String parmsString="八一勋章,大军区以上荣誉称号,一等功,二等功,三等功,全军士官优秀人才奖,无";
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
	public void insertDic55(){
		int type=55;
		String desc="供养情况";
		String parmsString="分散供养,集中供养,无";
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
	public void insertDic56(){
		int type=56;
		String desc="供养单位";
		String parmsString="荣康医院,复退军人精神病院,复退军人慢性病疗养院,综合性优抚医院,光荣院,社会福利机构,无";
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
	public void insertDic57(){
		int type=57;
		String desc="安置类型";
		String parmsString="原籍安置,异地安置,回入伍地安置,其他";
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
	public void contextLoads() {
	}

}
