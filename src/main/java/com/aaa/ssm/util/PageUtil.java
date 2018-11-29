package com.aaa.ssm.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 *className:PageUtil.java
 *discription:
 *author:zz
 *createTime:2018-4-20下午1:37:02
 */
public class PageUtil {

    //分页的要素
    private int pageNo;//页码（第几页）
    private int pageSize;//每页显示数量
    private int totalSize;//总条数
    //临时变量
    private String url;//请求的url
    private String pageString;//拼接后的分页字符串

    /**
     * 构造函数    拼接所有请求参数的方法（包括get和post请求）
     * @param pageNo
     * @param pageSize
     * @param totalSize
     */
    public PageUtil(int pageNo, int pageSize, int totalSize, HttpServletRequest request){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        //获取本次请求的url（从项目名称开始）
        url = request.getRequestURI()+"?";
        //获取所有请求中带的参数的名称   aaa=111&bbb=222&pageNo=1   getParameterNames获取到结果为：aaa,bbb,pageNo
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();
            //!"pageNo".equals(parameterName)  每次请求，pageNo都在变化，所以下面拼接pageString一直会带上
            if(!"pageNo".equals(parameterName)){
                //charAt 得到字符串某个位置的上的字符
                if(url.charAt(url.length()-1)=='?'){//判断请求的最后位置是否是？
                    url+=parameterName+"="+request.getParameter(parameterName);
                }else{
                    url+="&"+parameterName+"="+request.getParameter(parameterName);
                }
            }
        }
        //为了下面拼接简单
        if(url.charAt(url.length()-1)!='?'){
            url+="&";
        }
    }

    /**
     * 拼装分页字符串
     * @return
     */
    public String getPageString(){
        //计算总页数
        int pageCount = totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1;
        StringBuffer stringBuffer = new StringBuffer();
        //上一页不能小于1
        if(pageNo<1){
            pageNo = 1;
        }
        if(pageNo>1){//不是第一页
            stringBuffer.append("<a href='"+url+"pageNo=1'>首页</a>&nbsp;<a href='"+url+"pageNo="+(pageNo-1)+"'>上一页</a>");
        }else{
            stringBuffer.append("首页&nbsp;上一页");
        }
        stringBuffer.append("&nbsp;");
        //不能大于最大页
        if(pageNo>pageCount){
            pageNo=pageCount;
        }
        if(pageNo<pageCount){//不是最大页（尾页）
            stringBuffer.append("<a href='"+url+"pageNo="+(pageNo+1)+"'>下一页</a>&nbsp;<a href='"+url+"pageNo="+pageCount+"'>尾页</a>");
        }else{
            stringBuffer.append("下一页&nbsp;尾页");
        }
        stringBuffer.append("&nbsp;");
        //拼装下拉第几页                                                                                                                                                                                //this.value  select元素选中值
        stringBuffer.append("第<select onchange=\"javascript:window.location.href='"+url+"pageNo='+this.value\">"); //为select添加onchange事件，能选择自定义页数
        for(int i=1;i<=pageCount;i++){
            if(pageNo==i){//如果当前页码（第几页）和i相等，让option被选中 selected='selected'
                stringBuffer.append("<option value='"+i+"' selected='selected'>"+i+"</option>");
            }else{
                stringBuffer.append("<option value='"+i+"'>"+i+"</option>");
            }
        }
        stringBuffer.append("</select>页&nbsp,共"+totalSize+"条&nbsp;"+pageCount+"页");
        return stringBuffer.toString();
    }
	/*public static void main(String[] args) {
		String url  = "/strutsdemo-page/userMsg/user!list?aaa=111&bbb=222&";
		System.out.println(url.charAt(url.length()-1));
		System.out.println(url.charAt(url.length()-1)=='?');
	}*/
}

