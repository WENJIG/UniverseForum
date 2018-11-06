package trip.wenjig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import trip.wenjig.common.local.NewThread;
import trip.wenjig.common.local.SpringUtil;

@ServletComponentScan
@SpringBootApplication
public class UniverseForumApplication {

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(UniverseForumApplication.class, args);

		// *************************************************************************************************************
		SpringUtil springContext = new SpringUtil();
		springContext.setApplicationContext(app);
		localThreadStart();
	}

	private static void localThreadStart() {
		NewThread.DELETE_TEMP_USER.getLocalDeleteTempUserThread().start();
		NewThread.SEND_MAIL.getLocalSendMailThread().start();
		NewThread.NOVEL_AUTO_POST.AutoPostNovelThreadCorePool().start();
	}

}
