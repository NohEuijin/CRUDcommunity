package com.example.app.service;

import com.example.app.domain.dto.FileDto;
import com.example.app.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {
    private final FileMapper fileMapper;

    @Value("${file.dir}")
    private String fileDir;

    //    삽입
    public void register(FileDto fileDto){
        fileMapper.insert(fileDto);
    }
    //    삭제
    public void remove(Long boardNumber){
        if (boardNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락!!");
        }

        List<FileDto> fileList = findList(boardNumber);

        for(FileDto file : fileList){
            File target = new File(fileDir, file.getFileUploadPath() + "/" + file.getFileUuid() + "_" + file.getFileName());
            File thumbnail = new File(fileDir, file.getFileUploadPath() + "/th_" + file.getFileUuid() + "_" + file.getFileName());

            if(target.exists()){
                target.delete();
            }

            if(thumbnail.exists()){
                thumbnail.delete();
            }
        }

        fileMapper.delete(boardNumber);
    }
    //    파일 리스트 조회
    public List<FileDto> findList(Long boardNumber){
        if (boardNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락!!");
        }

        return fileMapper.selectList(boardNumber);
    }



//    파일 저장 처리
    public FileDto saveFile(MultipartFile file) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
        String originName = file.getOriginalFilename();
//        파일이름에 붙여줄 uuid를 생성(파일이름 중복이 나오지 않게 처리)
        UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
        String sysName = uuid.toString() + "_" + originName;

//        상위 경로와 하위경로를 합친다.
        File uploadPath = new File(fileDir, getUploadPath());

//        경로가 존재하지 않는다면 (폴더가 없다면)
        if(!uploadPath.exists()){
//            경로를 만들어준다.(폴더를 만든다)
            uploadPath.mkdirs();
        }

//        전체 경로와 파일이름을 연결한다.
        File uploadFile = new File(uploadPath,sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
        file.transferTo(uploadFile);

//        썸네일을 저장한다.
//        이미지 파일인 경우에만 썸네일을 저장해야한다.
        if(Files.probeContentType(uploadFile.toPath()).startsWith("image") ){
            FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_" + sysName));
            Thumbnailator.createThumbnail(file.getInputStream(), out, 300, 200);
            out.close();
        }


        FileDto fileDto = new FileDto();
        fileDto.setFileName(originName);
        fileDto.setFileUuid(uuid.toString());
        fileDto.setFileUploadPath(getUploadPath());

        return fileDto;
    }

    /**
     * 파일 DB등록 및 파일 저장 처리
     *
     * @param files 여러 파일을 담은 리스트
     * @param boardNumber 파일이 속하는 게시글 번호
     * @throws IOException
     */
    public void registerAndSaveFile(List<MultipartFile> files, Long boardNumber) throws IOException{
        for(MultipartFile file : files){
            FileDto fileDto = saveFile(file);
            fileDto.setBoardNumber(boardNumber);
            register(fileDto);
        }
    }


    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    public List<FileDto> findOldList(){
        return fileMapper.selectOldList();
    }
}















