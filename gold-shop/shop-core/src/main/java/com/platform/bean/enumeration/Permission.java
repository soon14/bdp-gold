package com.platform.bean.enumeration;

/**
 * 权限编码列表<br>
 * 权限编码需要和菜单中的菜单编码一致
 * @author wlhbdp
 * @date ：Created in 2019/7/31 11:05
 */
public interface Permission {

    //系统管理
    String CFG = "cfg";
    String CFG_EDIT = "cfgEdit";
    String CFG_DEL = "cfgDelete";
    String DICT = "dict";
    String DICT_EDIT = "dictEdit";
    String DICT_DEL = "dictDelete";
    String LOG = "log";
    String LOG_CLEAR = "logClear";
    String LOGIN_LOG = "loginLog";
    String LOGIN_LOG_CLEAR = "loginLogClear";
    String ROLE = "role";
    String ROLE_EDIT = "roleEdit";
    String ROLE_DEL = "roleDelete";
    String TASK = "task";
    String TASK_EDIT = "taskEdit";
    String TASK_DEL = "taskDelete";
    String MENU = "menu";
    String MENU_EDIT = "menuEdit";
    String MENU_DEL = "menuDelete";
    String USER = "mgr";
    String USER_EDIT = "mgrEdit";
    String USER_DEL = "mgrDelete";
    String DEPT = "dept";
    String DEPT_EDIT = "deptEdit";
    String DEPT_DEL = "deptDelete";
    String EXPRESS = "express";
    String EXPRESS_EDIT = "expressEdit";

    //消息管理
    String MSG = "msg";
    String MSG_CLEAR = "msgClear";
    String MSG_SENDER = "msgSender";
    String MSG_SENDER_EDIT = "msgSenderEdit";
    String MSG_SENDER_DEL = "msgSenderDelete";
    String MSG_TPL = "msgTpl";
    String MSG_TPL_EDIT = "msgTplEdit";
    String MSG_TPL_DEL = "msgTplDelete";

    //CMS管理
    String ARTICLE = "article";
    String ARTICLE_EDIT = "editArticle";
    String ARTICLE_DEL = "deleteArticle";
    String BANNER = "banner";
    String BANNER_EDIT = "bannerEdit";
    String BANNER_DEL = "bannerDelete";
    String CHANNEL = "channel";
    String CHANNEL_EDIT = "channelEdit";
    String CHANNEL_DEL = "channelDelete";
    String CONTACTS = "contacts";
    String FILE = "file";
    String FILE_UPLOAD = "fileUpload";

    //商城管理
    String CATEGORY_EDIT="categoryEdit";
    String GOODS_EDIT = "goodsEdit";

    //营销管理
    String TOPIC_EDIT = "topicEdit";
    String TOPIC_DEL = "topicDelete";

}
