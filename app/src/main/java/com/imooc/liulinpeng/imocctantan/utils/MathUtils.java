package com.imooc.liulinpeng.imocctantan.utils;

/**
 * Created by george on 2017/7/31.
 */

public class MathUtils {
    public static double getRoationOffset(float angle, int width, int height) {
        //算出小三角形的角度
        float halfWidth = width / 2f;
        float halfHeight = height / 2f;

        System.out.println("halfWidth" + halfWidth);
        System.out.println("halfHeight" + halfHeight);
        //小三角型的角度
        float i = halfWidth / halfHeight;

        System.out.println("tan" + i);
        double atan = Math.toDegrees(Math.atan(i));

        System.out.println("小三角形的角度" + atan);
        double sunAngle = atan + angle;
        double banjing = Math.sqrt(halfHeight * halfHeight + halfWidth * halfWidth);

        System.out.println("sunAngle" + sunAngle);
        double sin = Math.sin(Math.toRadians(sunAngle));

        System.out.println("sin" + sin);
        return sin * banjing;
    }
}
