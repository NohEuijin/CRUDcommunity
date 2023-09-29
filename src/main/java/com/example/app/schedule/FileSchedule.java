package com.example.app.schedule;

import com.example.app.domain.dto.FileDto;
import com.example.app.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileSchedule {
    private final FileService fileService;

    @Value("${file.dir}")
    private String fileDir;

//    cron 표현식
//    7개의 정보를 표현한다.
//    초 분 시 일 월 요일 년도(년도는 생략 가능)
    @Scheduled(cron = "30 * * * * *")
    public void checkFiles() throws IOException{
        log.info("File Check!!!");

//        이전 파일 리스트를 뽑아온다.
        List<FileDto> oldList = fileService.findOldList();

//        이전 파일들의 전체경로를 List<Path>타입으로 저장한다..
        List<Path> fileListPath = oldList.stream()
                .map(fileDto -> Paths.get(fileDir, fileDto.getFileUploadPath(), fileDto.getFileUuid() + "_" + fileDto.getFileName()))
                .collect(Collectors.toList());
//        이전 썸네일 파일들의 전체 경로를 fileListPath에 추가한다.
        oldList.stream().map(fileDto -> Paths.get(fileDir, fileDto.getFileUploadPath(), "th_" + fileDto.getFileUuid() + "_" + fileDto.getFileName()))
                .collect(Collectors.toList()).forEach(path -> fileListPath.add(path));

//        이전 파일들이 들어있는 경로(파일이름은 제외한)를 파일객체로 저장한다.
        File directory = Paths.get(fileDir, getUploadPathOldFile()).toFile();

//        파일객체는 폴더안에 있는 모든 파일 목록을 불러오는 기능이 있다. listFiles()이다.
//        불러온 파일들을 file매개변수로 받아 DB에서 가져온 fileListPath리스트에 존재하는지 검사하고 DB에 존재하지 않으면 삭제한다.
        File[] files = directory.listFiles(file -> !fileListPath.contains(file.toPath()));

        if(files == null) { return; }

        for(File file : files){
            log.info(file.getPath() + " delete!!!");
            file.delete();
        }

    }

    private String getUploadPathOldFile(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -6);
        return sdf.format(yesterday.getTime());
    }


}










