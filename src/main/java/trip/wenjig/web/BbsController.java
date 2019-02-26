package trip.wenjig.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import trip.wenjig.entity.Bbs;
import trip.wenjig.entity.Topic;
import trip.wenjig.service.BbsService;
import trip.wenjig.service.TopicService;
import trip.wenjig.service.UserService;
import trip.wenjig.util.IsInvalidCheck;
import trip.wenjig.util.SystemDateFormat;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class BbsController extends BaseController {

    private final BbsService bbsService;
    private final TopicService topicService;
    private final UserService userService;

    @Autowired
    public BbsController(BbsService bbsService, TopicService topicService, UserService userService) {
        this.bbsService = bbsService;
        this.topicService = topicService;
        this.userService = userService;
    }

    @RequestMapping( value = "/s/bbs" ,method = RequestMethod.GET)
    public String searchBbs(@RequestParam("s_bbs")String searchText, Model model) {
        if (IsInvalidCheck.allCheck(searchText) || searchText.length() > 40) {
            //setCookie("warningMsg","没有你输入的版块。也不支持创建此种名称的版块。");
            return "redirect:/index";
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Bbs bbs = bbsService.findByBbsName(searchText);
        if (bbs != null) {
            return getRet(model, bbs);
        } else {
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //    新建版块并默认发帖
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (getSession().getAttribute("isLogin") == null) {
                setCookie("warningMsg","未登录不能新建版块！请先登录或者浏览已有版块。");
                return "redirect:/a/login";
            }
            Bbs newBbs = new Bbs();
            newBbs.setBbsName(searchText);
            newBbs.setBbsSign("欢迎来到" + searchText + "版块,你可以在此畅所欲言~");
            newBbs.setBbsIcon("defMN.png");
            newBbs.setBbsTopicNum(1);
            bbsService.addBbs(newBbs);
            Bbs nowBbs = bbsService.findByBbsName(newBbs.getBbsName());
            Topic topic = new Topic();
            topic.setTopicTitle("欢迎来到"+searchText+"版块。");
            topic.setTopicContext("此版块创建时间：" + SystemDateFormat.getSystemPreciseDate()
                    + "此版块创建人：" + getSession().getAttribute("username"));
            topic.setTopicBbsid(nowBbs.getBbsId());
            topic.setPostUserName("论坛小管家");
            topic.setReleaseDate(SystemDateFormat.getSystemPreciseDate());
            topic.setIsBest(1);
            topicService.addTopic(topic);
            // END
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            return getRet(model, nowBbs);
        }
    }

    private String getRet(Model model, Bbs bbs) {
        Bbs bbsJson = JSON.parseObject(bbs.toString(), Bbs.class);
        model.addAttribute("bbsJson", bbsJson);

        ArrayList<Topic> bbsDbList = topicService.findTopicOrderReleaseDate(bbs.getBbsId());
        model.addAttribute("topicList", JSONArray.parseArray(JSON.toJSONString(bbsDbList)));

        ArrayList<String> userNameList = new ArrayList<>();
        for (int i = 0; i < bbsDbList.size(); i++) {
            userNameList.add(bbsDbList.get(i).getPostUserName());
        }
        HashMap<String,String> userHeadImgMap = userService.findByUserFaceImagePathMap(userNameList);
        model.addAttribute("userHeadImg",userHeadImgMap);

        return "html/bbs/bbsTemplate.html";
    }

}
