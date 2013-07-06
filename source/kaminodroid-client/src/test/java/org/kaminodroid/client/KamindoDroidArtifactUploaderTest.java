package org.kaminodroid.client;

import java.io.File;
import java.io.FileWriter;

import org.junit.Before;
import org.junit.Test;

public class KamindoDroidArtifactUploaderTest {

    private KaminoDroidArtifactUploader kaminoDroidArtifactUploader;

    @Before
    public void setUp() {
        kaminoDroidArtifactUploader = new KaminoDroidArtifactUploader("http://localhost:8080/artifacts");
    }
    @Test
    public void uploadFile() throws Exception {
        File file = new File("target/temp.apk");
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("content");
        fileWriter.close();

        String url = kaminoDroidArtifactUploader.uploadFile(new File("target/kaminodroid-client-1.0.0-SNAPSHOT.jar"));
        System.out.println(url);
    }
}
