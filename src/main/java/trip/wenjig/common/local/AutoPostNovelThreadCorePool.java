package trip.wenjig.common.local;

import trip.wenjig.entity.Bbs;
import trip.wenjig.entity.Floor;
import trip.wenjig.entity.Topic;
import trip.wenjig.service.BbsService;
import trip.wenjig.service.FloorService;
import trip.wenjig.service.TopicService;
import trip.wenjig.util.NovelUtil;
import trip.wenjig.util.SystemDateFormat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoPostNovelThreadCorePool extends Thread {

    private BbsService bbsService;
    private TopicService topicService;
    private FloorService floorService;

    private ScheduledExecutorService scheduledThreadPool;

    AutoPostNovelThreadCorePool() {
        this.bbsService = (BbsService) SpringUtil.getBean(BbsService.class);
        this.topicService = (TopicService) SpringUtil.getBean(TopicService.class);
        this.floorService = (FloorService) SpringUtil.getBean(FloorService.class);
    }

    public void newPostDomain(File tempFile, String fileName) {
        scheduledThreadPool.schedule(() -> {
            Topic topic = new Topic();

            // 默认发帖版块
            Bbs bbs = bbsService.findByBbsName("网络小说测试");
            bbsService.updateBbsTopicNumber(bbs.getBbsId());

            String baseFileName = fileName.substring(0, fileName.length() - 4);
            topic.setTopicTitle(baseFileName);
            // 默认发帖人
            String defPostUser = "席勒";
            topic.setPostUserName(defPostUser);
            String postDate = SystemDateFormat.getSystemPreciseDate();
            topic.setReleaseDate(postDate);
            topic.setTopicBbsid(bbs.getBbsId());
            topic.setTopicFloorsNum(1);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // 简介 放 1楼
            // 每楼变成一章
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            try {
                StringBuffer context = new StringBuffer("前言\n");
                String tempText;
                String templateText = "%s\n";

                // 1楼的简介
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile), StandardCharsets.UTF_8));
                while ((tempText = bufferedReader.readLine()) != null) {
                    if (NovelUtil.checkNovelChapter(tempText,0)) {
                        context.append("\n");
                    }
                    if (NovelUtil.checkNovelChapter(tempText)) {
                        break;
                    } else {
                        context.append(String.format(templateText, tempText));
                    }
                }
                topic.setTopicContext(context.toString());
                topicService.addTopic(topic);
                context = null;


                // 章节
                Floor chapter;
                int floorNum = 1;
                boolean lock = true;
                final Topic isTopic = topicService.findOneMyIdea(baseFileName, postDate, defPostUser);
                if (isTopic == null) {
                    throw new Exception("楼层依赖错误");
                }

                while ((tempText = bufferedReader.readLine()) != null) {
                    if (NovelUtil.checkNovelChapter(tempText)) {
                        chapter = new Floor();
                        assert context != null;
                        getFloor(defPostUser, context, chapter, floorNum, isTopic);

                        context = new StringBuffer();
                    }
                    if (lock) {
                        context = new StringBuffer();
                        lock = false;
                    }
                    context.append(String.format(templateText, tempText));
                }
                // 大结局以及后记的内容
                chapter = new Floor();
                assert context != null;
                getFloor(defPostUser, context, chapter, floorNum, isTopic);

                bufferedReader.close();

                //noinspection ResultOfMethodCallIgnored
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },0, TimeUnit.MINUTES);
    }

    private void getFloor(String defPostUser, StringBuffer context, Floor chapter, int floorNum, Topic isTopic) {
        chapter.setFloorContext(context.toString());
        chapter.setIsfloornum(++floorNum);
        chapter.setIstopicId(isTopic.getTopicId());
        chapter.setPostDate(SystemDateFormat.getSystemPreciseDate());
        chapter.setPostUsername(defPostUser);
        chapter.setRepliesnum(0);
        floorService.addFloor(chapter);
        topicService.updateTopicFloorNumber(isTopic.getTopicId());
        //return floorNum;
    }

    @Override
    public void run() {
        scheduledThreadPool = Executors.newScheduledThreadPool(4);
    }
}
