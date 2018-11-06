package trip.wenjig.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import trip.wenjig.entity.*;
import trip.wenjig.service.*;
import trip.wenjig.util.SystemDateFormat;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class TopicController extends BaseController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private BbsService bbsService;
    @Autowired
    private UserService userService;
    @Autowired
    private FloorService floorService;
    @Autowired
    private ReplyService replyService;

    @RequestMapping("/n/postTopic")
    public String postNewTopic(@RequestParam("tit") String titleText, @RequestParam("text") String conText, @RequestParam("bbsName") String bbsName) throws UnsupportedEncodingException {
        if (titleText.length() > 50 || conText.length() > 9999 || conText.trim().equals("") || conText == null) {
            return "redirect:/s/bbs?s_bbs=" + java.net.URLEncoder.encode(bbsName, "UTF-8");
        }
        if (getSession().getAttribute("isLogin") == null || (boolean) getSession().getAttribute("isLogin") == false) {
            setCookie("warningMsg", "未登录不能发帖！");
            return "redirect:/a/login";
        } else {
            Topic newPostTopic = new Topic();
            Bbs bbs = bbsService.findByBbsName(bbsName);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //更新用户发帖次数之类的信息,更新版块发帖次数之类的信息
            User postUser = userService.findByUserId((long) getSession().getAttribute("userId"));
            userService.updateUserPostTopicNumber(postUser.getTopicNumber() + 1, postUser.getUserId());
            bbsService.updateBbsTopicNumber(bbs.getBbsId());
            //END
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            newPostTopic.setTopicTitle(titleText);
            newPostTopic.setTopicContext(conText);
            newPostTopic.setReleaseDate(SystemDateFormat.getSystemPreciseDate());
            newPostTopic.setPostUserName((String) getSession().getAttribute("username"));
            newPostTopic.setTopicBbsid(bbs.getBbsId());
            newPostTopic.setTopicFloorsNum(1);
            topicService.addTopic(newPostTopic);
            //重定向后cookie不会立即生效，起不到提示作用，暂时不解决
            //setCookie("warningMsg", "发帖成功！");
            return "redirect:/s/bbs?s_bbs=" + java.net.URLEncoder.encode(bbsName, "UTF-8");
        }
    }

    @RequestMapping("/n/postFloor")
    public String postFloor(@RequestParam("t_id") long topicId, @RequestParam("text") String text) {
        if (text.length() > 9999 || text == null || text.trim().equals("")) {
            //setCookie("warningMsg", " 回复失败！");
            return "redirect:/p/" + topicId;
        }
        if (getSession().getAttribute("isLogin") == null || (boolean) getSession().getAttribute("isLogin") == false) {
            setCookie("warningMsg", "未登录不能发帖！");
            return "redirect:/a/login";
        }
        Floor newFloor = new Floor();
        Topic topic = topicService.findById(topicId);
        newFloor.setFloorContext(text);
        newFloor.setIsfloornum(topic.getTopicFloorsNum() + 1);
        newFloor.setIstopicId(topicId);
        newFloor.setPostDate(SystemDateFormat.getSystemPreciseDate());
        newFloor.setPostUsername((String) getSession().getAttribute("username"));
        newFloor.setRepliesnum(0);
        if (!topicService.updateTopicFloorNumber(topic.getTopicId())) {
            // 修改失败
            return "redirect:/p/" + topicId + "#floordomain";
        }
        floorService.addFloor(newFloor);
        return "redirect:/p/" + topicId + "#floordomain";
    }

    @RequestMapping(value = "/p/{lookTopicId}", method = RequestMethod.GET)
    public String lookTopic(@PathVariable String lookTopicId, Model model) {
        Long id = Long.valueOf(lookTopicId).longValue();
        Topic topic = topicService.findById(id);
        if (topic == null) {
            return "redirect:/hint/notFoundTopic";
        } else {
            model.addAttribute("topic", topic);
        }
        // 楼层以及所属楼层的二级回复模型
        ArrayList<Floor> isTopicFloorList = floorService.findIsTopicAllFloorList(topic.getTopicId());
        model.addAttribute("floorList", JSONArray.parseArray(JSON.toJSONString(isTopicFloorList)));
        // 加载头像
        ArrayList<String> userNameList = new ArrayList<>();
        userNameList.add(topic.getPostUserName());
        if (isTopicFloorList.size() > 0) {
            HashMap<Long, ArrayList<Reply>> floorContextMap = new HashMap<>();
            for (int i = 0; i < isTopicFloorList.size(); i++) {
                floorContextMap.put(isTopicFloorList.get(i).getIsfloornum(),
                        replyService.findIsFloorAllReplyList(isTopicFloorList.get(i).getFloorId()));
            }
            // 二级回复,KEY为楼层数
            model.addAttribute("allFloorMap", floorContextMap);

            // floor用户头像
            for (int i = 0; i < isTopicFloorList.size(); i++) {
                userNameList.add(isTopicFloorList.get(i).getPostUsername());
            }
        }
        HashMap<String, String> userHeadImgMap = userService.findByUserFaceImagePathMap(userNameList);
        model.addAttribute("userHeadImg", userHeadImgMap);

        return "html/bbs/topicTemplate.html";
    }

    @RequestMapping("/hint/notFoundTopic")
    public String notFoundTopicHint() {
        return "html/hint/notFoundPage.html";
    }

}
