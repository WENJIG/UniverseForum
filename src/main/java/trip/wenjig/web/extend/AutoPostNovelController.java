package trip.wenjig.web.extend;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import trip.wenjig.common.local.NewThread;
import trip.wenjig.util.SystemDateFormat;
import trip.wenjig.web.BaseController;

import java.io.File;

@Controller
public class AutoPostNovelController extends BaseController {

    @RequestMapping(value = "/manager/auto.post.novel.topic", method = RequestMethod.POST)
    public String autoPostNovel(@RequestParam("txtFile") MultipartFile file) {
        //检查约束
        try {
            if (file.isEmpty()) {
                return "html/hint/extend/autoPostNovelLoser.html";
            }
            if (file.getSize() > 100000000) {
                return "html/hint/extend/autoPostNovelLoser.html";
            }
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if (!(suffixName.equals(".txt"))) {
                return "html/hint/extend/autoPostNovelLoser.html";
            }
            String fileName = file.getOriginalFilename();
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            String savePath = "static/temptxt/" + SystemDateFormat.getSystemPreciseDate("时间戳");
            File tempFile = new File(path.getAbsolutePath(), savePath);
            //org.apache.commons.io.FileUtils.copyInputStreamToFile(file.getInputStream(),tempFile);
            file.transferTo(tempFile);
            NewThread.NOVEL_AUTO_POST.AutoPostNovelThreadCorePool().newPostDomain(tempFile, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "html/hint/extend/autoPostNovelSucceed.html";
    }

}
