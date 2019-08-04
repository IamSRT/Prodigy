package com.prodigy.extractor.offline;

import com.google.gson.Gson;
import com.prodigy.extractor.service.Cleaner;
import com.prodigy.extractor.service.Publisher;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Service public class OfflineProcessor {

    private static final String dir = "/root/data2/";

    private Cleaner cleaner;
    private Publisher publisher;

    @Autowired public OfflineProcessor (
            @Qualifier("dataCleaner") Cleaner cleaner,
            @Qualifier("apiPublisher") Publisher publisher) {

        this.cleaner = cleaner;
        this.publisher = publisher;
    }

    public void ingest () {
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            boolean success = process(file);
            if (success) deleteFile(file);
        }
    }

    private boolean process (File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;
            Gson gson = new Gson();
            while ((line = br.readLine()) != null) {
                DataExtractionRequest request = gson.fromJson(line, DataExtractionRequest.class);
                if (null == request) {
                    //System.out.println("empty data");
                    continue;
                }
                //System.out.println("file data :: " + request.toString());
                boolean filter = cleaner.clean(request);
                if (!filter) publisher.publish(request);
                else System.out.println("removing :: " + request.getUrl());
            }
        } catch (FileNotFoundException fnfe) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void deleteFile (File file) {
        //System.out.println("deleting file :: " + file.getName());
        file.delete();
    }

}
