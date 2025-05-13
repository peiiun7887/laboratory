package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.lang.Thread.sleep;

@Component
public class ScheduleEntry {

    /**
     * *    *    *    *    *    *
     * -    -    -    -    -    -
     * |    |    |    |    |    |
     * |    |    |    |    |    +----- Day of the week (0 - 7) (Sunday is both 0 and 7)
     * |    |    |    |    +---------- Month (1 - 12)
     * |    |    |    +--------------- Day of the month (1 - 31)
     * |    |    +-------------------- Hour (0 - 23)
     * |    +------------------------- Minute (0 - 59)
     * +------------------------------ Second (0 - 59) [optional]
     */

    private final Random random = new Random();
    private final Robot robot = new Robot();

    @Value("${clock.sleep:false}")
    private boolean blockSleep;

    @Value("${clock.in}")
    private boolean clockIn;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleEntry.class);


    static {
        System.setProperty("java.awt.headless", "false");
    }

    private static final Logger log = LoggerFactory.getLogger(ScheduleEntry.class);

    public ScheduleEntry() throws AWTException {
    }


    @Scheduled(cron = "10 * * * * *")
    public void blockSleep() {
        log.info("Running blockSleep...");
        if ((Boolean.TRUE.equals(blockSleep))) {
            Point point = MouseInfo.getPointerInfo().getLocation();
            try {
                Robot robot = new Robot();
                robot.mouseMove(point.x+2, point.y + 2);
                robot.mouseMove(point.x-2, point.y - 2);
            } catch (AWTException e) {
                logger.error("Error in blockSleep", e);
            }

        } else {
            logger.info("skiped blockSleep");
        }
    }

//    @Scheduled(cron = "0 0 9 * * MON-FRI")
    public void clockIn() throws IOException {
        doClock("ClockIn");
    }

//    @Scheduled(cron = "0 31 18 * * MON-FRI")
    public void clockOut() throws IOException {
        doClock("ClockOut");
    }

    private void doClock(String opt) throws IOException {
        logger.info("Running {}...", opt);

        if ((Boolean.TRUE.equals(clockIn))) {
            String os = System.getProperty("os.name");
            String user = System.getProperty("user.name");
            int randomDelay = random.nextInt(30000) ;
            logger.info("OS:{} user:{} delay:{}", os, user, randomDelay);
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                // open Clock.exe
                String path = "C:\\Users\\" + user + "\\Desktop\\Clock.exe";
                File file = new File(path);
                desktop.open(file);
                robot.delay(randomDelay);

                logger.info("Click clock-btn");
//                robot.mouseMove(1034, 630); // query-btn
                robot.mouseMove(872, 630); // clock-btn
                robot.delay(1000);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Press the left mouse button
                robot.delay(500);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                logger.info("Click pop window");
                robot.mouseMove(1061, 477);
                robot.delay(1000);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Press the left mouse button
                robot.delay(500);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                logger.info("Click close-btn");
                robot.mouseMove(1082, 317); // close-btn
                robot.delay(1000);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Press the left mouse button
                robot.delay(500);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            } else {
                logger.error("Desktop is not supported");
            }
        } else {
            logger.info("Skip {}", opt);
        }
    }

}
