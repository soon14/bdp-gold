package com.platform.warpper;

import com.platform.service.system.impl.ConstantFactory;
import com.platform.utils.BeanUtil;
import com.platform.utils.StringUtil;

import java.util.Map;

/**
 * 日志列表的包装类
 *
 * @author wlhbdp
 * @date 2020年4月5日22:56:24
 */
public class LogWarpper extends BaseControllerWarpper {

    public LogWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        String message = (String) map.get("message");
        Long userid = Long.valueOf(map.get("userid").toString());
        map.put("userName", ConstantFactory.me().getUserNameById(userid));

        //如果信息过长,则只截取前100位字符串
        if (StringUtil.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            map.put("message", subMessage);
        }

        //如果信息中包含分割符号;;;   则分割字符串返给前台
        if (StringUtil.isNotEmpty(message) && message.indexOf(BeanUtil.SEPARATOR) != -1) {
            String[] msgs = message.split(BeanUtil.SEPARATOR);
            map.put("regularMessage",msgs);
        }else{
            map.put("regularMessage",message);
        }
    }

}
