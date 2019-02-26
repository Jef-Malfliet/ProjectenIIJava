/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author IndyV
 */
public class FullScreenResolution {
    
    private static double width;
    private static double height;
    
    public static void setWidth(double width){
        FullScreenResolution.width=width;
    }
    
    public static void setHeight(double height){
        FullScreenResolution.height=height;
    }
    
    public static double getWidth(){
        return width;
    }
    
    public static double getHeight(){
        return height;
    }
}
