package com.example.demo_blog_management.service;

class Solution {
    public boolean isMatch(String s, String p) {
        if(p.equals(".*")){
            return true;
        }
        int sl=s.length();
        int pl=p.length();
        int[][] dp=new int[sl+1][pl+1];
        dp[0][0]=1;
        for(int i=1;i<sl+1;i++){
            dp[i][0]=0;
        }
        dp[0][1]=0;
        for(int j=2;j<pl+1;j++){
            if(p.substring(j-1,j+1-1).equals("*")){
                if(dp[0][j-1]==1||dp[0][j-2]==1){
                    dp[0][j]=1;
                }
                else{
                    dp[0][j]=0;
                }
            }
            else{
                dp[0][j]=0;
            }
        }
        for(int i=1;i<sl+1;i++){
            for(int j=1;j<pl+1;j++){
                if(p.substring(0,j).equals(".*")){
                    dp[i][j]=1;
                }
                else{
                    if(s.substring(i-1,i+1-1).equals(p.substring(j-1,j+1-1))||p.substring(j-1,j+1-1).equals(".")){
                        if(dp[i-1][j-1]==1){
                            dp[i][j]=1;
                        }
                        else{
                            dp[i][j]=0;
                        }
                    }
                    else if(p.substring(j-1,j+1-1).equals("*")){
                        if(dp[i][j-1]==1||dp[i][j-2]==1){
                            dp[i][j]=1;
                        }
                        else if(s.substring(i-1,i+1-1).equals(p.substring(j-1-1,j+1-1-1))&&dp[i-1][j]==1){
                            dp[i][j]=1;
                        }
                        else if(p.substring(j-1-1,j+1-1-1).equals(".")){
                            int flag=0;
                            for(int k=0;k<=i;k++){
                                if(dp[k][j-2]==1){
                                    flag=1;
                                }
                            }
                            if(flag==1){
                                dp[i][j]=1;
                            }
                            else{
                                dp[i][j]=0;
                            }
                        }
                        else{
                            dp[i][j]=0;
                        }
                    }
                    else {
                        dp[i][j]=0;
                    }
                }
            }
        }
        if(dp[sl][pl]==1){
            return true;
        }
        else{
            return false;
        }
    }


}