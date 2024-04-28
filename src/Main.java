import java.io.*;

public class Main {

	private static final String[] FORMAT_OPTIONS = {
			"2160p video",
			"1080p video",
			"720p video",
			"480p video",
			"360p video",
			"Audio only"
	};

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("Enter the URL of the video or playlist: ");
			String url = reader.readLine();

			if (checkIfShorts(url)) {
				System.out.println("Downloading shorts...");
				YouTubeDownloader.downloadShorts(url);
			} else {
				displayMenu();
				YouTubeDownloader.downloadVideo(getFormat(reader.readLine()), url);
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static void displayMenu() {
		System.out.println("Select the format:");
		for (int i = 0; i < FORMAT_OPTIONS.length; i++) {
			System.out.println((i + 1) + ". " + FORMAT_OPTIONS[i]);
		}
		System.out.println("a. About");
		System.out.println("q. Quit");
	}

	private static String getFormat(String choice) {
		String format = "";
		switch (choice) {
			case "1" -> format = "best[height<=2160]";
			case "2" -> format = "best[height<=1080]";
			case "3" -> format = "best[height<=720]";
			case "4" -> format = "best[height<=480]";
			case "5" -> format = "best[height<=360]";
			case "6" -> format = "bestaudio[ext=m4a]";
			case "A", "a" -> displayAbout();
			case "Q", "q" -> quitApplication();
			default -> System.out.println("Invalid choice. Using default format.");
		}
		return format;
	}

	private static void displayAbout() {
		System.out.println("About Youtility");
		System.exit(0);
	}

	private static void quitApplication() {
		System.out.println("It's sad to see you leave!");
		System.exit(0);
	}

	private static boolean checkIfShorts(String url) {
		return url.contains("/shorts/");
	}

}