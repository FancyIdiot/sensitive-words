package com.odianyun.util.sensi;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;

public class SensitiveFilterTest extends TestCase{
	
	public void test() throws Exception{
		
		// 使用默认单例（加载默认词典）
		SensitiveFilter filter = SensitiveFilter.DEFAULT;
		// 向过滤器增加一个词
		filter.put("婚礼上唱春天在哪里");
		
		// 待过滤的句子
		String sentence = "操你妈，绍兴已有2500多年建城史 [4]  ，是首批国家历史文化名城、联合国人居奖城市，中国优秀旅游城市，国家森林城市，中国民营经济最具活力城市，也是著名的水乡、桥乡、酒乡、书法之乡、名士之乡。绍兴素称“文物之邦、鱼米之乡”。著名的文化古迹有兰亭、禹陵、鲁迅故里、沈园、柯岩、蔡元培故居、周恩来祖居、秋瑾故居、马寅初故居、王羲之故居、贺知章故居等。";
		// 进行过滤
		String filted = filter.filter(sentence, '*');
		
		// 如果未过滤，则返回输入的String引用
		if(sentence != filted){
			// 句子中有敏感词
			System.out.println(filted);
		}

		List<String> words = filter.extract(sentence);

		System.out.println(JSON.toJSONString(words));


	}

	/**
	public void testLogic(){
		
		SensitiveFilter filter = new SensitiveFilter();
		
		filter.put("你好");
		filter.put("你好1");
		filter.put("你好2");
		filter.put("你好3");
		filter.put("你好4");
		
		System.out.println(filter.filter("你好3天不见", '*'));
		
	}
	
	public void testSpeed() throws Exception{
		
		PrintStream ps = new PrintStream("/data/敏感词替换结果.txt");
		
		File dir = new File("/data/穿越小说2011-10-14");
		
		List<String> testSuit = new ArrayList<String>(1048576);
		long length = 0;
		
		for(File file: dir.listFiles()){
			if(file.isFile() && file.getName().endsWith(".txt")){
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb18030"));
				for(String line = br.readLine(); line != null; line = br.readLine()){
					if(line.trim().length() > 0){
						testSuit.add(line);
						length += line.length();
					}
				}
				br.close();
			}
		}
		
		System.out.println(String.format("待过滤文本共 %d 行，%d 字符。", testSuit.size(), length));
		
		
		SensitiveFilter filter = SensitiveFilter.DEFAULT;
		
		int replaced = 0;
		
		for(String sentence: testSuit){
			String result = filter.filter(sentence, '*');
			if(result != sentence){
				ps.println(sentence);
				ps.println(result);
				ps.println();
				replaced ++;
			}
		}
		ps.close();
		
		long timer = System.currentTimeMillis();
		for(String line: testSuit){
			filter.filter(line, '*');
		}
		timer = System.currentTimeMillis() - timer;
		System.out.println(String.format("过滤耗时 %1.3f 秒， 速度为 %1.1f字符/毫秒", timer * 1E-3, length / (double) timer));
		System.out.println(String.format("其中 %d 行有替换", replaced));
		
	}
	 **/

}
