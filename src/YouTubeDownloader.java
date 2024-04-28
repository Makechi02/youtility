import java.io.*;

public class YouTubeDownloader {
	private static final String DEFAULT_DOWNLOAD_FOLDER;
	private static final String DEFAULT_SHORTS_DOWNLOAD_FOLDER;

	static {
		String os = System.getProperty("os.name");
		if (os.contains("win")) {
			DEFAULT_DOWNLOAD_FOLDER = System.getProperty("user.home") + "\\Videos\\Youtility";
			DEFAULT_SHORTS_DOWNLOAD_FOLDER = System.getProperty("user.home") + "\\Videos\\Youtility\\Shorts";
		} else {
			DEFAULT_DOWNLOAD_FOLDER = System.getProperty("user.home") + "/Videos/Youtility";
			DEFAULT_SHORTS_DOWNLOAD_FOLDER = System.getProperty("user.home") + "/Videos/Youtility/Shorts";
		}
	}

	public static void downloadVideo(String format, String url) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command("yt-dlp", "-o", DEFAULT_DOWNLOAD_FOLDER + "/%(title)s.%(ext)s", "-f", format, url);
			downloadVideos(processBuilder);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void downloadShorts(String url) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command("yt-dlp", "-o", DEFAULT_SHORTS_DOWNLOAD_FOLDER + "/%(title)s.%(ext)s", url);
			downloadVideos(processBuilder);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private static void downloadVideos(ProcessBuilder processBuilder) throws IOException, InterruptedException {
		processBuilder.redirectErrorStream(true);

		Process process = processBuilder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		process.waitFor();
		reader.close();
	}
}
