package builder;

import structure.AbstractFile;
import structure.File;
import structure.Folder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowSymLinkBuilder extends DirectoryBuilder {

    private Map<String, AbstractFile> cycleDetector;

    public FollowSymLinkBuilder() {
        super();
        this.cycleDetector = new HashMap<>();
    }


    @Override
    public AbstractFile build(String filePath) throws IOException {
        Path path = Path.of(filePath);

        if (cycleDetector.containsKey(filePath)) {
            return cycleDetector.get(filePath);
        }

        if (isShortCut(filePath)) {
            Path follow = extractPath(path);
            return build(follow.toString());
        }

        if (Files.isRegularFile(path)) {
            File toReturn = new File(path.toString(), path.toFile().length(), Files.getLastModifiedTime(path));
            cycleDetector.put(toReturn.getFilePath(), toReturn);
            return toReturn;
        } else {
            Folder directory = new Folder(path.toString(), Files.getLastModifiedTime(path));
            List<AbstractFile> children = new ArrayList<>();
            for (var files : path.toFile().listFiles()) {
                children.add(build(files.getPath()));
            }
            directory.addFolderContent(children);
            cycleDetector.put(directory.getFilePath(), directory);
            return directory;
        }
    }

    private boolean isShortCut(String fileName) {
        return fileName.endsWith(".lnk");
    }

    private Path extractPath(Path shortcut) {
        try {
            byte[] link = Files.readAllBytes(shortcut);
            String extracted = parseLink(link);
            return Paths.get(extracted);
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

    //TAKEN FROM STACKOVERFLOW

    private String parseLink(byte[] link) {
        // get the flags byte
        byte flags = link[0x14];

        // get the file attributes byte
        final int file_atts_offset = 0x18;

        // if the shell settings are present, skip them
        final int shell_offset = 0x4c;
        final byte has_shell_mask = (byte) 0x01;
        int shell_len = 0;
        if ((flags & has_shell_mask) > 0) {
            // the plus 2 accounts for the length marker itself
            shell_len = bytes2short(link, shell_offset) + 2;
        }

        // get to the file settings
        int file_start = 0x4c + shell_len;

        final int file_location_info_flag_offset_offset = 0x08;
        int file_location_info_flag = link[file_start + file_location_info_flag_offset_offset];
        boolean isLocal = (file_location_info_flag & 2) == 0;
        // get the local volume and local system values
        //final int localVolumeTable_offset_offset = 0x0C;
        final int basename_offset_offset = 0x10;
        final int networkVolumeTable_offset_offset = 0x14;
        final int finalname_offset_offset = 0x18;
        int finalname_offset = link[file_start + finalname_offset_offset] + file_start;
        String finalname = getNullDelimitedString(link, finalname_offset);
        if (isLocal) {
            int basename_offset = link[file_start + basename_offset_offset] + file_start;
            String basename = getNullDelimitedString(link, basename_offset);
            return basename + finalname;
        } else {
            int networkVolumeTable_offset = link[file_start + networkVolumeTable_offset_offset] + file_start;
            int shareName_offset_offset = 0x08;
            int shareName_offset = link[networkVolumeTable_offset + shareName_offset_offset]
                + networkVolumeTable_offset;
            String shareName = getNullDelimitedString(link, shareName_offset);
            return shareName + "\\" + finalname;
        }
    }

    private static String getNullDelimitedString(byte[] bytes, int off) {
        int len = 0;
        // count bytes until the null character (0)
        while (true) {
            if (bytes[off + len] == 0) {
                break;
            }
            len++;
        }
        return new String(bytes, off, len);
    }

    /*
     * convert two bytes into a short note, this is little endian because it's
     * for an Intel only OS.
     */
    private static int bytes2short(byte[] bytes, int off) {
        return ((bytes[off + 1] & 0xff) << 8) | (bytes[off] & 0xff);
    }

}
