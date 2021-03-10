package com.scma.interview;


// 这个类用于存取一组权限，每个权限用正整数表示的，这组权限存储在rightString属性中；
// 如果第N个权限存在，rightString第N个字符为“1”，否则，为空格。N是权限字符在字符串
// 中的位置。
public class RightStore{
    private String rightString = "";

    // 如果传入的权限存在，该方法返回true，否则，为false
    // position传入的权限的位置
    public boolean getRight(int position){
        if(position<=0){
            return false;
        }
        char c = rightString.charAt(position-1);
        return  c=='1'?true:false;
    }

    // 该方法存储或清除传入的权限，如果value为true，存储传入的权限，否则清除该权限。
    // position传入的权限的位置
    public void setRight(int position, boolean value){
        StringBuilder ste=new StringBuilder("00");
        ste.capacity();
        StringBuilder stringBuilder = new StringBuilder(rightString);
        if(value){
            stringBuilder.replace(position-1,position,"1");
        }else{
            stringBuilder.replace(position-1,position,"");
        }
        rightString=stringBuilder.toString();
    }
}
