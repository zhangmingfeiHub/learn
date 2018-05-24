/**
 * 
 * @author mingfei.z 2018年5月21日 下午5:05:13
 */
package com.mfzhang.mayi.springmvc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mingfei.z
 */
public class ListBatch {

	public static void main(String[] args) {
		int size = 1221;
		
		List<String> list = createData(size);
		processData2(list);
	}

	private BigDecimal cal() {
		
		int computerCount = 1; // 电脑数量
		int useYears = 4; // 使用年数
		BigDecimal rentMoney = new BigDecimal(80); // 电脑每月租金
		
		BigDecimal computerPrice = new BigDecimal(3450); // 电脑在租赁初期售价
		
		
		return null;
	}
	
	private static void printData(List<List<String>> list) {
		for (List<String> partList : list) {
			System.out.println("======打印：" + partList);
		}
	}
	
	private static void processData2(List<String> list) {
		System.out.println("开始处理---------");
		
		int maxVal = 100;
		
		List<List<String>> dataList = new ArrayList<>();
		
		if (list.size() > maxVal) {
			int part = 0;
			
			if (list.size() % maxVal > 0) {
				part = list.size() / maxVal + 1;

				for (int i=0; i<part; i++) {
					System.out.println("共" + list.size() + "条记录，分" + part + "批次完成，现操作第" + (i+1) + "次");
					
					if ((i+1) == part) { // 最后一批次

						List<String> partList = new ArrayList<>();
						partList.addAll(list.subList(i * maxVal, list.size()));
						dataList.add(partList);
					} else {
						List<String> partList = new ArrayList<>();
						partList.addAll(list.subList(i * maxVal, (i + 1) * maxVal));
						dataList.add(partList);
					}
				}
				
			} else {
				part = list.size() / maxVal;

				for (int i=0; i<part; i++) {
					System.out.println("共" + list.size() + "条记录，分" + part + "批次完成，现操作第" + (i+1) + "次");
					
					List<String> partList = new ArrayList<>();
					partList.addAll(list.subList(i * maxVal, (i + 1) * maxVal));
					dataList.add(partList);
				}
				
			}

		} else {
			dataList.add(list);
		}
		
		printData(dataList);
		
		System.out.println("结束处理---------");
	}
	
	private static List<String> createData(int size) {
		List<String> strList = new ArrayList<>();
		for (int i=0; i<size; i++) {
			strList.add("String-" + (i+1));
		}
		
		return strList;
	}
	
}
