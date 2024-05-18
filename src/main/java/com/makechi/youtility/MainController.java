package com.makechi.youtility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainController {

	@FXML
	private TextField urlField;

	@FXML
	private ComboBox<String> formatBox;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private Label statusLabel;

	@FXML
	public void initialize() {
		ObservableList<String> formats = FXCollections.observableArrayList(
				"2160p video",
				"1080p video",
				"720p video",
				"480p video",
				"360p video",
				"Audio only"
		);
		formatBox.setItems(formats);
		formatBox.setValue(formats.getFirst());
		progressBar.setProgress(0);
	}

	@FXML
	private void handleDownload(ActionEvent event) {
		System.out.println("Event: " + event);

//		https://youtu.be/PCXkYY-8cUY?si=2zJATpqQ48Ug2i5H

		String url = urlField.getText();
		String format = getFormat(formatBox.getValue());

		final String DEFAULT_DOWNLOAD_FOLDER;
		final String DEFAULT_SHORTS_DOWNLOAD_FOLDER;

		String os = System.getProperty("os.name");
		if (os.contains("win")) {
			DEFAULT_DOWNLOAD_FOLDER = System.getProperty("user.home") + "\\Videos\\Youtility";
			DEFAULT_SHORTS_DOWNLOAD_FOLDER = System.getProperty("user.home") + "\\Videos\\Youtility\\Shorts";
		} else {
			DEFAULT_DOWNLOAD_FOLDER = System.getProperty("user.home") + "/Videos/Youtility";
			DEFAULT_SHORTS_DOWNLOAD_FOLDER = System.getProperty("user.home") + "/Videos/Youtility/Shorts";
		}

		if (url.isBlank()) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "URL can't be blank");
			alert.showAndWait();
			return;
		}

		Task<Void> downloadTask = new Task<>() {
			@Override
			protected Void call() throws Exception {
				try {
					ProcessBuilder builder = new ProcessBuilder();

					if (checkIfShorts(url)) {
						builder.command("yt-dlp", "-o", DEFAULT_SHORTS_DOWNLOAD_FOLDER + "/%(title)s.%(ext)s", url);
					} else {
						builder.command("yt-dlp", "-o", DEFAULT_DOWNLOAD_FOLDER + "/%(title)s.%(ext)s", "-f", format, url);
					}

					Process process = builder.start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String line;

					while ((line = reader.readLine()) != null) {
						if (line.contains("[download]")) {
							String progress = line.substring(line.indexOf("[download]") + 10).trim();
							if (progress.contains("%")) {
								String percentString = progress.substring(0, progress.indexOf('%')).trim();
								double percent = Double.parseDouble(percentString) / 100.0;
								updateProgress(percent, 1.0);
							}
						}
						updateMessage(line);
					}

					process.waitFor();
				} catch (Exception e) {
					updateMessage("Download failed: " + e.getMessage());
					throw new Exception(e.getMessage());
				}
				return null;
			}
		};

		progressBar.progressProperty().bind(downloadTask.progressProperty());
		statusLabel.textProperty().bind(downloadTask.messageProperty());

		new Thread(downloadTask).start();
	}

	private String getFormat(String choice) {
		String format = "";
		switch (choice) {
			case "2160p video" -> format = "best[height<=2160]";
			case "1080p video" -> format = "best[height<=1080]";
			case "720p video" -> format = "best[height<=720]";
			case "480p video" -> format = "best[height<=480]";
			case "360p video" -> format = "best[height<=360]";
			case "Audio only" -> format = "bestaudio[ext=m4a]";
		}
		return format;
	}

	private boolean checkIfShorts(String url) {
		return url.contains("/shorts/");
	}
}
