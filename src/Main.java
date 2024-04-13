import java.io.*;

public class Main {

	private static final String[] FORMAT_OPTIONS = {
			"Best video and audio",
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

			System.out.println("Select the format:");
			for (int i = 0; i < FORMAT_OPTIONS.length; i++) {
				System.out.println((i + 1) + ". " + FORMAT_OPTIONS[i]);
			}

			int choice = Integer.parseInt(reader.readLine());
			String format = switch (choice) {
				case 1 -> "bestvideo[height<=720]+bestaudio/best[height<=720]";
				case 2 -> "best[height<=720]";
				case 3 -> "best[height<=480]";
				case 4 -> "best[height<=360]";
				case 5 -> "bestaudio";
				default -> {
					System.out.println("Invalid choice. Using default format.");
					yield "";
				}
			};

			YouTubeDownloader.downloadVideo(format, url);

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}