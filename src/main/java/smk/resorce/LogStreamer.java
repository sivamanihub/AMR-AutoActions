package smk.resorce;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.jcraft.jsch.*;

public class LogStreamer {

    // 🔁 Thread-safe event queue
    private static final BlockingQueue<String> eventQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Thread 1 → Log streaming
        executor.submit(() -> startLogStreaming());

        // Thread 2 → UI automation
        executor.submit(() -> processUIActions());
    }

    // ================================
    // 🔹 LOG STREAMING THREAD
    // ================================
    private static void startLogStreaming() {

        String host = "10.79.169.160";   // AMR IP
        String user = "pi";
        String password = "sivamani";

        while (true) { // auto-reconnect loop
            try {
                JSch jsch = new JSch();
                Session session = jsch.getSession(user, host, 22);
                session.setPassword(password);
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();

                // 👉 Step 1: Get latest log file
                String latestLog = getLatestLogFile(session);

                if (latestLog == null) {
                    System.out.println("No log file found!");
                    return;
                }

                System.out.println("📄 Latest Log: " + latestLog);

                // 👉 Step 2: Start tail -F
                ChannelExec channel = (ChannelExec) session.openChannel("exec");
                channel.setCommand("tail -F " + latestLog);

                InputStream input = channel.getInputStream();
                channel.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String line;
                while ((line = reader.readLine()) != null) {

                    System.out.println("LOG: " + line);

                    // 👉 Process log line
                    processLog(line);
                }

                channel.disconnect();
                session.disconnect();

            } catch (Exception e) {
                System.out.println("⚠️ Reconnecting due to error: " + e.getMessage());

                try {
                    Thread.sleep(5000); // wait before reconnect
                } catch (InterruptedException ignored) {}
            }
        }
    }

    // ================================
    // 🔹 GET LATEST LOG FILE
    // ================================
    private static String getLatestLogFile(Session session) throws Exception {

        ChannelExec channel = (ChannelExec) session.openChannel("exec");

        // Sort by time → get latest
        channel.setCommand("ls -t /home/pi/log-APPU5-BOT2-M-*.txt | head -1");

        InputStream input = channel.getInputStream();
        channel.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String latestFile = reader.readLine();

        channel.disconnect();

        return latestFile;
    }

    // ================================
    // 🔹 LOG PARSER
    // ================================
    private static void processLog(String line) {
        try {
            if (line.contains("ERROR")) {
                eventQueue.put("STOP");
            }

            if (line.contains("ABORT")) {
                eventQueue.put("ABORT");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================================
    // 🔹 UI AUTOMATION THREAD
    // ================================
    private static void processUIActions() {

        while (true) {
            try {
                String event = eventQueue.take();

                switch (event) {

                    case "STOP":
                        System.out.println("🚨 Trigger STOP UI Action");
                        performStop();
                        break;

                    case "ABORT":
                        System.out.println("🛑 Trigger ABORT UI Action");
                        performAbort();
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ================================
    // 🔹 YOUR EXISTING SELENIUM METHODS
    // ================================
    private static void performStop() {
        // 👉 Call your Selenium STOP code here
        System.out.println("Executing STOP in UI...");
    }

    private static void performAbort() {
        // 👉 Call your Selenium ABORT code here
        System.out.println("Executing ABORT in UI...");
    }
}