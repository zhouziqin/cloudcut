package com.mainiway.cloudcut.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
public class GenOrderNumUtil {
    /**
     * 锁对象，可以为任意对象
     */
    private static Object lockObj = "lockerOrder";
    /**
     * 订单号生成计数器
     */
    private static long orderNumCount = 0L;
    /**
     * 每毫秒生成订单号数量最大值
     */
    private int maxPerMSECSize=1000;
    /**
     * 生成非重复订单号，理论上限1毫秒1000个，可扩展
     * @param tname 测试用
     */
    public String gen(String mechineID,String userID,String orderID,int type) {
    	// 最终生成的订单号
        String finOrderNum = "";
        try {
            synchronized (lockObj) {
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                // 计数器到最大值归零，可扩展更大
                if (orderNumCount > maxPerMSECSize) {
                    orderNumCount = 0L;
                }
                //组装订单号
                String countStr=maxPerMSECSize +orderNumCount+"";
                if(type==1){
                	finOrderNum = mechineID+"-"+userID+"-"+orderID+"-"+nowLong+countStr;
                }else{
                	finOrderNum = "re-"+mechineID+"-"+userID+"-"+orderID+"-"+nowLong+countStr;
                }
                orderNumCount++;
               // System.out.println(finOrderNum + "--" + Thread.currentThread().getName());
                // Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return finOrderNum;
    }

//    public static void main(String[] args) {
//        // 测试多线程调用订单号生成工具
//        try {
//            for (int i = 0; i < 200; i++) {
//                Thread t1 = new Thread(new Runnable() {
//                    public void run() {
//                    	GenOrderNumUtil genOrderNum = new GenOrderNumUtil();
//                    	genOrderNum.gen("1","1");
//                    }
//                }, "at" + i);
//                t1.start();
//
//                Thread t2 = new Thread(new Runnable() {
//                    public void run() {
//                    	GenOrderNumUtil genOrderNum = new GenOrderNumUtil();
//                    	genOrderNum.gen("1","1");
//                    }
//                }, "bt" + i);
//                t2.start();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}












