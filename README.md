# Youtility: Your Personal YouTube Video Archiver

Youtility is a Java command-line application that allows you to download videos from YouTube using yt-dlp.

## Features

- Download YouTube videos and playlists in various formats.
- Choose from a selection of predefined formats for downloading videos.
- Specify the URL of the video or playlist to download.
- Set a default download folder for saving downloaded videos.

## Installation

1. Make sure you have Java installed on your system.
2. Install `yt-dlp` by following the instructions on the [yt-dlp GitHub page](https://github.com/yt-dlp/yt-dlp#installation).
3. Download the `Youtility.jar` file from the [releases page](https://github.com/Makechi02/youtility/releases).
4. Place the JAR file in a directory of your choice.

## Creating an Alias

To make it easier to run Youtility from the command line, you can create an alias. Add the following line to your shell configuration file (e.g., `.bashrc`, `.bash_profile`, `.zshrc`):

```bash
alias youtility='java -jar /path/to/Youtility.jar'
```

Replace `/path/to/Youtility.jar` with the actual path to your downloaded JAR file.

After adding the alias, you can simply run youtility from the command line to start the Youtility Tool.

## Usage

To download a video, run the following command in your terminal: 
```bash
java -jar Youtility.jar
```

Follow the prompts to enter the URL of the video or playlist and choose the desired format.

## Format Options

The following format options are available:

1. 2160p video,
2. 1080p video,
3. 720p video
4. 480p video
5. 360p video
6. Audio only (m4a format)

## Default Download Folder

Downloaded videos are stored in the `Videos/YouTube` directory within the user's home directory. This ensures that your downloaded videos are neatly organized in a dedicated folder.
## Acknowledgements

- This project uses [yt-dlp](https://github.com/yt-dlp/yt-dlp) for downloading YouTube videos.

## Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests.

